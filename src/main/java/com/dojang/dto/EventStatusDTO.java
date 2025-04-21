package com.dojang.dto;

import com.dojang.model.Event;
import lombok.Data;

@Data
public class EventStatusDTO {
    private Event event;
    private String status; // "UPCOMING" or "RUNNING"

    public EventStatusDTO(Event event, String status) {
        this.event = event;
        this.status = status;
    }
}