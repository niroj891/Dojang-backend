package com.dojang.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="registration_tbl")
public class Registration implements Serializable {
	
	private static final long  serialVersionUID = 987655467889778L;
	
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="registration_id")
	private int id;
	
	private RegistrationStatus status; // Enum type are pending, Approved and rejected...
	
	private Event event;
	private User user;
	

}
