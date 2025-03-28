package com.dojang.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dojang.dao.ChatDao;
import com.dojang.dao.MessageDao;
import com.dojang.exception.ChatException;
import com.dojang.exception.UserException;
import com.dojang.model.Chat;
import com.dojang.model.Message;
import com.dojang.model.User;
import com.dojang.request.GroupChatRequest;

@Service
public class ChatServiceImpl implements ChatService{
	
	@Autowired
	private ChatDao chatDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageDao messageDao;

	@Override
	public Chat createChat(Integer reqUserId, Integer userId2, boolean isGroup) throws UserException {
		User reqUser=userService.findUserById(reqUserId);
		User user2 = userService.findUserById(userId2);
		
//		System.out.println("before isChatExist");
		
		Chat isChatExist = chatDao.findSingleChatByUsersId(user2, reqUser);
		
//		System.out.println("isChatExist ----------------------------- "+isChatExist);
		
		if(isChatExist!=null) {
			return isChatExist;
		}
		
		Chat chat=new Chat();
		
		chat.setCreatedBy(reqUser);
		chat.getUsers().add(reqUser);
		chat.getUsers().add(user2);
		chat.setIsGroup(false);
		
//		System.out.println("chat ----------------------------- "+chat);
		Chat createdChat = chatDao.save(chat);
		
//		
		
		return createdChat;
	}

	
	

	@Override
	public Chat findChatById(Integer chatId) throws ChatException {
		
		Optional<Chat> chat =chatDao.findById(chatId);
		
		if(chat.isPresent()) {
			return chat.get();
		}
		throw new ChatException("Chat not exist with id "+chatId);
	}

	@Override
	public List<Chat> findAllChatByUserId(Integer userId) throws UserException {
		
		
		
		List<Chat> chats=chatDao.findChatByUserId(userId);
		
	
		
		return chats;
	}
	
	@Override
	public Chat deleteChat(Integer chatId, Integer userId) throws ChatException, UserException {
		
		User user=userService.findUserById(userId);
		Chat chat=findChatById(chatId);
		System.out.println(chatId+"++++++++++");
		
		if((chat.getCreatedBy().getId().equals(user.getId())) && !chat.getIsGroup() ) {
			
			List<Message> messages = chat.getMessage();
	        for (Message message : messages) {
	            messageDao.delete(message);
	        }
	        chatDao.deleteById(chat.getChatId());
			
			return chat;
		}
		
		throw new ChatException("you dont have access to delete this chat");
	}




	@Override
	public Chat createGroup(GroupChatRequest req,Integer reqUserId) throws UserException {
		
		User reqUser=userService.findUserById(reqUserId);
		
		Chat chat=new Chat();
		
		chat.setCreatedBy(reqUser);
		chat.getUsers().add(reqUser);
		
		for(Integer userId: req.getUserIds()) {
			User user =userService.findUserById(userId);
			if(user!=null)chat.getUsers().add(user);
		}
		
		chat.setChatName(req.getChatName());
		chat.setChatImage(req.getChatImage());
		chat.setIsGroup(true);
		
		
		return chatDao.save(chat);
		
	}


	@Override
	public Chat addUserToGroup(Integer userId, Integer chatId) throws UserException, ChatException {
		
		Chat chat =findChatById(chatId);
		User user=userService.findUserById(userId);
		
		chat.getUsers().add(user);
		
		
		Chat updatedChat=chatDao.save(chat);
		
		return updatedChat;
	}




	@Override
	public Chat renameGroup(Integer chatId, String groupName, Integer reqUserId) throws ChatException, UserException {
		
		Chat chat=findChatById(chatId);
		User user=userService.findUserById(reqUserId);
		
		
		if(chat.getUsers().contains(user))
		chat.setChatName(groupName);
		
		return chatDao.save(chat);
	}

	@Override
	public Chat removeFromGroup(Integer chatId, Integer userId, Integer reqUserId) throws UserException, ChatException {
		Chat chat=findChatById(chatId);
		System.out.println(chat+"------->");
		User user=userService.findUserById(userId);
		
		User reqUser=userService.findUserById(reqUserId);
		
		if(user.getId().equals(reqUser.getId()) ) {
			chat.getUsers().remove(reqUser);
		}
		
		return chat;
	}
}
