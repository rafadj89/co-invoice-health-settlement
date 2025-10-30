package com.reven.rips.infrastructure.entity.rips;

import lombok.Data;

@Data
public class UsuarioRips {
    private String tipoDocumentoIdentificacion;
    private String numDocumentoIdentificacion;
    private String tipoUsuario;
    private String fechaNacimiento; // Puedes usar String o convertir a Date
    private String codSexo;
    private String codPaisResidencia;
    private String codPaisOrigen;
    private String codMunicipioResidencia;
    private String codZonaTerritorialResidencia;
    private String incapacidad;
    private Integer consecutivo;
    private Servicios servicios;
}