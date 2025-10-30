package com.reven.rips.infrastructure.repository;

import com.reven.rips.infrastructure.entity.cuv.Cuv;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuvRepository extends MongoRepository<Cuv, String> {

    Optional<Cuv> findByNitAndNumFactura(String nit, String numFactura);
}
