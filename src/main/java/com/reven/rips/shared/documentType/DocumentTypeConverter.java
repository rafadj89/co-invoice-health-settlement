package com.reven.rips.shared.documentType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/** @author Jhon Barón */
@Converter
public class DocumentTypeConverter implements AttributeConverter<DocumentType, String> {

  @Override
  public String convertToDatabaseColumn(DocumentType attribute) {
    if (attribute != null) {
      return attribute.getId();
    }
    return null;
  }

  @Override
  public DocumentType convertToEntityAttribute(String dbData) {
    return DocumentType.findByPrimaryKey(dbData);
  }
}
