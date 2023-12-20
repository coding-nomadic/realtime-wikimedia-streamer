package com.wikimedia.unit.controller;

import com.wikimedia.unit.constants.WikiMediaConstants;
import com.wikimedia.unit.entities.WikiMediaData;
import com.wikimedia.unit.repository.WikiMediaDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wikimedia")
public class WikiMediaDataRestController {

    private final WikiMediaDataRepository repository;

    @Autowired
    public WikiMediaDataRestController(WikiMediaDataRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<Page<WikiMediaData>> getAllMediaDataBySize(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "30") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(WikiMediaConstants.ID).ascending());
        final Page<WikiMediaData> entities = repository.findAll(pageable);
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WikiMediaData> getMediaDataById(@PathVariable String id) {
        WikiMediaData entity = repository.findById(id).orElse(null);
        return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
    }
}
