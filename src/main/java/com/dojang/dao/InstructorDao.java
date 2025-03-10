package com.dojang.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dojang.model.Instructor;

@Repository
public interface InstructorDao extends JpaRepository <Instructor ,Integer> {
	
	//public Instructor findById(Instructor instructor);
	public List<Instructor> findByName(String name);
	
	
	
	
}
