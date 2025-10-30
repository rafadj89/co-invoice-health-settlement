package com.reven.rips.infrastructure.entity.radicacion;

import com.reven.rips.infrastructure.entity.resumen.RipsResumen;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InfoRips {
    private EstadoValidacionArchivo validacion = new EstadoValidacionArchivo("PENDIENTE");
    private List<RipsResumen> rips = new ArrayList<RipsResumen>();
    private List<RipsResumen> notas = new ArrayList<RipsResumen>();;
}