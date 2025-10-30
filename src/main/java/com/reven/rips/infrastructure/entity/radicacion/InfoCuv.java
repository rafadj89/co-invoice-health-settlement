package com.reven.rips.infrastructure.entity.radicacion;

import com.reven.rips.infrastructure.entity.cuv.Cuv;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InfoCuv {
    private EstadoValidacionArchivo validacion = new EstadoValidacionArchivo("PENDIENTE");
    private List<Cuv> cuvs = new ArrayList();
}