package com.reven.rips.application.assembler.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "attachedDocument")
public class AttachmentDocument {

  @XmlElement
  private String billId;

  @XmlElement
  private String thirdPartyDocumentType;

  @XmlElement
  private String thirdPartyName;

  @XmlElement
  private String thirdPartyDocumentNumber;

  @XmlElement
  private String receiverPartyDocumentNumber;
  
  @XmlElement
  private String receiverPartyDocumentType;

  @XmlElement
  private String receiverPartyName;

  @XmlElement
  private String validationDate;

  @XmlElement
  private String documentDescription;

  @XmlElement
  private String dianValidationDescription;
}
