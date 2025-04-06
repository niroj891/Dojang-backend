package com.dojang.dto;

import java.time.LocalDateTime;

import org.hibernate.mapping.List;

import com.dojang.model.Chat;
import com.dojang.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
	
	private int id;
	
	private String content;
	private String image;
	
	private LocalDateTime timeStamp;
	
	private Boolean isRead;
	private UserDto user;
	
	private ChatDto chat;
	

}
