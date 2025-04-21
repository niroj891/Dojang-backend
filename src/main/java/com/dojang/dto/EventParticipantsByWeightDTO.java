package com.dojang.dto;

import com.dojang.model.WeightCategory;

import java.util.Map;

public class EventParticipantsByWeightDTO {
    private Integer eventId;
    private String eventTitle;
    private Map<WeightCategory, Long> participantsByWeight;

    // Constructors, getters, and setters
    public EventParticipantsByWeightDTO() {
    }

    public EventParticipantsByWeightDTO(Integer eventId, String eventTitle, Map<WeightCategory, Long> participantsByWeight) {
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.participantsByWeight = participantsByWeight;
    }

    // Getters and Setters
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public Map<WeightCategory, Long> getParticipantsByWeight() {
        return participantsByWeight;
    }

    public void setParticipantsByWeight(Map<WeightCategory, Long> participantsByWeight) {
        this.participantsByWeight = participantsByWeight;
    }
}