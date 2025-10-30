package com.reven.rips.application.dto;

import com.reven.rips.shared.documentType.DocumentType;
import com.reven.rips.shared.documentType.DocumentTypeConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;

@Getter
@Setter
public class ThirdPartyDto {

  private String documentNumber;
  private String name;

  @Convert(converter = DocumentTypeConverter.class)
  private DocumentType documentType;

}
