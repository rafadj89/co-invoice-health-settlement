package com.reven.rips.shared.paymenMeansType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

/** @author Jhon Baron */
@Converter
public class PaymentMeansConverter implements AttributeConverter<PaymentMeansType, String> {
  @Override
  public String convertToDatabaseColumn(PaymentMeansType paymentMeansType) {
    if (!Objects.isNull(paymentMeansType)) {
      return paymentMeansType.getId();
    }
    return PaymentMeansType.NOT_APPLY.getId();
  }

  @Override
  public PaymentMeansType convertToEntityAttribute(String s) {
    return PaymentMeansType.findByPrimaryKey(s);
  }
}
