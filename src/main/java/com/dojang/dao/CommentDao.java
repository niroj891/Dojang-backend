package com.dojang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dojang.model.Comments;

@Repository
public interface CommentDao extends JpaRepository<Comments, Integer> {

}
