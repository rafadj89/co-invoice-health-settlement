package com.reven.rips.infrastructure.repository;

import com.reven.rips.infrastructure.entity.rips.Rips;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RipsRepository extends MongoRepository<Rips, String> {
}