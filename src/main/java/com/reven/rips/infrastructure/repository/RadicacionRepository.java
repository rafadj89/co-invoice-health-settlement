package com.reven.rips.infrastructure.repository;

import com.reven.rips.infrastructure.entity.radicacion.Radicacion;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RadicacionRepository extends MongoRepository<Radicacion, String> {

    Radicacion findByNitAndNumeroFactura(Long nit, String invoicesNumber);
}
