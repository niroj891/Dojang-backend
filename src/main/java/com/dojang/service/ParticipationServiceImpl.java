package com.dojang.service;

import com.dojang.dao.ParticipationDao;
import com.dojang.model.Participation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ParticipationServiceImpl implements ParticipationService
{
    @Autowired
    private ParticipationDao participationDao;
    @Override
    public void saveParticipation(Participation participation) {
        participationDao.save(participation);
    }

    @Override
    public List<Participation> getAll() {
        return participationDao.findAll();
    }
}
