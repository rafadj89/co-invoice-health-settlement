package com.reven.rips.infrastructure.entity.radicacion;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Document
public class Radicacion {
    
    @Id
    private String id;
    private String numeroRadicacion;
    private Long nit;
    private String nombrePrestador;
    
    private String numeroFactura;
    private BigDecimal valor;

    private String estadoGeneral; // EN_PROCESO, PENDIENTE_CORRECCION, VALIDADO, RECHAZADO

    private InfoFactura factura;
    private InfoRips rips;
    private InfoCuv cuv;
    // private InfoSoportes soportes;

    private List<Errores> erroresGlobales;

    @CreatedDate
    private LocalDateTime fechaCreacion;
    @LastModifiedDate
    private LocalDateTime fechaActualizacion;
}