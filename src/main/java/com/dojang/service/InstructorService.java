package com.dojang.service;

import java.util.List;

import com.dojang.model.Instructor;

public interface InstructorService {
	
	public void saveInstructor(Instructor instructor);
	public List<Instructor> getAllInstructors();
	public Instructor getInstructorById(int id);
	
	public List<Instructor> getInstructorByName(String name);
	public void updateInstructor(Instructor instructor);
	public void deleteInstructor(Instructor instructor);
	
	/*
	 * 
	 * public User registerUser(User user);
	public User findUserById(Integer id) throws Exception;
	public User findUserByEmail(String email);
	public User followUser(Integer userId1, Integer userId2) throws Exception ;
	public User updateUser(User user, Integer userId) throws Exception;
	public List<User> searchUser(String query); 
	public List<User> findAll();
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	
	

}
