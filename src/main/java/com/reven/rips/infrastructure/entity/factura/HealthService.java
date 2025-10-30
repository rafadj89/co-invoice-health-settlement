package com.reven.rips.infrastructure.entity.factura;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Data

public class HealthService {


    @Column(name = "cobertura")
    private String coverage;

    @Column(name = "numero_mipres")
    private String prescriptionNumber;

    @Column(name = "numero_poliza")
    private String policyNumber;

    @Column(name = "numero_suministro")
    private String supplyNumber;

    @Column(name = "fecha_inicio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate invoicingStartDate;

    @Column(name = "fecha_final")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate invoicingEndDate;

    @Column(name = "numero_contrato")
    private String contractNumber;

    @Column(name = "numero_autorizacion")
    private String authorizationNumber;

    @Column(name = "modalidad_contratacion")
    private String contractingModality;

    @Column(name = "codigo_prestador")
    private String lenderCode;

    private PaymentShared paymentShared;


    @Column(name = "modalidad_pago")
    private String paymentModality;
}
