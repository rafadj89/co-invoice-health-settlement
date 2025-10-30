package com.reven.rips.infrastructure.adapter;


import com.reven.rips.infrastructure.domain.FacturaService;
import com.reven.rips.infrastructure.entity.factura.XmlData;
import com.reven.rips.infrastructure.repository.FacturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de Factura.
 * Contiene la lógica para interactuar con el repositorio.
 */
@Service
@RequiredArgsConstructor
public class FacturaServiceImpl implements FacturaService {

    private final FacturaRepository facturaRepository;

    @Override
    public XmlData save(XmlData invoice) {
        return facturaRepository.save(invoice);
    }
}