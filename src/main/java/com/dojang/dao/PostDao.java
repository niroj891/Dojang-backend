package com.dojang.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dojang.model.Post;

@Repository
public interface PostDao extends JpaRepository <Post, Integer>{
	
	@Query("select p from Post p where p.user.id=?1")
	public List<Post> findByUserId (Integer userId);

    @Query("SELECT p FROM Post p WHERE p.user.id IN :users ORDER BY p.createdAt DESC")
    public List<Post> findAllPostByUserIdsSortedByDateDesc(@Param("users") List<Integer> userIds);
    
    List<Post> findAllByOrderByCreatedAtDesc();


}
