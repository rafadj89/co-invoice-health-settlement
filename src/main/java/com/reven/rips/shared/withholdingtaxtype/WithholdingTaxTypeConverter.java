package com.reven.rips.shared.withholdingtaxtype;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class WithholdingTaxTypeConverter implements AttributeConverter<WithholdingTaxType, String> {

  @Override
  public String convertToDatabaseColumn(WithholdingTaxType withholdingTaxType) {
    return withholdingTaxType.getName();
  }

  @Override
  public WithholdingTaxType convertToEntityAttribute(String name) {
    return WithholdingTaxType.findByPrimaryKey(name);
  }
}
