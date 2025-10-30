package com.reven.rips.infrastructure.entity.rips;

import lombok.Data;

@Data
public class RecienNacido {
    private String codPrestador;
    private String tipoDocumentoIdentificacion;
    private String numDocumentoIdentificacion; // Se usa String por si contiene caracteres no numéricos
    private String fechaNacimiento;
    private Integer edadGestacional;
    private Integer numConsultasCPrenatal;
    private String codSexoBiologico;
    private Integer peso;
    private String codDiagnosticoPrincipal;
    private String condicionDestinoUsuarioEgreso;
    private String codDiagnosticoCausaMuerte;
    private String fechaEgreso;
    private Integer consecutivo;
}