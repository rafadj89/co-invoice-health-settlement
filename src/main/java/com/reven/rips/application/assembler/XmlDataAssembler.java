package com.reven.rips.application.assembler;

import com.reven.rips.application.assembler.mapper.legacy.XmlDataMapper;
import com.reven.rips.application.assembler.model.DianValidation;
import com.reven.rips.application.assembler.model.legacy.Invoice;
import com.reven.rips.application.assembler.model.legacy.InvoiceMessage;
import com.reven.rips.application.dto.XmlDataDTO;
import com.reven.rips.shared.Utility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.Objects;

@Slf4j
@Component
@AllArgsConstructor
public class XmlDataAssembler {
  private static final String INVOICE_MESSAGE_XSLT_PATH = "/invoice.xslt/invoicemessage.xslt";
  private static final String INVOICE_XSLT_PATH = "/invoice.xslt/invoice.xslt";
  private static final String DIAN_VALIDATION_DESCRIPTION_XSLT_PATH = "/xslt/dianValidationDescription.xslt";
  private static final String CLEAN_NAME_SPACES_XSLT_PATH = "/invoice.xslt/cleanNamespaces.xslt";

  private final Jaxb2Marshaller jaxb2Marshaller;
  private final XmlDataMapper xmlDataMapper;

  public XmlDataDTO transformXml(String attachmentContent) {
    try {
      String invoiceMessageParsed = Utility
              .parseXmlDocument(new ClassPathResource(INVOICE_MESSAGE_XSLT_PATH),
                      attachmentContent);
      if (Objects.isNull(invoiceMessageParsed)) {
        return null;
      }
      InvoiceMessage invoiceMessage = unmarshallXml(invoiceMessageParsed);
      if (StringUtils.isNotEmpty(invoiceMessage.getDescription()) && StringUtils
              .isNotEmpty(invoiceMessage.getDianValidationDescription())) {
        Invoice invoice = getInvoice(invoiceMessage.getDescription());
        DianValidation applicationResponse = getDianValidation(invoiceMessage.getDianValidationDescription());
        String dianValidationDescription = getDianValidationDescription(applicationResponse);
        return xmlDataMapper.mapXmlDataDto(invoiceMessage, invoice, applicationResponse, dianValidationDescription);
      } else {
        return null;
      }
    } catch (Exception e){
      log.error("Se presento el siguiente Error: {}::{}", e.getClass(), e.getLocalizedMessage());
      return null;
    }
  }

  @SuppressWarnings("unchecked")
  private <T> T unmarshallXml(final String xmlDocument) {
    return (T) jaxb2Marshaller.unmarshal(new StreamSource(new StringReader(xmlDocument)));
  }

  private Invoice getInvoice(String invoiceXml) {
    if (StringUtils.isEmpty(invoiceXml)){
      return null;
    }
    String invoiceWithoutNamespaces = Utility
        .parseXmlDocument(new ClassPathResource(CLEAN_NAME_SPACES_XSLT_PATH),
            invoiceXml);
    String invoiceParsed = Utility.parseXmlDocument(new ClassPathResource(INVOICE_XSLT_PATH),
        invoiceWithoutNamespaces);
    if (Objects.isNull(invoiceParsed)) {
      return null;
    }
    return unmarshallXml(invoiceParsed);
  }

  private DianValidation getDianValidation(String dianValidationXml){
    String invoiceParsed = Utility
            .parseXmlDocument(new ClassPathResource(DIAN_VALIDATION_DESCRIPTION_XSLT_PATH),
                    dianValidationXml);
    if (Objects.isNull(invoiceParsed)) {
      return null;
    }
    return unmarshallXml(invoiceParsed);
  }

  private String getDianValidationDescription(DianValidation dianValidation) {
    if (StringUtils.isEmpty(dianValidation.getDescription()) || !dianValidation
        .getDescription().equalsIgnoreCase(Utility.DOCUMENT_WAS_VALIDATED_BY_DIAN_RULE)) {
      return StringUtils.EMPTY;
    }
    return dianValidation.getDescription();
  }

}
