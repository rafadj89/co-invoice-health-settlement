package com.reven.rips.shared.invoiceStatusType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter
public class InvoiceStatusTypeConverter implements AttributeConverter<InvoiceStatusType, String>{

  @Override
public String convertToDatabaseColumn(final InvoiceStatusType invoiceStatusType) {
    if (!Objects.isNull(invoiceStatusType)) return invoiceStatusType.getId();
    return InvoiceStatusType.EMPTY.getId();
    }

@Override
public InvoiceStatusType convertToEntityAttribute(final String s) {
    return InvoiceStatusType.findByPrimaryKey(s);
    }

    }
