package com.dojang.service;

import com.dojang.dao.MatchDao;
import com.dojang.dao.ParticipationDao;
import com.dojang.dao.ResultDao;
import com.dojang.dto.ParticipationResultDto;
import com.dojang.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchResultService {
    private final MatchDao matchRepository;

    private final ParticipationDao participationRepository;

    @Autowired
    private ResultDao resultDao;




//    /**
//     * Declare winner for a match and record the result.
//     */
//    public Result declareWinner(Long matchId, Long winnerId) {
//
//        // Fetch match
//        Match match = matchRepository.findById(matchId)
//                .orElseThrow(() -> new RuntimeException("Match not found with ID: " + matchId));
//
//        // Fetch winner participation
//        Participation winner = participationRepository.findById(winnerId)
//                .orElseThrow(() -> new RuntimeException("Participant not found with ID: " + winnerId));
//
//        // Validate winner is one of the players in the match
//        if (!winner.getId().equals(match.getPlayer1().getId()) &&
//                !winner.getId().equals(match.getPlayer2().getId())) {
//            throw new RuntimeException("Selected winner did not participate in this match.");
//        }
//
//        // Create Result
//        Result result = new Result();
//        result.setMatch(match);
//        result.setWinner(winner);
//        // Optionally set points, duration, remarks etc.
//
//        // Save result
//        resultDao.save(result);
//
//        // Update match status to COMPLETED
//        match.setStatus(MatchStatus.COMPLETED);
//        matchRepository.save(match);
//
//        return result;
//    }





    public List<ParticipationResultDto> getRecentWinners(Integer eventId, WeightCategory weightCategory) {
        // Get matches ordered by most recent first
        List<Match> matches = matchRepository.findByEventAndWeightCategoryOrderByDateDesc(
                eventId, weightCategory);

        if (matches.isEmpty()) {
            return Collections.emptyList();
        }

        List<ParticipationResultDto> results = new ArrayList<>();

        // First Winner - Winner of last match
        Match lastMatch = matches.get(0);
        if (lastMatch.getResult() != null && lastMatch.getResult().getWinner() != null) {
            results.add(new ParticipationResultDto(
                    lastMatch.getResult().getWinner(),
                    "FIRST_WINNER (Last Match Winner)", lastMatch.getEvent().getTitle(),
                    lastMatch.getEvent().getInstructor().getFirstName()+" "+lastMatch.getEvent()
                            .getInstructor().getLastName()
            ));
        }

        // Second Winner - Loser of last match
        if (lastMatch.getResult() != null && lastMatch.getResult().getLosser() != null) {
            results.add(new ParticipationResultDto(
                    lastMatch.getResult().getLosser(),
                    "SECOND_WINNER (Last Match Loser)",lastMatch.getEvent().getTitle(),
                    lastMatch.getEvent().getInstructor().getFirstName()+" "+lastMatch.getEvent()
                            .getInstructor().getLastName()
            ));
        }

        // Third Winner - Loser of second last match (if exists)
        if (matches.size() > 1 && matches.get(1).getResult() != null
                && matches.get(1).getResult().getLosser() != null) {
            results.add(new ParticipationResultDto(
                    matches.get(1).getResult().getLosser(),
                    "THIRD_WINNER (Second Last Match Loser)", lastMatch.getEvent().getTitle(),
                    lastMatch.getEvent().getInstructor().getFirstName()+" "+lastMatch.getEvent()
                            .getInstructor().getLastName()
            ));
        }

        return results;
    }


}
