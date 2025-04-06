package com.dojang.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="comment_tbl")
public class Comments  implements Serializable{
	
	private static final long serialVersionUID = 87654123456789L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="comment_id")
	private Integer id;
	
	@Column(name="comment_content")
	private String content;
	
	@Column(name="create_date")
	private LocalDateTime createdAt;
	
	@ManyToOne
	private User user;
	
	@ManyToMany
	private Set<User> liked= new HashSet<>();
	
	

}
