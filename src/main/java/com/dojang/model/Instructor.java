package com.dojang.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="instructor_tbl")
public class Instructor implements Serializable{
	
	private static final long serialVersionUID = 98765342564567878L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="instructor_id")
	private int id;
	
	private String name;
	private String email;
	
	@Transient
	private MultipartFile imageFile;
	
	@Column(name="instructor_grade")
	private  Rank rankOfInstructor;
	
	
	private String image;
	
	
	
	

}
