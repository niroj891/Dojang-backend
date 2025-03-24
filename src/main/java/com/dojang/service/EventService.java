package com.dojang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dojang.model.Event;

public interface EventService {
	
	
	public void createEvent(Event event);
	public void deleteEvent(Event event);
	public void UpdateEvent(Event event);
	public List <Event> getAllEvents();
	public List<Event> getByName();

}
