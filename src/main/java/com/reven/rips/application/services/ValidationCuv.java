package com.reven.rips.application.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reven.rips.application.assembler.XmlDataAssembler;
import com.reven.rips.application.dto.AttachmentDto;
import com.reven.rips.application.dto.CuvDto;
import com.reven.rips.application.dto.FileInsideZipDTO;
import com.reven.rips.application.dto.XmlDataDTO;
import com.reven.rips.infrastructure.domain.CuvServices;
import com.reven.rips.infrastructure.entity.cuv.Cuv;
import com.reven.rips.infrastructure.entity.factura.XmlData;
import com.reven.rips.infrastructure.entity.radicacion.Errores;
import com.reven.rips.infrastructure.entity.radicacion.InfoCuv;
import com.reven.rips.infrastructure.entity.radicacion.InfoFactura;
import com.reven.rips.shared.StatusRips;
import com.reven.rips.shared.StatusValidation;
import com.reven.rips.shared.Utility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ValidationCuv {
    private static final String APPLICATION_TXT = "text/plain";
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String ERROR_CODE_INVALID_ZIP_COUNT = "001";
    private static final String ERROR_MSG_INVALID_ZIP_COUNT = "Cantidad de txt de cuv invalida. Se esperaba 1 y se encontraron ";
    private static final String ERROR_CODE_ZIP_PROCESSING = "002";
    private static final String ERROR_MSG_ZIP_PROCESSING = "Error al leer el txt de factura ";
    private final CuvServices cuvServices;


    public InfoCuv processAndValidateInvoice(List<AttachmentDto> attachments, Long nit) {
        final List<Errores> errors = new ArrayList<>();
        Cuv savedCuv = null;

        try {
            AttachmentDto zipAttachment = findUniqueZipAttachment(attachments, errors);
            String filename = zipAttachment.getName();
            String bucketName = filename.concat(UUID.randomUUID().toString());
            if (zipAttachment != null) {

                String jsonContent = new String(zipAttachment.getContent(), StandardCharsets.UTF_8);
                CuvDto cuvDocument = objectMapper.readValue(jsonContent, CuvDto.class);
                savedCuv = createCuv(cuvDocument,filename,bucketName, nit);
                log.info("Resumen creado para el archivo '{}' con ID: {}", filename);
            }
        } catch (JsonProcessingException e) {
            createError(errors, ERROR_CODE_ZIP_PROCESSING, ERROR_MSG_ZIP_PROCESSING) ;
        }

        return buildResponse(errors, savedCuv);
    }

    private Cuv createCuv(CuvDto cuvDto, String filename, String bucketName, Long nit) {
        return cuvServices.save(
                Cuv.builder()
                        .estado(StatusRips.PROCESANDO.getValue())
                        .estadoValidaciones(StatusValidation.PROCESANDO.getValue())
                        .nit(nit)
                        .codigoUnicoValidacion(cuvDto.getCodigoUnicoValidacion())
                        .numFactura(cuvDto.getNumFactura())
                        .nombreArchivo(filename)
                        .nombreBucket(bucketName)
                        .build()
        );
    }


    private AttachmentDto findUniqueZipAttachment(List<AttachmentDto> attachments, List<Errores> errors) {
        List<AttachmentDto> zipAttachments = attachments.stream()
                .filter(att -> APPLICATION_TXT.equals(att.getContentType()))
                .collect(Collectors.toList());

        if (zipAttachments.size() != 1) {
            createError(errors, ERROR_CODE_INVALID_ZIP_COUNT, ERROR_MSG_INVALID_ZIP_COUNT + zipAttachments.size());
            return null;
        }

        return zipAttachments.get(0);
    }

    private void createError(List<Errores> errors,String code, String message) {
        errors.add(Errores.builder()
                .code(code)
                .mensaje(message)
                .build());
    }

    private InfoCuv buildResponse(List<Errores> errors, Cuv saveCuv) {
        final InfoCuv infoCuv = new InfoCuv();
        infoCuv.getValidacion().setFechaValidacion(LocalDateTime.now());

        if (saveCuv != null) {
            infoCuv.getValidacion().setIdArchivo(saveCuv.getId());
            infoCuv.getCuvs().add(saveCuv);
        }

        if (errors.isEmpty()) {
            infoCuv.getValidacion().setEstado("EXITOSO");
        } else {
            infoCuv.getValidacion().setEstado("FALLIDO");
            infoCuv.getValidacion().setErrores(errors);
        }

        return infoCuv;
    }

}
