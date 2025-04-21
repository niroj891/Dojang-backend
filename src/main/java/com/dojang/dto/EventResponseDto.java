package com.dojang.dto;

import com.dojang.model.Event;
import com.dojang.model.Participation;
import com.dojang.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
public class EventResponseDto {

    private Integer eventId;

    private String title;
    private String description;

    private Date eventDate;


    private Date endDate;

    private String imageUrl;


    private User instructor;


    private String location;

    private List<Participation> registrations;

    public EventResponseDto(Event event){
        this.eventId = event.getEventId();
        this.title = event.getTitle();
        this.description = event.getDescription();
        this.eventDate = event.getEndDate();
        this.imageUrl = event.getImageUrl();
        this.instructor = event.getInstructor();
        this.location = event.getLocation();
        this.registrations = event.getRegistrations();

    }

}
