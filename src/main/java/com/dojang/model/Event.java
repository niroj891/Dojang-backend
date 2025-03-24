package com.dojang.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
	private String eventImage;
	@Transient 
	private MultipartFile imageFile;
	private LocalDateTime eventDate;
	
	@ManyToOne
	@JoinColumn(name="instructor_id")
	private Instructor instructor;
	
	@ManyToMany
	@JoinTable(name= "registration_tbl", 
	joinColumns = @JoinColumn(name="event_id"), 
	inverseJoinColumns= @JoinColumn(name="user_id"))
	
	private Set<User> participants = new HashSet<>();
	
	
}
