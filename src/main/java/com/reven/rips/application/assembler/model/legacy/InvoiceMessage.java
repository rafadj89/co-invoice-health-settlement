package com.reven.rips.application.assembler.model.legacy;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "invoiceMessage")
public class InvoiceMessage {


  @XmlElement
  private String lenderId;
  
  @XmlElement
  private String lenderDocumentType;
  
  @XmlElement
  private String lenderName;

  @XmlElement
  private String providerNit;

  @XmlElement
  private String validationDate;

  @XmlElement
  private String description;

  @XmlElement
  private String documentType;

  @XmlElement
  private String dianValidationDescription;
}
