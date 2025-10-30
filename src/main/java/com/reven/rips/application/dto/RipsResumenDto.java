package com.reven.rips.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RipsResumenDto {

    private String id;
    private String numDocumentoIdObligado;
    private String numFactura;
    private String tipoNota;
    private String numNota;
    private String estado;
    private String estadoValidaciones;
    private String nombreArchivo;
    private String nombreBucket;

}