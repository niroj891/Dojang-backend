package com.dojang.service;

import java.io.IOException;
import java.util.List;

import com.dojang.model.Event;
import org.springframework.web.multipart.MultipartFile;

public interface EventService {
	
	
	public String createEvent(Event event, MultipartFile multipartFile) throws IOException;
	public void deleteEvent(Event event);
	public void UpdateEvent(Event event);
	public List <Event> getAllEvents();
	public List<Event> getByName();
	public Event getById(Integer id);
}
