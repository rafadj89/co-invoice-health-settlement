package com.reven.rips.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicioSaludDto {

    private String numeroContrato;
    private String modalidadContratacion;
    private String numeroSuministro;
    private String mipres;
    private String poliza;
    private String autorizacion;
    private String codigoPrestador;
    private String cobertura;
}
