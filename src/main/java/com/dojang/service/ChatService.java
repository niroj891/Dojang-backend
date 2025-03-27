package com.dojang.service;

import java.util.List;

import com.dojang.exception.ChatException;
import com.dojang.exception.UserException;
import com.dojang.model.Chat;
import com.dojang.model.User;
import com.dojang.request.GroupChatRequest;

public interface ChatService {
	
	public Chat createChat(User reqUser, User user2);
	

	public Chat findChatById(Integer chatId) throws ChatException;
	
	public List<Chat> findAllChatByUserId(Integer userId) throws UserException;
	
	
	public Chat createGroup(GroupChatRequest req,Integer reqUerId) throws UserException;
	
	public Chat addUserToGroup(Integer userId, Integer chatId) throws UserException, ChatException;
	
	public Chat renameGroup(Integer chatId,String groupName, Integer reqUserId) throws ChatException, UserException;
	
	public Chat removeFromGroup(Integer chatId,Integer userId, Integer reqUser) throws UserException,ChatException;
	
	public Chat deleteChat(Integer chatId, Integer userId) throws ChatException, UserException;

}
