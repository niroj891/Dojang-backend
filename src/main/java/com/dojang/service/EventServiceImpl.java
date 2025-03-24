package com.dojang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dojang.dao.EventDao;
import com.dojang.model.Event;



@Service
@Transactional
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventDao eventDao;
	
	
	@Override
	public void createEvent(Event event) {
		eventDao.save(event);
		
	}

	@Override
	public void deleteEvent(Event event) {
		eventDao.delete(event);
		
	}

	@Override
	public void UpdateEvent(Event event) {
		eventDao.save(event);
		
	}

	@Override
	public List<Event> getAllEvents() {
		return eventDao.findAll();
	}

	@Override
	public List<Event> getByName() {
		// TODO Auto-generated method stub
		return null;
	}

}
