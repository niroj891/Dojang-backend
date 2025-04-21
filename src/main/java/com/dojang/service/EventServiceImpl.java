package com.dojang.service;

import java.io.File;
import java.io.IOException;
import java.rmi.server.UID;
import java.util.*;

import com.dojang.dto.EventStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dojang.dao.EventDao;
import com.dojang.model.Event;
import org.springframework.web.multipart.MultipartFile;


@Service
@Transactional
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventDao eventDao;
	
	@Override
	public String createEvent(Event event, MultipartFile image) throws IOException {
		String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";
		File directory = new File(desktopPath, "dojang_app/images/event");

		if (!directory.exists()) {
			directory.mkdirs();
		}

		if (image == null || image.getOriginalFilename() == null || image.getOriginalFilename().isBlank()) {
			throw new IllegalArgumentException("Invalid file");
		}


		String savingName = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf('.'));
		String randomFileName = UUID.randomUUID().toString();
		savingName  = randomFileName+ savingName;
		File uploadFile = new File(directory, savingName);
		image.transferTo(uploadFile);
		event.setImageUrl(savingName);
		eventDao.save(event);
		return "OK";

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

	public Event getById(Integer id){
		Optional<Event> byId = eventDao.findById(id);
		Event event = byId.orElseThrow(()->new RuntimeException("Event not found"));
		return  event;
	}



	public List<EventStatusDTO> getUpcomingAndRunningEvents(Integer instructorId) {
		Date currentDate = new Date();
		List<EventStatusDTO> result = new ArrayList<>();

		// Get instructor's events
		List<Event> instructorEvents = eventDao.findByInstructorId(instructorId);

		// Categorize each event
		for (Event event : instructorEvents) {
			if (event.getEventDate().after(currentDate)) {
				// Upcoming event
				result.add(new EventStatusDTO(event, "UPCOMING"));
			} else if (event.getEndDate() != null && !event.getEndDate().before(currentDate)) {
				// Currently running event
				result.add(new EventStatusDTO(event, "RUNNING"));
			}
		}

		return result;
	}

	public long countUpcomingEvents(Integer instructorId) {
		Date currentDate = new Date();
		return eventDao.findByInstructorId(instructorId).stream()
				.filter(event -> event.getEventDate().after(currentDate))
				.count();
	}

	public long countRunningEvents(Integer instructorId) {
		Date currentDate = new Date();
		return eventDao.findByInstructorId(instructorId).stream()
				.filter(event -> event.getEndDate() != null &&
						!event.getEventDate().after(currentDate) &&
						!event.getEndDate().before(currentDate))
				.count();
	}


}
