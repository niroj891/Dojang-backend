package com.dojang.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dojang.model.Chat;
import com.dojang.model.User;

public interface ChatDao extends JpaRepository<Chat, Integer>{
	
	@Query("Select c from Chat c join c.users u where u.id =: userId")
	public List<Chat> findChatByUserId(Integer userId);
	
	@Query("Select c from Chat c Where c.is_group = flase and :user Member fo c.users And :reqUser Member of c.users ")
	public Chat findSingleChatByUsersId(@Param("user")
	User user, @Param("reqUser")User reqUser);
}
