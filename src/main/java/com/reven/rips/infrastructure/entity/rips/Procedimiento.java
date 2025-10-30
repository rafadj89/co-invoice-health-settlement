package com.reven.rips.infrastructure.entity.rips;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Procedimiento {
    private String codPrestador;
    private String fechaInicioAtencion;
    private String idMIPRES;
    private String numAutorizacion;
    private String codProcedimiento;
    private String viaIngresoServicioSalud;
    private String modalidadGrupoServicioTecSal;
    private String grupoServicios;
    private Integer codServicio;
    private String finalidadTecnologiaSalud;
    private String tipoDocumentoIdentificacion;
    private String numDocumentoIdentificacion;
    private String codDiagnosticoPrincipal;
    private String codDiagnosticoRelacionado;
    private String codComplicacion;
    private BigDecimal vrServicio;
    private String conceptoRecaudo;
    private BigDecimal valorPagoModerador;
    private String numFEVPagoModerador;
    private Integer consecutivo;
}