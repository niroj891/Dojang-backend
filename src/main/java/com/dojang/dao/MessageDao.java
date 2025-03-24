package com.dojang.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dojang.model.Message;

@Repository
public interface MessageDao extends JpaRepository<Message, Integer>{
	
	@Query("select m from Message m join m.chat c where c.id=:chatId")
	public List<Message> findMessageByChatId(@Param("chatId") Integer chatId);

}
