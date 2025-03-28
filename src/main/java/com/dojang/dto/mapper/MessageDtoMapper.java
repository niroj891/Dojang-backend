package com.dojang.dto.mapper;

import java.util.ArrayList;
import java.util.List;


import com.dojang.dto.MessageDto;
import com.dojang.dto.UserDto;
import com.dojang.model.Message;

public class MessageDtoMapper {
	
public static MessageDto toMessageDto(Message message) {
		
//		ChatDto chatDto=ChatDtoMapper.toChatDto(message.getChat());
		UserDto userDto=UserDtoMapper.userDTO(message.getUser());
		
		MessageDto messageDto=new MessageDto();
		messageDto.setId(message.getId());
//		messageDto.setChat(chatDto);
		messageDto.setContent(message.getContent());
		messageDto.setIsRead(message.getIsRead());
		messageDto.setTimeStamp(message.getTimeStamp());
		messageDto.setUser(userDto);
		messageDto.setImage(message.getImage());
		
		return messageDto;
	}
	
	public static List<MessageDto> toMessageDtos(List<Message> messages){
		
		List<MessageDto> messageDtos=new ArrayList<>();
		
		for(Message message : messages) {
			MessageDto messageDto=toMessageDto(message);
			messageDtos.add(messageDto);
		}
		
		return messageDtos;
	}


}
