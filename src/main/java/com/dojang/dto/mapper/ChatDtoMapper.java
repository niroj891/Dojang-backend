package com.dojang.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.dojang.dto.ChatDto;
import com.dojang.dto.MessageDto;
import com.dojang.dto.UserDto;
import com.dojang.model.Chat;
import com.dojang.model.Post;
import com.dojang.model.User;
import com.dojang.utils.ChatUtil;

public class ChatDtoMapper {
	
	public static ChatDto toChatDto(Chat chat, User user) {

		UserDto userDto = UserDtoMapper.userDTO(chat.getCreatedBy());
		List<MessageDto> messageDtos = MessageDtoMapper.toMessageDtos(chat.getMessage());
		List<UserDto> userDtos = UserDtoMapper.userDTOS(chat.getUsers());

		ChatDto chatDto = new ChatDto();
		chatDto.setChatId(chat.getChatId());
		chatDto.setChatImage(chat.getChatImage());
		chatDto.setChatName(ChatUtil.chatName(chat, user));
		chatDto.setCreated_by(userDto);
		chatDto.setIsGroup(chat.getIsGroup());
		chatDto.setMessages(messageDtos);
		chatDto.setUsers(userDtos);

		return chatDto;
	}

	public static List<ChatDto> toChatDtos(List<Chat> chats, User user) {

		List<ChatDto> chatDtos = new ArrayList<>();

		for (Chat chat : chats) {
			ChatDto chatDto = toChatDto(chat, user);
			chatDtos.add(chatDto);
		}

		return chatDtos;
	}

}
