package com.dojang.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_table")
public class User  implements Serializable{
	
	private static final long serialVersionUID = 934839458398500L;
	@Id 
	@Column(name="user_id")	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String username;
	
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	
	private String password;
	private String gender;
	private String image;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	@Transient
	private String authority;

	
	@JsonIgnore
	@ManyToMany
	private Set<User> follower = new HashSet<>();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "follower",fetch = FetchType.LAZY)
	private Set<User> following = new HashSet<User>();

	@JsonIgnore
	@ManyToMany
	private List<Post> savedPosts = new ArrayList<>();

	@ManyToMany
	@JsonManagedReference
	private List<Post> reposts = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	@JsonManagedReference
	private List<Participation> registrations = new ArrayList<>();


	@OneToMany(mappedBy = "instructor")
	@JsonManagedReference
	private  List<Event> events = new ArrayList<>();


	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Comments> comments = new ArrayList<>();
	

}
