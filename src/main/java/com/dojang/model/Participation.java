package com.dojang.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
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
}
