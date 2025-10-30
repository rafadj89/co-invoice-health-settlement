package com.reven.rips.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaDto {

        private String id;
        private String nitPrestador;
        private String numeroFactura;
        private String prestador;
        private BigDecimal total;
        private BigDecimal impuestos;
        private BigDecimal copagos;
        private BigDecimal valorNeto;
        private LocalDateTime fechaExpedicion;
        private ServicioSaludDto servicioSalud;

}
