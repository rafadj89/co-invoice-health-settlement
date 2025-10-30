package com.reven.rips.infrastructure.domain;

import com.reven.rips.infrastructure.entity.factura.XmlData;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define el contrato para los servicios relacionados con la entidad Factura.
 * Estas son las operaciones de negocio principales.
 */
public interface FacturaService {

    XmlData save(XmlData invoice);


}