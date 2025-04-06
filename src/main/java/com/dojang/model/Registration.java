package com.dojang.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Registration {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;

	private String lastName;

	private String dojangName;

	private WeightCategory weightCategory;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "event_id")
	private  Event event;


}
