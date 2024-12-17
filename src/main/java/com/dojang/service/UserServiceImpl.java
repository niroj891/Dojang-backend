package com.dojang.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dojang.dao.UserDao;
import com.dojang.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User registerUser(User user) {
		
		return userDao.save(user);
	}

	@Override
	public User findUserById(Integer id) throws Exception{
		
		Optional<User> user = userDao.findById(id);
		
		if(user.isPresent()) {
			return user.get();
		}
		throw new Exception("user not exist with user id" + id);
		
	}
	

	@Override
	public User findUserByEmail(String email) {
		User user = userDao.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer userId1, Integer userId2) throws Exception {
		User user1 = findUserById(userId1);
		User user2 = findUserById(userId2);
		
		user2.getFollowers().add(user1.getId());
		user1.getFollowers().add(user2.getId());
		
		userDao.save(user1);
		userDao.save(user2);
		return user1;
	}

	@Override
	public User updateUser(User user, Integer userId) throws Exception{
		
		Optional<User> user1 = userDao.findById(userId);
		
		if (user1.isEmpty()) {
			throw new Exception("user not found with user id" + userId);
		}
		
		User oldUser = user1.get();
		
		if(user.getFirstName()!=null) {
			oldUser.setFirstName(user.getFirstName());
		}
		
		if(user.getLastName()!=null) {
			oldUser.setLastName(user.getLastName());
		}
		
		if(user.getEmail()!=null) {
			oldUser.setEmail(user.getEmail());
		}
		
		if(user.getPhoneNumber()!=null) {
			oldUser.setPhoneNumber(user.getPhoneNumber());
		}
		
		User updatedUser = userDao.save(oldUser);
		return updatedUser;
	}

	@Override
	public List<User> searchUser(String query) {
		// TODO Auto-generated method stub
		return  userDao.searchUser(query);
		  
	}

	@Override
	public List<User> findAll() {
		
		return userDao.findAll();
	}
	
	

}
