package com.reven.rips.shared.invoiceNotificatioStatusType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

/** @author Jhon Barón */
@Converter
public class InvoiceNotificationStatusTypeConverter implements AttributeConverter<InvoiceNotificationStatusType, String> {
  @Override
  public String convertToDatabaseColumn(final InvoiceNotificationStatusType invoiceNotificationStatusType) {
    if (!Objects.isNull(invoiceNotificationStatusType)) return invoiceNotificationStatusType.getId();
    return InvoiceNotificationStatusType.EMPTY.getId();
  }

  @Override
  public InvoiceNotificationStatusType convertToEntityAttribute(final String s) {
    return InvoiceNotificationStatusType.findByPrimaryKey(s);
  }
}
