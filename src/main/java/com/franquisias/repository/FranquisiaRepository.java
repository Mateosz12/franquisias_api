package com.franquisias.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.franquisias.model.Franquisia;

@Repository
public interface FranquisiaRepository extends MongoRepository<Franquisia, String> {
}
