package com.dojang.controller;

import com.dojang.dao.EventDao;
import com.dojang.dao.UserDao;
import com.dojang.dto.EventRequestDto;
import com.dojang.exception.UserException;
import com.dojang.model.Event;
import com.dojang.model.User;
import com.dojang.service.EventService;
import com.dojang.service.UserService;
import com.dojang.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @PostMapping("/event")
    public ResponseEntity<?> createEvent(@ModelAttribute EventRequestDto eventRequestDto) throws IOException {
        Event event = new Event();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User instructor = userDao.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
        System.out.println(email);
        event.setEventDate(new Date());
        event.setInstructor(instructor);
        event.setDescription(eventRequestDto.getDescription());
        event.setTitle(eventRequestDto.getTitle());
        event.setEndDate(eventRequestDto.getEndDate());
        String message = eventService.createEvent(event, eventRequestDto.getImageFile());
        // event.set
        return new ResponseEntity<>(message,HttpStatus.OK);
    }


    @GetMapping("/event")
    public ResponseEntity<?> getEvents() throws UserException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User userByEmail = userService.findUserByEmail(email);
        if (userByEmail==null){
            throw new UserException("User not found");
        }
        List<Event> list = eventDao.findAll().stream().filter(event -> event.getInstructor().getEmail().equals(email)).toList();
        return  new ResponseEntity<>(list,HttpStatus.OK);
    }


}
