package com.dojang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dojang.dao.InstructorDao;
import com.dojang.model.Instructor;



@Service
@Transactional
public class InstructorServiceImpl implements InstructorService{
	
	@Autowired
	private InstructorDao instructorDao;

	@Override
	public void saveInstructor(Instructor instructor) {
		instructorDao.save(instructor);
		
	}

	@Override
	public List<Instructor> getAllInstructors() {
		// TODO Auto-generated method stub
		return instructorDao.findAll();
	}

	@Override
	public Instructor getInstructorById(int id) {
		// TODO Auto-generated method stub
		return instructorDao.findById(id).get();
	}

	@Override
	public List<Instructor> getInstructorByName(String name) {
		// TODO Auto-generated method stub
		return instructorDao.findByName(name);
	}

	@Override
	public void updateInstructor(Instructor instructor) {
		// TODO Auto-generated method stub
		instructorDao.save(instructor);
		
	}

	@Override
	public void deleteInstructor(Instructor instructor) {
		instructorDao.delete(instructor);
		
	}

	

}
