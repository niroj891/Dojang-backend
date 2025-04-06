package com.dojang.controller;

import com.dojang.model.Event;
import com.dojang.service.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PublicController {

    private final EventService eventService;
    
    public PublicController(EventService eventService){
        this.eventService = eventService;
    }


    @GetMapping("/events")
    public List<Event> events(){
      return  eventService.getAllEvents();
    }
}
