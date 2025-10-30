package com.reven.rips.infrastructure.domain;

import com.reven.rips.infrastructure.entity.cuv.Cuv;

import java.util.Optional;

public interface CuvServices {

    Cuv save(Cuv cuv);

    Optional<Cuv> findByNitAndNumFactura(String nit, String numFactura);

}
