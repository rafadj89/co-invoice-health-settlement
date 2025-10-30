package com.reven.rips.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuvDto {

    private String id;
    private Long nit;
    @JsonProperty("CodigoUnicoValidacion")
    private String codigoUnicoValidacion;
    @JsonProperty("NumFactura")
    private String numFactura;
    private String nombreArchivo;
    private String nombreBucket;
    private String estado;
    private String estadoValidaciones;
}
