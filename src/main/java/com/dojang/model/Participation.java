package com.dojang.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Participation {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;

	private String firstName;

	private String lastName;

	private String dojangName;

	@Enumerated(EnumType.STRING)
	private WeightCategory weightCategory;  // Fin, Fly, Bantam and Feather weight

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "event_id")
	private  Event event;

	@Enumerated(EnumType.STRING)
	private PlayerStatus playerStatus = PlayerStatus.NOTOUT;


	@OneToMany(mappedBy = "winner")
	@JsonManagedReference
	private List<Result> winnerResults = new ArrayList<>();

	@OneToMany(mappedBy = "losser")
	@JsonManagedReference
	private List<Result> losserResults = new ArrayList<>();
}
