package com.dojang.service;

import java.util.List;

import com.dojang.exception.ChatException;
import com.dojang.exception.MessageException;
import com.dojang.exception.UserException;
import com.dojang.model.Message;

public interface MessageService {
	
public Message sendMessage(SendMessageRequest req) throws UserException, ChatException;
	
	public List<Message> getChatsMessages(Integer chatId) throws ChatException;
	
	public Message findMessageById(Integer messageId) throws MessageException;
	
	public String deleteMessage(Integer messageId) throws MessageException;

}
