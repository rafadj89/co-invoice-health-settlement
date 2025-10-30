package com.reven.rips.infrastructure.repository;

import com.reven.rips.infrastructure.entity.resumen.RipsResumen;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RipsResumenRepository extends MongoRepository<RipsResumen, String> {
}
