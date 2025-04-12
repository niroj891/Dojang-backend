package com.dojang.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "_match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Enumerated(EnumType.STRING)
    private WeightCategory weightCategory;

    @ManyToOne
    @JoinColumn(name = "player1_id")
    private Participation player1;

    @ManyToOne
    @JoinColumn(name = "player2_id")
    private Participation player2;

    private int roundNumber;

    private Date matchDate;

    @OneToOne(mappedBy = "match")
    @JsonManagedReference
    private  Result result;

    @Enumerated(EnumType.STRING)
    private MatchStatus status = MatchStatus.IN_PROGRESS;
}

