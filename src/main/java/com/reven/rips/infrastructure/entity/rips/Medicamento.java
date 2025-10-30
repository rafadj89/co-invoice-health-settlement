package com.reven.rips.infrastructure.entity.rips;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Medicamento {
    private String codPrestador;
    private String numAutorizacion;
    private String idMIPRES;
    private String fechaDispensAdmon;
    private String codDiagnosticoPrincipal;
    private String codDiagnosticoRelacionado;
    private String tipoMedicamento;
    private String codTecnologiaSalud;
    private Integer cantidadMedicamento;
    private String nomTecnologiaSalud;
    private Integer concentracionMedicamento;
    private Integer unidadMedida;
    private String formaFarmaceutica;
    private Integer unidadMinDispensa;
    private Integer diasTratamiento;
    private String tipoDocumentoIdentificacion;
    private String numDocumentoIdentificacion;
    private BigDecimal vrUnitMedicamento;
    private BigDecimal vrServicio;
    private String conceptoRecaudo;
    private BigDecimal valorPagoModerador;
    private String numFEVPagoModerador;
    private Integer consecutivo;
}