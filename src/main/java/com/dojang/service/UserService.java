package com.dojang.service;

import java.util.List;
import java.util.Set;

import com.dojang.exception.UserException;
import com.dojang.model.User;

public interface UserService {
	
	public User registerUser(User user) throws UserException;
	public User findUserProfileByJwt(String jwt) throws UserException;

	public User findUserById(Integer id) throws UserException;
	public User findUserByEmail(String email) throws UserException;
	public String followUser(Integer reqUserId, Integer followUserId) throws UserException ;
	public List<User> findUsersByUserIds(List<Integer> userIds);
	
	public Set<User> searchUser(String query) throws UserException;
	public User updateUser(User updatedUser, User existingUser) throws UserException;
	public void deleteUser(User user);
	
	public List<User> findAll();
	
	
	

}
