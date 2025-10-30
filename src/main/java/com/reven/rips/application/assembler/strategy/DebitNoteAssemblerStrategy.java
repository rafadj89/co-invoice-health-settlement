package com.reven.rips.application.assembler.strategy;

import com.reven.rips.application.assembler.mapper.DebitNoteMapper;
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
public class DebitNoteAssemblerStrategy extends InvoiceDocumentAssemblerStrategy {

  private static final String DEBIT_NOTE_XSLT = "/xslt/note/debitNote.xslt";

  private final DebitNoteMapper debitNoteMapper;

  public DebitNoteAssemblerStrategy(Jaxb2Marshaller jaxb2Marshaller,
      DebitNoteMapper debitNoteMapper) {
    super(jaxb2Marshaller);

    this.debitNoteMapper = debitNoteMapper;
  }

  @Override
  public InvoiceDocumentType getInvoiceDocumentType() {
    return InvoiceDocumentType.DEBIT_NOTE;
  }

  @Override
  public InvoiceDocumentDto getInvoiceDocumentDto(String attachmentContentDescription) {

    String xmlParsed = Utility.parseXmlDocument(new ClassPathResource(DEBIT_NOTE_XSLT),
        attachmentContentDescription);
    if (Objects.isNull(xmlParsed)) {
      return null;
    }
    return debitNoteMapper
        .mapDebitNoteDto(unmarshallXml(xmlParsed), getInvoiceDocumentType());
  }
}
