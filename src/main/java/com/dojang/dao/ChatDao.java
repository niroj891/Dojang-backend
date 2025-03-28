package com.dojang.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dojang.model.Chat;
import com.dojang.model.User;

@Repository
public interface ChatDao extends JpaRepository<Chat, Integer>{
	
	@Query("Select c from Chat c join c.users u where u.id =: userId")
	public List<Chat> findChatByUserId(Integer userId);
	
	@Query("Select c from Chat c Where c.isGroup = false and :user Member of c.users And :reqUser Member of c.users ")
	public Chat findSingleChatByUsersId(@Param("user")
	User user, @Param("reqUser")User reqUser);
}
