package com.reven.rips.infrastructure.entity.radicacion;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "radicacion_historial")

public class RadicacionHistorial {
    @Id
    private String id;

    private String radicacionId; 
    
    private String tipoDocumento; // "RIPS", "CUV", "FACTURA", "RADICACIOn"
    private String idArchivo;
    private String estado; // FALLIDO, EXITOSO
    private List<Errores> errores;
    private LocalDateTime fechaEvento;
}