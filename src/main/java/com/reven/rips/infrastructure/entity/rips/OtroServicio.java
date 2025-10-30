package com.reven.rips.infrastructure.entity.rips;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OtroServicio {
    private String codPrestador;
    private String numAutorizacion;
    private String idMIPRES;
    private String fechaSuministroTecnologia;
    private String tipoOS;
    private String codTecnologiaSalud;
    private String nomTecnologiaSalud;
    private Integer cantidadOS;
    private String tipoDocumentoIdentificacion;
    private String numDocumentoIdentificacion;
    private BigDecimal vrUnitOS;
    private BigDecimal vrServicio;
    private String conceptoRecaudo;
    private BigDecimal valorPagoModerador;
    private String numFEVPagoModerador;
    private Integer consecutivo;
}