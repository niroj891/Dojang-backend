package com.dojang.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer resultId;


    @ManyToOne
    @JsonBackReference
    private Participation winner;

    @ManyToOne
    @JsonBackReference
    private Participation losser;

    @OneToOne
    @JsonBackReference
    private  Match match;
}
