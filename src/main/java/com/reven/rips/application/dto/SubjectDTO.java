package com.reven.rips.application.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SubjectDTO implements Serializable {

  private String nitBiller;
  private String nameTradeBiller;
  private String nameBiller;
  private String codeTypeDocument;
  private String number;
  private String businessLine;

  public String getConcatenatedSubject() {
    return this.nitBiller + ";" + this.nameBiller + ";" + this.number + ";" + this.codeTypeDocument
        + ";"
        + this.nameTradeBiller;
  }
}
