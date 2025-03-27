package com.dojang.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {
	
	private Integer chatId;
	private String chatName;
	private String chatImage;
	
	private Boolean isGroup;
	
	private List<UserDto> admins= new ArrayList<>();
	
	private UserDto created_by;

	private List<UserDto> users = new ArrayList<>();
	
	private List<MessageDto> messages=new ArrayList<>();

}
