package com.reven.rips.infrastructure.entity.cuv;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reven.rips.infrastructure.entity.radicacion.Errores;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Document(collection = "cuv")
public class Cuv {

    @Id
    private String id;
    private Long nit;
    @JsonProperty("CodigoUnicoValidacion")
    private String codigoUnicoValidacion;
    @JsonProperty("NumFactura")
    private String numFactura;
    private String nombreArchivo;
    private String nombreBucket;
    private String estado;
    private String estadoValidaciones;
    @CreatedDate
    private LocalDateTime fechaCreacion;
    @LastModifiedDate
    private LocalDateTime fechaActualizacion;
}
