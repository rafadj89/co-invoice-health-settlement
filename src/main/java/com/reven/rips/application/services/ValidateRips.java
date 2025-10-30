package com.reven.rips.application.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reven.rips.application.assembler.XmlDataAssembler;
import com.reven.rips.application.dto.AttachmentDto;
import com.reven.rips.application.dto.CuvDto;
import com.reven.rips.application.dto.XmlDataDTO;
import com.reven.rips.infrastructure.domain.CuvServices;
import com.reven.rips.infrastructure.domain.FacturaService;
import com.reven.rips.infrastructure.domain.RipsResumenService;
import com.reven.rips.infrastructure.domain.RipsService;
import com.reven.rips.infrastructure.entity.cuv.Cuv;
import com.reven.rips.infrastructure.entity.radicacion.Errores;
import com.reven.rips.infrastructure.entity.radicacion.InfoCuv;
import com.reven.rips.infrastructure.entity.radicacion.InfoRips;
import com.reven.rips.infrastructure.entity.resumen.RipsResumen;
import com.reven.rips.infrastructure.entity.rips.Rips;
import com.reven.rips.shared.StatusRips;
import com.reven.rips.shared.StatusValidation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ValidateRips {

    private final FacturaService facturaService;

    private final RipsService ripsService;
    private final RipsResumenService ripsResumenService;
    private static final String APPLICATION_JSON = "application/json";
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String ERROR_CODE_INVALID_ZIP_COUNT = "001";
    private static final String ERROR_MSG_INVALID_ZIP_COUNT = "Cantidad de json de rips invalida. Se esperaba 1 y se encontraron ";
    private static final String ERROR_CODE_ZIP_PROCESSING = "002";
    private static final String ERROR_MSG_ZIP_PROCESSING = "Error al leer el rips";
    private static final String ERROR_CODE_NIT_MISMATCH = "003";
    private static final String ERROR_MSG_NIT_MISMATCH = "Factura no pertenece al prestador en sesión";
    private final CuvServices cuvServices;


    public InfoRips processAndValidateInvoice(List<AttachmentDto> attachments, Long nit) {
        final List<Errores> errors = new ArrayList<>();
        RipsResumen ripsResumen = null;

        try {
            AttachmentDto zipAttachment = findUniqueZipAttachment(attachments, errors);
            String filename = zipAttachment.getName();
            String bucketName = filename.concat(UUID.randomUUID().toString());
            if (zipAttachment != null) {

                String jsonContent = new String(zipAttachment.getContent(), StandardCharsets.UTF_8);
                Rips ripsDocument = objectMapper.readValue(jsonContent, Rips.class);
                ripsResumen = createSummary(ripsDocument,filename,bucketName, nit);
                log.info("Resumen creado para el archivo '{}' con ID: {}", filename, ripsResumen.getId());
                ripsDocument.setIdResumen(ripsResumen.getId());
                ripsService.saveRips(ripsDocument);
                validateRips(ripsDocument,ripsResumen, nit, errors);
            }
        } catch (JsonProcessingException e) {
            createError(errors, ERROR_CODE_ZIP_PROCESSING, ERROR_MSG_ZIP_PROCESSING) ;
        }

        return buildResponse(errors, ripsResumen);
    }


    private void validateRips(Rips ripsDocument, RipsResumen ripsSummary, Long nit, List<Errores> errors) {
        if (isNitMismatch(ripsSummary, nit)) {
            createError(errors, ERROR_CODE_NIT_MISMATCH, ERROR_MSG_NIT_MISMATCH);
        }

    }

    private boolean isNitMismatch(RipsResumen ripsResumen, Long nit) {
        return !Objects.equals(ripsResumen.getNumDocumentoIdObligado(), nit);
    }

    private AttachmentDto findUniqueZipAttachment(List<AttachmentDto> attachments, List<Errores> errors) {
        List<AttachmentDto> zipAttachments = attachments.stream()
                .filter(att -> APPLICATION_JSON.equals(att.getContentType()))
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

    private InfoRips buildResponse(List<Errores> errors, RipsResumen ripsResumen) {
        final InfoRips infoRips = new InfoRips();
        infoRips.getValidacion().setFechaValidacion(LocalDateTime.now());

        if (ripsResumen != null) {
            infoRips.getValidacion().setIdArchivo(ripsResumen.getId());
            infoRips.getRips().add(ripsResumen);
        }

        if (errors.isEmpty()) {
            infoRips.getValidacion().setEstado("EXITOSO");
        } else {
            infoRips.getValidacion().setEstado("FALLIDO");
            infoRips.getValidacion().setErrores(errors);
        }

        return infoRips;
    }

    private RipsResumen createSummary(Rips ripsDocument, String filename, String bucketName, Long nit) {
        return ripsResumenService.save(
                RipsResumen.builder()
                        .estado(StatusRips.PROCESANDO.getValue())
                        .estadoValidaciones(StatusValidation.PROCESANDO.getValue())
                        .numDocumentoIdObligado(nit)
                        .numFactura(ripsDocument.getNumFactura())
                        .numNota(ripsDocument.getNumNota())
                        .tipoNota(ripsDocument.getTipoNota())
                        .nombreArchivo(filename)
                        .nombreBucket(bucketName)
                        .build()
        );
    }


}
