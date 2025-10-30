package com.reven.rips.infrastructure.repository;

import com.reven.rips.infrastructure.entity.radicacion.Errores;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErroresRepository extends MongoRepository<Errores, String> {

//    Optional<Errores> findByIdResumenRips(String idRipsSummary);

}
