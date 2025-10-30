package com.reven.rips.infrastructure.entity.rips;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Consulta {
    private String codPrestador;
    private String fechaInicioAtencion;
    private String numAutorizacion;
    private String codConsulta;
    private Integer codServicio;
    private String grupoServicios;
    private String modalidadGrupoServicioTecSal;
    private String finalidadTecnologiaSalud;
    private String causaMotivoAtencion;
    private String codDiagnosticoPrincipal;
    private String codDiagnosticoRelacionado1;
    private String codDiagnosticoRelacionado2;
    private String codDiagnosticoRelacionado3;
    private String tipoDiagnosticoPrincipal;
    private String tipoDocumentoIdentificacion;
    private String numDocumentoIdentificacion;
    private BigDecimal vrServicio;
    private String conceptoRecaudo;
    private BigDecimal valorPagoModerador;
    private String numFEVPagoModerador;
    private Integer consecutivo;
}