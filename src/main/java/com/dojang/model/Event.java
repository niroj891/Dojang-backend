package com.dojang.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="event_tbl")
public class Event implements Serializable {
	private static final long serialVersionUID = 983475893475800L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="event_id")
	private int eventId;
	
	private String title;
	private String description;
	@Transient
	private MultipartFile imageFile;

	private Date eventDate;


	private Date endDate;

	private String imageUrl;
	
	@ManyToOne
	@JoinColumn(name="instructor_id")
	@JsonBackReference
	private User instructor;


	private String location;

//	@ManyToMany
//	@JoinTable(name= "registration_tbl", 
//	joinColumns = @JoinColumn(name="event_id"), 
//	inverseJoinColumns= @JoinColumn(name="user_id"))
//	private Set<User> participants = new HashSet<>()

	@OneToMany(mappedBy = "event")
	@JsonManagedReference
	private List<Registration> registrations = new ArrayList<>();
}
