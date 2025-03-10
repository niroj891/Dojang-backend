package com.dojang.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dojang.model.Event;

public interface EventDao extends JpaRepository<Event, Integer> {

}
