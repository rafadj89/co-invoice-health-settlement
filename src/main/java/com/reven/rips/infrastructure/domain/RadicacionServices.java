package com.reven.rips.infrastructure.domain;

import com.reven.rips.infrastructure.entity.radicacion.Radicacion;

import java.util.List;

public interface RadicacionServices {

    void save(Radicacion radication);

    List<Radicacion> findAll();

    Radicacion findById(String id);

    Radicacion finByNitAndFactura(Long nit, String invoicesNumber);
}
