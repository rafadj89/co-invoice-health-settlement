package com.reven.rips.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.reven.rips.shared.paymenMeansType.PaymentMeansConverter;
import com.reven.rips.shared.paymenMeansType.PaymentMeansType;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Convert;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ElectronicInvoiceDto extends InvoiceDocumentDto {

  private String createdBy;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-ES", timezone = "America/Bogota")
  private LocalDate createdDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-ES", timezone = "America/Bogota")
  private LocalDate startDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-ES", timezone = "America/Bogota")
  private LocalDate finalDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-ES", timezone = "America/Bogota")
  private LocalDate emissionDate;

  private String resolutionId;

  private BigDecimal startRange;

  private BigDecimal finalRange;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-ES", timezone = "America/Bogota")
  private LocalDate expirationDate;

  private String uniqueElectronicDocumentCode;

  @Convert(converter = PaymentMeansConverter.class)
  private PaymentMeansType paymentMeansType;

  private String description;

  private BigDecimal totalValue;

  private BigDecimal amount;

  private BigDecimal unitValue;

  private BigDecimal subTotal;

  private BigDecimal iva;

  private BigDecimal withholdingTax;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-ES", timezone = "America/Bogota")
  private LocalDate expeditionDate;

  private BigDecimal taxPercent;

  private BigDecimal tax;

  private BigDecimal taxValue;

  private BigDecimal expirationDays;

  private List<HealthServiceDto> healthServices;
}
