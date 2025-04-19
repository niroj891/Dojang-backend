package com.dojang.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="post_tbl")
public class Post implements Serializable{
	
	private static final long serialVersionUID = 899878384747399L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="post_id")
	private Integer id;
	
	private String caption;

	@Transient
	private MultipartFile imageFile;
	
	private String image;
	
	private String video;
	
	private String location;
	
	private LocalDateTime createdAt;

	@Transient
	private  MultipartFile videoFile;
	
	
	@ManyToOne
	@JsonBackReference
	private User user;
	
	@OneToMany(mappedBy = "post")
	@JsonManagedReference
	private List<Comments> comments=new ArrayList<Comments>();
	
	@ManyToMany
	@JsonIgnore
	private Set<User> liked= new HashSet<>();


	
	
}
