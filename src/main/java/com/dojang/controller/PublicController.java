package com.dojang.controller;

import com.dojang.dao.ParticipationDao;
import com.dojang.dto.ParticipationResultDto;
import com.dojang.model.*;
import com.dojang.service.EventService;
import com.dojang.service.MatchResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class PublicController {

    private final EventService eventService;

    @Autowired
    private MatchResultService matchResultService;

    @Autowired
    private ParticipationDao participationDao;

    public PublicController(EventService eventService){
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public List<Event> events(){
      return  eventService.getAllEvents();
    }

    @GetMapping("/events/active")
    public List<Event> activeEvents(){
        List<Event> list = eventService.getAllEvents().stream().filter(event -> event.getEndDate() != null).toList();
        return list.stream().filter(event -> (
                new Date().before(event.getEndDate())
        )).toList();
    }

    @GetMapping("/leaderboard")
    public List<List<ParticipationResultDto>> ahsjasa(){
        List<Participation> participations = participationDao.findSingleRemainingParticipants();
        System.out.println("Participants count:-----------------"+participations.size());
        List<List<ParticipationResultDto>> listOfWinnerOfCompletedEvent = new ArrayList<>();
        for (Participation participation:participations){
            List<ParticipationResultDto> recentWinners = matchResultService.getRecentWinners(participation.getEvent().getEventId(), participation.getWeightCategory());
            listOfWinnerOfCompletedEvent.add(recentWinners);
        }
        return listOfWinnerOfCompletedEvent;
    }

}

//previous code of leaderboard
//        List<MatchResultService.ParticipationResultDto> winners = matchResultService.getRecentWinners(
//                352,
//                WeightCategory.BELOW68);
//
//        winners.forEach(winner -> {
//            System.out.println(winner.getPosition() + ": " +
//                    winner.getParticipation().getUser().getFirstName());
//        });
