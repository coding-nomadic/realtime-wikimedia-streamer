package com.wikimedia.repository;


import com.wikimedia.entities.WikiMediaData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WikiMediaDataRepository extends MongoRepository<WikiMediaData,String> {

}
