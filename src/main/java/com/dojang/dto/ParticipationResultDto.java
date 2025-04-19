package com.dojang.dto;

import com.dojang.model.Participation;
import lombok.AllArgsConstructor;
import lombok.Getter;

// DTO to hold the result
@Getter
@AllArgsConstructor
public  class ParticipationResultDto {
    private Participation participation;
    private String position;
    private String event;
    private String instructor;
}