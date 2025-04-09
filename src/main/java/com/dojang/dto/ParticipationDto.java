package com.dojang.dto;

import com.dojang.model.PlayerStatus;
import com.dojang.model.WeightCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipationDto {

    private String firstName;

    private String lastName;

    private String dojangName;

    private WeightCategory weightCategory;  // Fin, Fly, Bantam and Feather weight

    private PlayerStatus playerStatus = PlayerStatus.NOTOUT;
}
