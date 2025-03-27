package com.dojang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dojang.exception.ChatException;
import com.dojang.exception.MessageException;
import com.dojang.exception.UserException;
import com.dojang.model.Message;

@Service
public class MessageServiceImpl implements MessageService{

	@Override
	public Message sendMessage(SendMessageRequest req) throws UserException, ChatException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getChatsMessages(Integer chatId) throws ChatException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message findMessageById(Integer messageId) throws MessageException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteMessage(Integer messageId) throws MessageException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
