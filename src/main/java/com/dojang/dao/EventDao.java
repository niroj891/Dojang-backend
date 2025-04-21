package com.dojang.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dojang.model.Event;

@Repository
public interface EventDao extends JpaRepository<Event, Integer> {
	
	Optional<Event> findById(Integer id);


	// Find upcoming events (eventDate > current date)
	@Query("SELECT e FROM Event e WHERE e.eventDate > :currentDate ORDER BY e.eventDate ASC")
	List<Event> findUpcomingEvents(@Param("currentDate") Date currentDate);

	// Find currently running events (eventDate <= current date AND endDate >= current date)
	@Query("SELECT e FROM Event e WHERE e.eventDate <= :currentDate AND e.endDate >= :currentDate ORDER BY e.endDate ASC")
	List<Event> findRunningEvents(@Param("currentDate") Date currentDate);

	// Find events created by a specific instructor
	List<Event> findByInstructorId(Integer instructorId);
}
