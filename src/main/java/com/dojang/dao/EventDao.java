package com.dojang.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dojang.model.Event;

@Repository
public interface EventDao extends JpaRepository<Event, Integer> {
	
	List<Event> findByInstructorId(Integer id);
	Optional<Event> findById(Integer id);

}
