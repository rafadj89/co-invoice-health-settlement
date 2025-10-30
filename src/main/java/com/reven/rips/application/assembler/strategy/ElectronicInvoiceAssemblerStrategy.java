package com.reven.rips.application.assembler.strategy;

import com.reven.rips.application.assembler.mapper.ElectronicInvoiceMapper;
import com.reven.rips.application.dto.InvoiceDocumentDto;
import com.reven.rips.shared.InvoiceDocumentType;
import com.reven.rips.shared.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class ElectronicInvoiceAssemblerStrategy extends InvoiceDocumentAssemblerStrategy {

  private static final String ELECTRONIC_INVOICE_XSLT = "/xslt/electronInvoice/electronicInvoice.xslt";

  private final ElectronicInvoiceMapper electronicInvoiceMapper;

  public ElectronicInvoiceAssemblerStrategy(Jaxb2Marshaller jaxb2Marshaller,
      ElectronicInvoiceMapper electronicInvoiceMapper) {
    super(jaxb2Marshaller);

    this.electronicInvoiceMapper = electronicInvoiceMapper;
  }

  @Override
  public InvoiceDocumentType getInvoiceDocumentType() {
    return InvoiceDocumentType.ELECTRONIC_INVOICE;
  }

  @Override
  public InvoiceDocumentDto getInvoiceDocumentDto(String attachmentContentDescription) {
    String xmlParsed = Utility.parseXmlDocument(new ClassPathResource(ELECTRONIC_INVOICE_XSLT),
        attachmentContentDescription);
    if (Objects.isNull(xmlParsed)) {
      return null;
    }
    return electronicInvoiceMapper
        .mapElectronicInvoiceDto(unmarshallXml(xmlParsed), getInvoiceDocumentType());
  }
}
