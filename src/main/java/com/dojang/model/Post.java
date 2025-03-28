package com.dojang.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
	
	private String image;
	
	private String video;
	
	private String location;
	
	private LocalDateTime createdAt;
	
	
	@ManyToOne
	private User user;
	
	@OneToMany
	private List<Comments> comments=new ArrayList<Comments>();
	
	@ManyToMany
	private Set<User> liked= new HashSet<>(); 
	
	
}
