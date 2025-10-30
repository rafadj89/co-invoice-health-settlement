package com.reven.rips.application.assembler.strategy;

import com.reven.rips.application.assembler.mapper.CreditNoteMapper;
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
public class CreditNoteAssemblerStrategy extends InvoiceDocumentAssemblerStrategy {

  private static final String CREDIT_NOTE_XSLT = "/xslt/note/creditNote.xslt";

  private final CreditNoteMapper creditNoteMapper;

  public CreditNoteAssemblerStrategy(Jaxb2Marshaller jaxb2Marshaller,
      CreditNoteMapper creditNoteMapper) {
    super(jaxb2Marshaller);

    this.creditNoteMapper = creditNoteMapper;
  }

  @Override
  public InvoiceDocumentType getInvoiceDocumentType() {
    return InvoiceDocumentType.CREDIT_NOTE;
  }

  @Override
  public InvoiceDocumentDto getInvoiceDocumentDto(String attachmentContentDescription) {

    String xmlParsed = Utility.parseXmlDocument(new ClassPathResource(CREDIT_NOTE_XSLT),
        attachmentContentDescription);
    if (Objects.isNull(xmlParsed)) {
      return null;
    }
    return creditNoteMapper
        .mapCreditNoteDto(unmarshallXml(xmlParsed), getInvoiceDocumentType());
  }
}
