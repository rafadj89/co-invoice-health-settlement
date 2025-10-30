package com.reven.rips.infrastructure.entity.factura;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.reven.rips.infrastructure.entity.radicacion.Errores;
import com.reven.rips.shared.documentType.DocumentType;
import com.reven.rips.shared.documentType.DocumentTypeConverter;
import com.reven.rips.shared.invoiceNotificatioStatusType.InvoiceNotificationStatusType;
import com.reven.rips.shared.invoiceNotificatioStatusType.InvoiceNotificationStatusTypeConverter;
import com.reven.rips.shared.invoiceStatusType.InvoiceStatusType;
import com.reven.rips.shared.invoiceStatusType.InvoiceStatusTypeConverter;
import com.reven.rips.shared.paymenMeansType.PaymentMeansConverter;
import com.reven.rips.shared.paymenMeansType.PaymentMeansType;
import com.reven.rips.shared.thirdType.ThirdType;
import com.reven.rips.shared.thirdType.ThirdTypeConverter;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * @author Jhon Baron
 */
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class XmlData {

    @Id
    private String id;

    @Column(name = "tipoTercero", nullable = false)
    @Convert(converter = ThirdTypeConverter.class)
    private ThirdType thirdType;

    @Column(name = "idFactura")
    private String billId;

    @Column(name = "idResolucion")
    private String resolutionId;

    @Column(name = "rangoInicio")
    private Long startRange;

    @Column(name = "rangoFin")
    private Long finalRange;

    @Convert(converter = DocumentTypeConverter.class)
    @Column(name = "tipoDocumento")
    private DocumentType documentType;

    @Column(name = "idPrestador")
    private String lenderId;

    @Column(name = "nitProvedor", nullable = false)
    private Long providerNit;

    @Column(name = "nomPrestador")
    private String lenderName;

    @Column(name = "cufe")
    private String cufe;

    @Column(name = "fechaEmision")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-ES", timezone = "America/Bogota")
    private Date emissionDate;

    @Column(name = "fechaVencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-ES", timezone = "America/Bogota")
    private Date expirationDate;

    @Column(name = "fechaValidacion")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-ES", timezone = "America/Bogota")
    private Date validationDate;

    @Column(name = "formaPago")
    @Convert(converter = PaymentMeansConverter.class)
    private PaymentMeansType paymentMeansType;

    @Column(name = "descripcionValidacionDian")
    private String dianValidationDescription;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "cantidad")
    private Double amount;

    @Column(name = "vrUnit")
    private BigDecimal unitValue;

    @Column(name = "subtotal")
    private BigDecimal subTotal;

    @Column(name = "baseIva")
    private BigDecimal baseIva;

    @Column(name = "iva")
    private BigDecimal iva;

    @Column(name = "rtefte")
    private BigDecimal rtefte;

    @Column(name = "vrTotal")
    private BigDecimal totalValue;

    @Column(name = "fecCrea")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-ES", timezone = "America/Bogota")
    private Date createdDate;

    @Column(name = "usuCrea")
    private String createdBy;

    @Column(name = "fechaInicio")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-ES", timezone = "America/Bogota")
    private Date startDate;

    @Column(name = "fechaFin")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-ES", timezone = "America/Bogota")
    private Date finalDate;

    @Column(name = "idAutorizacion")
    private String authorizationId;

    @Column(name = "correoTercero")
    private String thirdEmail;

    @Column(name = "ordenCompra")
    private Integer purchaseOrder;

    @Column(name = "fechaExpedicion")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-ES", timezone = "America/Bogota")
    private Date expeditionDate;

    @Column(name = "porcentajeImpuesto")
    private Double taxPercent;

    @Column(name = "impuesto")
    private String tax;

    @Column(name = "valorImpuesto")
    private BigDecimal taxValue;

    @Column(name = "estadoFactura")
    @Convert(converter = InvoiceStatusTypeConverter.class)
    private InvoiceStatusType invoiceStatusType;

    @Column(name = "estadoNotificacionFactura")
    @Convert(converter = InvoiceNotificationStatusTypeConverter.class)
    private InvoiceNotificationStatusType invoiceNotificationStatusType;

    @Column(name = "diasVencimiento")
    private Integer expirationDays;

    @Column(name = "retenciones")
    private String withholdings;

    private List<HealthService> healthServices;

    private List<WithholdingTaxInvoice> withholdingTaxInvoices;

    @Column(name = "administracion")
    private BigDecimal administrationAmount;

    @Column(name = "imprevisto")
    private BigDecimal unforeseenAmount;

    @Column(name = "utilidad")
    private BigDecimal utilityAmount;

    @Column(name = "fechaInicioFactura")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-ES", timezone = "America/Bogota")
    private Date billInitialDate;

    @Column(name = "fechaFinFactura")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-ES", timezone = "America/Bogota")
    private Date billFinalDate;

    @Column(name = "fechaIntegracion")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-ES", timezone = "America/Bogota")
    private Date integrationDate;

    @Column(name = "totalDescuento")
    private BigDecimal totalDiscount;

    @Column(name = "totalMasImpuestos")
    private BigDecimal totalTaxes;

    private List<Errores> errores;
}