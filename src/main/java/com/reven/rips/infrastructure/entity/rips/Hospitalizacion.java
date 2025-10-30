package com.reven.rips.infrastructure.entity.rips;

import lombok.Data;

@Data
public class Hospitalizacion {
    private String codPrestador;
    private String viaIngresoServicioSalud;
    private String fechaInicioAtencion;
    private String numAutorizacion;
    private String causaMotivoAtencion;
    private String codDiagnosticoPrincipal;
    private String codDiagnosticoPrincipalE;
    private String codDiagnosticoRelacionadoE1;
    private String codDiagnosticoRelacionadoE2;
    private String codDiagnosticoRelacionadoE3;
    private String codComplicacion;
    private String condicionDestinoUsuarioEgreso;
    private String codDiagnosticoCausaMuerte;
    private String fechaEgreso;
    private Integer consecutivo;
}