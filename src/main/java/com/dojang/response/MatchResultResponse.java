package com.dojang.response;

import com.dojang.model.MatchStatus;
import com.dojang.model.WeightCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MatchResultResponse {
    private Integer matchId;
    private WeightCategory weightCategory;
    private Integer roundNumber;
    private Date matchDate;
    private MatchStatus status;
    private Integer resultId;
    private PlayerDto winner;
    private PlayerDto loser;
}

