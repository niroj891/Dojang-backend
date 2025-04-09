package com.dojang.controller;

import com.dojang.dao.*;
import com.dojang.dto.EventRequestDto;
import com.dojang.exception.UserException;
import com.dojang.model.*;
import com.dojang.service.EventService;
import com.dojang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/instructor")
public class InstructorController {
    @Autowired
    private EventDao eventDao;


    @Autowired
    private UserDao userDao;


    @Autowired
    private EventService eventService;


    @Autowired
    private UserService userService;


    @Autowired
    private ParticipationDao participationDao;

    @Autowired
    private MatchDao matchDao;

    @Autowired
    private RoundDao roundDao;


    @PostMapping("/event")
    public ResponseEntity<?> createEvent(@ModelAttribute EventRequestDto eventRequestDto) throws IOException {
        Event event = new Event();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User instructor = userDao.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println(email);
        event.setEventDate(new Date());
        event.setInstructor(instructor);
        event.setDescription(eventRequestDto.getDescription());
        event.setTitle(eventRequestDto.getTitle());
        event.setEndDate(eventRequestDto.getEndDate());
        String message = eventService.createEvent(event, eventRequestDto.getImageFile());
        // event.set
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @GetMapping("/event")
    public ResponseEntity<?> getEvents() throws UserException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User userByEmail = userService.findUserByEmail(email);
        if (userByEmail == null) {
            throw new UserException("User not found");
        }
        List<Event> list = eventDao.findAll().stream().filter(event -> event.getInstructor().getEmail().equals(email)).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/events/{eventId}/participants")
    public ResponseEntity<List<Participation>> getParticipantsByEventAndWeight(
            @PathVariable Integer eventId,
            @RequestParam(required = false) WeightCategory weightCategory) {

        List<Participation> participants;
        if (weightCategory != null) {
            participants = participationDao.findByEventIdAndWeightCategory(eventId, weightCategory);
        } else {
            participants = participationDao.findByEventId(eventId);
        }
        return ResponseEntity.ok(participants);
    }

    @PostMapping("/matches/create")
    public ResponseEntity<Match> createMatch(
            @RequestParam Integer eventId,
            @RequestParam WeightCategory weightCategory) {

        Event event = eventDao.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        List<Participation> availableParticipants = participationDao
                .findByEventIdAndWeightCategoryAndPlayerStatus(eventId, weightCategory, PlayerStatus.NOTOUT);

        if (availableParticipants.size() < 2) {
            throw new RuntimeException("Not enough participants available");
        }

        // Shuffle and pick 2 random participants
        Collections.shuffle(availableParticipants);
        Participation player1 = availableParticipants.get(0);
        Participation player2 = availableParticipants.get(1);

        // Determine current round number
        int currentRound = matchDao.findMaxRoundByEventAndWeightCategory(eventId, weightCategory)
                .orElse(0) + 1;

        Match match = new Match();
        match.setEvent(event);
        match.setWeightCategory(weightCategory);
        match.setPlayer1(player1);
        match.setPlayer2(player2);
        match.setRoundNumber(currentRound);
        match.setMatchDate(new Date());
        match.setStatus(MatchStatus.SCHEDULED);

        return ResponseEntity.ok(matchDao.save(match));
    }

    @PostMapping("/rounds/record")
    public ResponseEntity<Round> recordRoundResult(
            @RequestParam Integer matchId,
            @RequestParam Integer winnerId,
            @RequestParam Integer loserId) {

        Match match = matchDao.findById(Long.valueOf(matchId))
                .orElseThrow(() -> new RuntimeException("Match not found"));

        Participation winner = participationDao.findById(winnerId)
                .orElseThrow(() -> new RuntimeException("Winner not found"));

        Participation loser = participationDao.findById(loserId)
                .orElseThrow(() -> new RuntimeException("Loser not found"));

        // Verify participants are in this match
        if (!match.getPlayer1().getId().equals(winnerId) &&
                !match.getPlayer2().getId().equals(winnerId)) {
            throw new RuntimeException("Winner is not in this match");
        }

        if (!match.getPlayer1().getId().equals(loserId) &&
                !match.getPlayer2().getId().equals(loserId)) {
            throw new RuntimeException("Loser is not in this match");
        }

        // Update loser status
        loser.setPlayerStatus(PlayerStatus.OUT);
        participationDao.save(loser);

        // Determine round number
        int roundNumber = roundDao.countByMatchId(Long.valueOf(matchId)) + 1;

        // Record round
        Round round = new Round();
        round.setMatch(match);
        round.setRoundNumber(roundNumber);
        round.setWinner(winner);
        round.setLoser(loser);
        round.setEndTime(new Date());

        // Update match status if this is the final round
        if (roundNumber >= 3) { // Assuming best of 3 rounds
            match.setStatus(MatchStatus.COMPLETED);
            matchDao.save(match);
        }

        return ResponseEntity.ok(roundDao.save(round));
    }

    @PostMapping("/tournaments/reset")
    public ResponseEntity<String> resetTournament(
            @RequestParam Integer eventId,
            @RequestParam WeightCategory weightCategory) {

        List<Participation> participants = participationDao
                .findByEventIdAndWeightCategory(eventId, weightCategory);

        participants.forEach(p -> {
            p.setPlayerStatus(PlayerStatus.NOTOUT);
            participationDao.save(p);
        });

        // Optionally clear matches and rounds for this event/weight category
        matchDao.deleteByEventIdAndWeightCategory(eventId, weightCategory);

        return ResponseEntity.ok("Tournament reset successfully");
    }

    @GetMapping("/tournaments/progress")
    public ResponseEntity<Map<String, Object>> getTournamentProgress(
            @RequestParam Integer eventId,
            @RequestParam WeightCategory weightCategory) {

        List<Match> matches = matchDao.findByEventIdAndWeightCategoryOrderByRoundNumber(eventId, weightCategory);
        List<Round> rounds = roundDao.findByMatchEventIdAndMatchWeightCategory(eventId, String.valueOf(weightCategory));

        List<Participation> remainingParticipants = participationDao
                .findByEventIdAndWeightCategoryAndPlayerStatus(eventId, weightCategory, PlayerStatus.NOTOUT);

        Map<String, Object> response = new HashMap<>();
        response.put("matches", matches);
        response.put("rounds", rounds);
        response.put("remainingParticipants", remainingParticipants);
        response.put("winner", remainingParticipants.size() == 1 ? remainingParticipants.get(0) : null);

        return ResponseEntity.ok(response);
    }
}




