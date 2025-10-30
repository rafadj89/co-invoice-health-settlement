package com.reven.rips.infrastructure.entity.resumen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "rips_resumen")
public class RipsResumen {
    @Id
    private String id;
    private Long numDocumentoIdObligado;
    private String numFactura;
    private String tipoNota;
    private String numNota;
    private String estado;
    private String estadoValidaciones;
    private String nombreArchivo;
    private String nombreBucket;
    @CreatedDate
    private LocalDateTime fechaCreacion;
    @LastModifiedDate
    private LocalDateTime fechaActualizacion;

}