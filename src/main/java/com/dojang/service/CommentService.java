package com.dojang.service;

import com.dojang.exception.CommentException;
import com.dojang.exception.PostException;
import com.dojang.exception.UserException;
import com.dojang.model.Comments;

public interface CommentService {
	
	public Comments createComment(Comments comment,Integer postId,Integer userId) throws PostException, UserException;

	public Comments findCommentById(Integer commentId) throws CommentException;
	
	
	public Comments likeComment(Integer commentId,Integer userId) 
			throws UserException, CommentException;

}
