package com.dojang.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class User {
	
	@Id 
	@Column(name="user_id")
	
	private int id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String password;
	private String gender;
	
	private List<Integer> followers = new ArrayList<>();
	private List<Integer> followings = new ArrayList<>();
	

}
