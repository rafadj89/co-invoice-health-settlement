package com.reven.rips.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Jhon Baron
 */
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class XmlDataDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @Convert(converter = ThirdTypeConverter.class)
    private ThirdType thirdType;

    private String billId;

    private String resolutionId;

    private Long startRange;

    private Long finalRange;

    @Convert(converter = DocumentTypeConverter.class)
    private DocumentType documentType;

    private String lenderId;

    private String lenderDocumentType;

    private Long providerNit;

    private Long providerNitPartyTaxScheme;

    private Long providerNitPartyLegalEntity;
    private Long providerNitApplicationResponse;

    private String lenderName;

    private String cufe;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date emissionDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date expirationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date validationDate;

    @Convert(converter = PaymentMeansConverter.class)
    private PaymentMeansType paymentMeansType;

    private String dianValidationDescription;

    private String description;

    private Double amount;

    private BigDecimal unitValue;

    private BigDecimal subTotal;

    private BigDecimal baseIva;

    @Deprecated
    private BigDecimal iva;

    @Deprecated
    private BigDecimal rtefte;

    private BigDecimal totalValue;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date createdDate;

    private String createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date finalDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String authorizationId;

    private String thirdEmail;

    private Integer purchaseOrder;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date expeditionDate;

    @Deprecated
    private Double taxPercent;

    @Deprecated
    private String tax;

    @Deprecated
    private BigDecimal taxValue;

    @Convert(converter = InvoiceStatusTypeConverter.class)
    private InvoiceStatusType invoiceStatusType;

    @Convert(converter = InvoiceNotificationStatusTypeConverter.class)
    private InvoiceNotificationStatusType invoiceNotificationStatusType;

    private Integer expirationDays;

    @Deprecated
    private String withholdings;

    @Deprecated
    private List<TaxDTO> taxes;

    @Deprecated
    private String reteiva;

    @Deprecated
    private String retefuente;

    @Deprecated
    private String reteica;

    private List<HealthServiceDto> healthServices;
    private List<WithholdingTaxDto> withholdingTax;
    private List<WithholdingTaxDto> taxTotal;
    private AiuAmountDto aiuAmounts;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date billInitialDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date billFinalDate;

    private boolean fromLoader;

    private BigDecimal totalDiscount;
    private BigDecimal totalTaxes;
}
