package com.dojang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dojang.dao.ChatDao;
import com.dojang.dao.MessageDao;
import com.dojang.exception.ChatException;
import com.dojang.model.Chat;
import com.dojang.model.User;

@Service
public class ChatServiceImpl implements ChatService{
	
	@Autowired
	private ChatDao chatDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageDao messageDao;

	@Override
	public Chat createChat(User reqUser, User user2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chat findChatById(Integer chatId) throws ChatException {
		
		return null;
	}

	@Override
	public List<Chat> findAllChatByUserId(Integer userId) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chat createGroup(GroupChatRequest req, Integer reqUerId) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chat addUserToGroup(Integer userId, Integer chatId) throws UserException, ChatException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chat renameGroup(Integer chatId, String groupName, Integer reqUserId) throws ChatException, UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chat removeFromGroup(Integer chatId, Integer userId, Integer reqUser) throws UserException, ChatException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chat deleteChat(Integer chatId, Integer userId) throws ChatException, UserException {
		// TODO Auto-generated method stub
		return null;
	}

}
