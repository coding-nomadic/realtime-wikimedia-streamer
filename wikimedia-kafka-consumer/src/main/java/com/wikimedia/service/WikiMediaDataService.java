package com.wikimedia.service;

import com.wikimedia.entities.WikiMediaData;
import com.wikimedia.repository.WikiMediaDataRepository;
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
