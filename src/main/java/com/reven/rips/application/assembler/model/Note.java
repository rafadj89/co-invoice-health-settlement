package com.reven.rips.application.assembler.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class Note {

  @XmlElement
  private String documentId;

  @XmlElement
  private String uniqueElectronicDocumentCode;

  @XmlElement
  private String issueDate;

  @XmlElement
  private String invoiceId;

  @XmlElement
  private String uniqueElectronicInvoiceCode;

  @XmlElement
  private String invoiceIssueDate;

  @XmlElement
  private String reference;

  @XmlElement
  private String responseCode;

  @XmlElement
  private String responseDescription;

  @XmlElement
  private BigDecimal amount;

  @XmlElement
  private String thirdPartyDocumentType;

  @XmlElement
  private String thirdPartyName;

  @XmlElement
  private String thirdPartyDocumentNumber;

  @XmlElement
  private String partyLegalEntityDocumentType;

  @XmlElement
  private String partyLegalEntityDocumentNumber;

  @XmlElement
  private String partyLegalEntityName;

}
