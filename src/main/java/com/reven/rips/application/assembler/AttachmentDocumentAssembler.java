package com.reven.rips.application.assembler;

import com.reven.rips.application.assembler.mapper.AttachmentDocumentMapper;
import com.reven.rips.application.assembler.model.AttachmentDocument;
import com.reven.rips.application.assembler.model.DianValidation;
import com.reven.rips.application.assembler.strategy.InvoiceDocumentAssemblerStrategyFactory;
import com.reven.rips.application.dto.AttachmentDocumentDto;
import com.reven.rips.application.dto.InvoiceDocumentDto;
import com.reven.rips.shared.Utility;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class AttachmentDocumentAssembler {

    private static final String ATTACHED_DOCUMENT_XSLT = "/xslt/attachedDocument.xslt";
    private static final String DIAN_VALIDATION_DESCRIPTION_XSLT = "/xslt/dianValidationDescription.xslt";
    private static final String DOCUMENT_VALIDATED_BY_DIAN_RULE = "Documento validado por la DIAN";

    private final InvoiceDocumentAssemblerStrategyFactory invoiceDocumentAssemblerStrategyFactory;
    private final AttachmentDocumentMapper attachmentDocumentMapper;
    private final Jaxb2Marshaller jaxb2Marshaller;

    public AttachmentDocumentDto transformToDto(String attachmentContent,
                                                String invoiceDocumentTypeCode) {

        String xmlParsed = Utility.parseXmlDocument(new ClassPathResource(ATTACHED_DOCUMENT_XSLT),
                attachmentContent);
        if (Objects.isNull(xmlParsed)) {
            return null;
        }

        AttachmentDocument attachmentDocument = unmarshallXml(xmlParsed);
        AttachmentDocumentDto attachmentDocumentDto = attachmentDocumentMapper
                .mapAttachmentDataDto(attachmentDocument);
        DianValidation dianValidation = getDianValidation(attachmentDocument.getDianValidationDescription());
        attachmentDocumentDto
                .setDianValidationDescription(getDianValidationDescription(dianValidation));
        attachmentDocumentDto.setDianValidation(toDianValidationDto(dianValidation));
        if (Objects.nonNull(attachmentDocument.getDocumentDescription())) {
            InvoiceDocumentDto invoiceDocumentDto = invoiceDocumentAssemblerStrategyFactory
                    .findStrategy(invoiceDocumentTypeCode)
                    .getInvoiceDocumentDto(attachmentDocument.getDocumentDescription());
            if (Objects.nonNull(invoiceDocumentDto)) {
                attachmentDocumentDto.setDescription(invoiceDocumentDto);
            }
        }
        return attachmentDocumentDto;
    }

    private DianValidation getDianValidation(String dianValidationXml) {
        String invoiceParsed = Utility
                .parseXmlDocument(new ClassPathResource(DIAN_VALIDATION_DESCRIPTION_XSLT),
                        dianValidationXml);
        if (Objects.isNull(invoiceParsed)) {
            return null;
        }
        return unmarshallXml(invoiceParsed);
    }

    private String getDianValidationDescription(DianValidation dianValidation) {
        if (Objects.isNull(dianValidation) || StringUtils.isEmpty(dianValidation.getDescription()) || !dianValidation
                .getDescription().equalsIgnoreCase(DOCUMENT_VALIDATED_BY_DIAN_RULE)) {
            return StringUtils.EMPTY;
        }
        return dianValidation.getDescription();
    }

    private AttachmentDocumentDto.DianValidationDto toDianValidationDto(DianValidation dianValidation) {
        if (Objects.isNull(dianValidation)) {
            return AttachmentDocumentDto.DianValidationDto.builder().build();
        }
        return AttachmentDocumentDto.DianValidationDto
                .builder()
                .description(dianValidation.getDescription())
                .providerNit(dianValidation.getProviderNit())
                .build();
    }

    @SuppressWarnings("unchecked")
    protected <T> T unmarshallXml(final String xmlDocument) {
        return (T) jaxb2Marshaller.unmarshal(new StreamSource(new StringReader(xmlDocument)));
    }

    public AttachmentDocument getAttachmentDocument(String attachmentContent) {

        String xmlParsed = Utility.parseXmlDocument(new ClassPathResource(ATTACHED_DOCUMENT_XSLT),
                attachmentContent);
        if (Objects.isNull(xmlParsed)) {
            return null;
        }

        return unmarshallXml(xmlParsed);
    }
}