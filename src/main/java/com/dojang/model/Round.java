package com.dojang.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    private int roundNumber;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Participation winner;

    @ManyToOne
    @JoinColumn(name = "loser_id")
    private Participation loser;

    private Date endTime;
}