package com.dojang.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable{
	
	private static final long serialVersionUID = 8765431234567L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="message_id")
	private Integer id;
	
	private String Content;
	private String image;
	
	private LocalDateTime timeStamp;
	
	private Boolean isRead;
	
	@ManyToOne
	private User user;
	
	@ManyToOne // Join column to chat_id
	private Chat chat;
	
	

}
