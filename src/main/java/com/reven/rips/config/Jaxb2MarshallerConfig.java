package com.reven.rips.config;

import com.reven.rips.application.assembler.model.*;

import com.reven.rips.application.assembler.model.legacy.Invoice;
import com.reven.rips.application.assembler.model.legacy.InvoiceMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class Jaxb2MarshallerConfig {

  @Bean
  public Jaxb2Marshaller jaxb2Marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller
        .setClassesToBeBound(DebitNote.class, CreditNote.class, ElectronicInvoice.class,
            AdditionalInformation.class,
            AttachmentDocument.class, InvoiceMessage.class, Invoice.class,
            TaxTotal.class, Withholding.class, AiuElement.class, DianValidation.class);

    return marshaller;
  }

}
