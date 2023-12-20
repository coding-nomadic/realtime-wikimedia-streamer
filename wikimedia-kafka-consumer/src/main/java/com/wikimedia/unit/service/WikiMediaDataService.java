package com.wikimedia.unit.service;

import com.wikimedia.unit.entities.WikiMediaData;
import com.wikimedia.unit.repository.WikiMediaDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WikiMediaDataService {

    private WikiMediaDataRepository wikiMediaDataRepository;

    @Autowired
    public WikiMediaDataService(WikiMediaDataRepository repository) {
        this.wikiMediaDataRepository = repository;
    }

    public void insertData(WikiMediaData myEntity) {
        wikiMediaDataRepository.save(myEntity);
    }
}
