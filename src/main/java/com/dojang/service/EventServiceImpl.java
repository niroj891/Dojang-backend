package com.dojang.service;

import java.io.File;
import java.io.IOException;
import java.rmi.server.UID;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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


}
