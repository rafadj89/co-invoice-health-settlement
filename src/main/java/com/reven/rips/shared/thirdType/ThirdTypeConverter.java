package com.reven.rips.shared.thirdType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

/** @author Jhon Baron */
@Converter
public class ThirdTypeConverter implements AttributeConverter<ThirdType, String> {
  @Override
  public String convertToDatabaseColumn(ThirdType thirdType) {
    if (!Objects.isNull(thirdType)) return thirdType.getId();
    return ThirdType.NOT_APPLY.getId();
  }

  @Override
  public ThirdType convertToEntityAttribute(String s) {
    return ThirdType.findByPrimaryKey(s);
  }
}
