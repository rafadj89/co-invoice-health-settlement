package com.reven.rips.infrastructure.entity.radicacion;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class EstadoValidacionArchivo {
    private String estado; // PENDIENTE, EXITOSO, FALLIDO
    private List<Errores> errores;
    private LocalDateTime fechaValidacion;
    private String idArchivo;

    public EstadoValidacionArchivo(String estado) {
        this.estado = estado;
    }
}