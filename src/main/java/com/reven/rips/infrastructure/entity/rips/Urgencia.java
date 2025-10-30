package com.reven.rips.infrastructure.entity.rips;

import lombok.Data;

@Data
public class Urgencia {
    private String codPrestador;
    private String fechaInicioAtencion;
    private String causaMotivoAtencion;
    private String codDiagnosticoPrincipal;
    private String codDiagnosticoPrincipalE;
    private String codDiagnosticoRelacionadoE1;
    private String codDiagnosticoRelacionadoE2;
    private String codDiagnosticoRelacionadoE3;
    private String condicionDestinoUsuarioEgreso;
    private String codDiagnosticoCausaMuerte;
    private String fechaEgreso;
    private Integer consecutivo;
}