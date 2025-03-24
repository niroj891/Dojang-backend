package com.dojang.service;

import java.util.List;

import com.dojang.model.User;

public interface UserService {
	
	public User registerUser(User user);
	public User findUserById(Integer id) throws Exception;
	public User findUserByEmail(String email);
	public User followUser(Integer userId1, Integer userId2) throws Exception ;
	public User updateUser(User user, Integer userId) throws Exception;
	public void deleteUser(User user);
	public List<User> searchUser(String query); 
	public List<User> findAll();
	
	
	

}
