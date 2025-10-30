package com.reven.rips.infrastructure.entity.rips;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "rips")
public class Rips {
    @Id
    private String id;
    private String idResumen;
    private String numDocumentoIdObligado;
    private String numFactura;
    private String tipoNota;
    private String numNota;
    private List<UsuarioRips> usuarios;
    @CreatedDate
    private LocalDateTime fechaCreacion;
    @LastModifiedDate
    private LocalDateTime fechaActualizacion;
}