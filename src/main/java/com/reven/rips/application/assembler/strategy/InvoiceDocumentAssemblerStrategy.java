package com.reven.rips.application.assembler.strategy;

import com.reven.rips.application.dto.InvoiceDocumentDto;
import com.reven.rips.shared.InvoiceDocumentType;
import lombok.AllArgsConstructor;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

@AllArgsConstructor
public abstract class InvoiceDocumentAssemblerStrategy {

  protected final Jaxb2Marshaller jaxb2Marshaller;

  public abstract InvoiceDocumentType getInvoiceDocumentType();

  public abstract InvoiceDocumentDto getInvoiceDocumentDto(String attachmentContentDescription);

  @SuppressWarnings("unchecked")
  protected  <T> T unmarshallXml(final String xmlDocument) {
    return (T) jaxb2Marshaller.unmarshal(new StreamSource(new StringReader(xmlDocument)));
  }

}
