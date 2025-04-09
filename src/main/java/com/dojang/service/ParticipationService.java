package com.dojang.service;

import com.dojang.model.Participation;

import java.util.List;

public interface ParticipationService {

     void saveParticipation(Participation participation);

     List<Participation> getAll();
}
