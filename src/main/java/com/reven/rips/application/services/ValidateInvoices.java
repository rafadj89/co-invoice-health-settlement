package com.reven.rips.application.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reven.rips.application.assembler.XmlDataAssembler;
import com.reven.rips.application.dto.AttachmentDto;
import com.reven.rips.application.dto.FileInsideZipDTO;
import com.reven.rips.application.dto.HealthServiceDto;
import com.reven.rips.application.dto.XmlDataDTO;
import com.reven.rips.infrastructure.domain.FacturaService;
import com.reven.rips.infrastructure.entity.factura.XmlData;
import com.reven.rips.infrastructure.entity.radicacion.Errores;
import com.reven.rips.infrastructure.entity.radicacion.InfoFactura;
import com.reven.rips.shared.Utility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Slf4j
@Service
@AllArgsConstructor
public class ValidateInvoices {

    private final FacturaService facturaService;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String APPLICATION_ZIP = "application/zip";
    private final XmlDataAssembler xmlDataAssembler;
    private static final String ERROR_CODE_INVALID_ZIP_COUNT = "001";
    private static final String ERROR_MSG_INVALID_ZIP_COUNT = "Cantidad de zip de factura inválida. Se esperaba 1 y se encontraron ";
    private static final String ERROR_CODE_ZIP_PROCESSING = "002";
    private static final String ERROR_MSG_ZIP_PROCESSING = "Error al leer el zip de factura ";
    private static final String ERROR_CODE_XML_NOT_FOUND = "003"; // Mismo código, diferente msg
    private static final String ERROR_MSG_XML_NOT_FOUND = "No se encontró un archivo XML dentro del ZIP.";
    private static final String ERROR_CODE_NIT_MISMATCH = "003";
    private static final String ERROR_MSG_NIT_MISMATCH = "Factura no pertenece al prestador en sesión";
    private static final String ERROR_CODE_NO_HEALTH_NODE = "004";
    private static final String ERROR_MSG_NO_HEALTH_NODE = "Factura no contiene nodo de sector salud";
    private static final String ERROR_CODE_NO_CONTRACT = "005";
    private static final String ERROR_MSG_NO_CONTRACT = "Factura no contiene un contrato";


    public InfoFactura processAndValidateInvoice(List<AttachmentDto> attachments, Long nit) {
        final List<Errores> errors = new ArrayList<>();
        XmlData savedXmlData = null;

        try {
            AttachmentDto zipAttachment = findUniqueZipAttachment(attachments, errors);

            if (zipAttachment != null) {

                final FileInsideZipDTO xmlFile = Utility.getXML(zipAttachment.getContent());
                if (xmlFile == null) {
                    createError(errors, ERROR_CODE_XML_NOT_FOUND, ERROR_MSG_XML_NOT_FOUND);
                    return buildResponse(errors, null);
                }

                final AttachmentDto xmlAttachmentDto = getAttachmentDocument(xmlFile);
                final XmlDataDTO xmlDataDTO = this.loadXmlDataDTO(xmlAttachmentDto);

                validateXmlData(xmlDataDTO, nit, errors);

                if (errors.isEmpty()) {
                    savedXmlData = facturaService.save(objectMapper.convertValue(xmlDataDTO, XmlData.class));
                }
            }
        } catch (Exception e) {
            createError(errors, ERROR_CODE_ZIP_PROCESSING, ERROR_MSG_ZIP_PROCESSING) ;
        }

        return buildResponse(errors, savedXmlData);
    }


    private AttachmentDto findUniqueZipAttachment(List<AttachmentDto> attachments, List<Errores> errors) {
        List<AttachmentDto> zipAttachments = attachments.stream()
                .filter(att -> APPLICATION_ZIP.equals(att.getContentType()))
                .collect(Collectors.toList());

        if (zipAttachments.size() != 1) {
            createError(errors, ERROR_CODE_INVALID_ZIP_COUNT, ERROR_MSG_INVALID_ZIP_COUNT + zipAttachments.size());
            return null;
        }

        return zipAttachments.get(0);
    }


    private void validateXmlData(XmlDataDTO xmlDataDTO, Long nit, List<Errores> errors) {
        if (isNitMismatch(xmlDataDTO, nit)) {
            createError(errors, ERROR_CODE_NIT_MISMATCH, ERROR_MSG_NIT_MISMATCH);
        }
        validateHealthServices(xmlDataDTO.getHealthServices(), errors);
    }


    private boolean isNitMismatch(XmlDataDTO xmlDataDTO, Long nit) {
        return !Objects.equals(xmlDataDTO.getProviderNit(), nit) ||
                !Objects.equals(xmlDataDTO.getProviderNitPartyTaxScheme(), nit) ||
                !Objects.equals(xmlDataDTO.getProviderNitPartyLegalEntity(), nit) ||
                !Objects.equals(xmlDataDTO.getProviderNitApplicationResponse(), nit);
    }

    private void validateHealthServices(List<HealthServiceDto> healthServices, List<Errores> errors) {
        if (healthServices == null || healthServices.isEmpty()) {
            createError(errors, ERROR_CODE_NO_HEALTH_NODE, ERROR_MSG_NO_HEALTH_NODE);
            return;
        }


        final String contractNumber = healthServices.get(0).getContractNumber();
        if (contractNumber == null || contractNumber.trim().isEmpty()) {
            createError(errors, ERROR_CODE_NO_CONTRACT, ERROR_MSG_NO_CONTRACT);
        }
    }


    private InfoFactura buildResponse(List<Errores> errors, XmlData savedXmlData) {
        final InfoFactura infoFactura = new InfoFactura();
        infoFactura.getValidacion().setFechaValidacion(LocalDateTime.now());

        if (savedXmlData != null) {
            infoFactura.getValidacion().setIdArchivo(savedXmlData.getId());
            infoFactura.getXmls().add(savedXmlData);
        }

        if (errors.isEmpty()) {
            infoFactura.getValidacion().setEstado("EXITOSO");
        } else {
            infoFactura.getValidacion().setEstado("FALLIDO");
            infoFactura.getValidacion().setErrores(errors);
        }

        return infoFactura;
    }

    private void createError(List<Errores> errors,String code, String message) {
        errors.add(Errores.builder()
                .code(code)
                .mensaje(message)
                .build());
    }

    public static String readXmlFromZip(byte[] bytes) {
        String xmlString = null;
        try {
            ZipInputStream zipStream = new ZipInputStream(new ByteArrayInputStream(bytes));
            ZipEntry entry = null;
            while ((entry = zipStream.getNextEntry()) != null) {

                String entryName = entry.getName();
                if (entryName.endsWith(".xml")) {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();

                    byte[] bytesEntry = new byte[1024];
                    int length;
                    while ((length = zipStream.read(bytesEntry)) >= 0) {
                        bos.write(bytesEntry, 0, length);
                    }
                    bos.close();
                    zipStream.closeEntry();
                    xmlString = new String(bos.toByteArray(), StandardCharsets.UTF_8);
                }
            }
            zipStream.close();
        } catch (Exception ex) {
            ex.getMessage();
        }
        return xmlString;
    }

    private XmlDataDTO loadXmlDataDTO(final AttachmentDto xmlAttachmentDTO) {
        return xmlDataAssembler.transformXml(xmlAttachmentDTO.getStringData());
    }


    private AttachmentDto getAttachmentDocument(final FileInsideZipDTO fileInsideZipDto) {
        return AttachmentDto
                .builder()
                .name(fileInsideZipDto.getFileName())
                .content(fileInsideZipDto.getData())
                .stringData(readXmlFromZip(fileInsideZipDto.getData()))
                .build();
    }


}

