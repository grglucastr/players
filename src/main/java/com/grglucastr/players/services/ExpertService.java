package com.grglucastr.players.services;

import com.grglucastr.players.entities.Expert;
import com.grglucastr.players.repositories.ExpertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpertService {

    private ExpertRepository expertRepository;

    @Autowired
    public ExpertService(ExpertRepository expertRepository) {
        this.expertRepository = expertRepository;
    }

    public Expert save(Expert expert){
        return expertRepository.save(expert);
    }

}