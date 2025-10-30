package com.reven.rips.infrastructure.domain;

import com.reven.rips.infrastructure.entity.resumen.RipsResumen;

import java.util.List;
import java.util.Optional;

public interface RipsResumenService {

    RipsResumen save(RipsResumen ripsSummary);

    List<RipsResumen> findAll();

    Optional<RipsResumen> findById(String id);

}
