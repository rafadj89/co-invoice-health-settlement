package com.reven.rips.infrastructure.repository;

import com.reven.rips.infrastructure.entity.factura.XmlData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FacturaRepository extends MongoRepository<XmlData, String> {

   // Optional<XmlData> findByNitPrestadorAndNumeroFactura(String nitPrestador, String numeroFactura);

}