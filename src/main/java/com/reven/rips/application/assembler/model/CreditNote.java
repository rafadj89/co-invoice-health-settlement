package com.reven.rips.application.assembler.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "creditNote")
public class CreditNote extends Note {

  @XmlElement
  private String documentCode;

}
