package com.reven.rips.application.assembler.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "electronicInvoice")
public class ElectronicInvoice {

  @XmlElement
  private String startDate;

  @XmlElement
  private String finalDate;

  @XmlElement
  private String emissionDate;

  @XmlElement
  private String resolutionId;

  @XmlElement
  private BigDecimal startRange;

  @XmlElement
  private BigDecimal finalRange;

  @XmlElement
  private String expirationDate;

  @XmlElement
  private String uniqueElectronicDocumentCode;

  @XmlElement
  private String paymentMeansType;

  @XmlElement
  private String description;

  @XmlElement
  private BigDecimal totalValue;

  @XmlElement
  private BigDecimal amount;

  @XmlElement
  private BigDecimal unitValue;

  @XmlElement
  private BigDecimal subTotal;

  @XmlElement
  private BigDecimal iva;

  @XmlElement
  private BigDecimal withholdingTax;

  @XmlElement
  private String expeditionDate;

  @XmlElement
  private BigDecimal taxPercent;

  @XmlElement
  private BigDecimal tax;

  @XmlElement
  private BigDecimal taxValue;

  @XmlElement
  private AdditionalInformation additionalInformation;
}
