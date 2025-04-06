package com.dojang.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dojang.dao.ChatDao;
import com.dojang.dao.MessageDao;
import com.dojang.exception.ChatException;
import com.dojang.exception.MessageException;
import com.dojang.exception.UserException;
import com.dojang.model.Chat;
import com.dojang.model.Message;
import com.dojang.model.User;
import com.dojang.request.SendMessageRequest;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private ChatDao chatDao;

	@Override
	public Message sendMessage(SendMessageRequest req) throws UserException, ChatException {
		System.out.println("send message ------- ");
		
		User user=userService.findUserById(req.getUserId());
		Chat chat=chatService.findChatById(req.getChatId());
		
		Message message=new Message();
		message.setChat(chat);
		message.setUser(user);
		message.setContent(req.getContent());
		message.setTimeStamp(LocalDateTime.now());
		message.setIsRead(false);
		message.setImage(req.getImage());
		
		
		Message savedMessage=messageDao.save(message);
		
		chat.getMessage().add(savedMessage);
		
		
		chatDao.save(chat);
		return savedMessage;
	}

	@Override
	public List<Message> getChatsMessages(Integer chatId) throws ChatException {
		Chat chat=chatService.findChatById(chatId);
		
		List<Message> messages=messageDao.findMessageByChatId(chatId);
		
		return messages;
	}

	@Override
	public Message findMessageById(Integer messageId) throws MessageException {
		Optional<Message> message =messageDao.findById(messageId);
		
		if(message.isPresent()) {
			return message.get();
		}
		throw new MessageException("message not exist with id "+messageId);
	}

	@Override
	public String deleteMessage(Integer messageId) throws MessageException {
		Message message=findMessageById(messageId);
		
		messageDao.deleteById(message.getId());
		
		return "message deleted successfully";
	}
	
	

}
