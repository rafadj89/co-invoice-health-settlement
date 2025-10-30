package com.reven.rips.application.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reven.rips.application.events.RipsFileReceivedEvent;
import com.reven.rips.infrastructure.domain.*;
import com.reven.rips.infrastructure.entity.resumen.RipsResumen;
import com.reven.rips.infrastructure.entity.rips.Rips;
import com.reven.rips.shared.StatusRips;
import com.reven.rips.shared.StatusValidation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class RipsFileProcessorListener {

    private final RipsService ripsService;
    private final FacturaService facturaService;
    private final RipsResumenService ripsResumenService;
    private final CuvServices cuvServices;
    private final ErroresService erroresService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Async
    @EventListener
    public void handleRipsFileReceived(RipsFileReceivedEvent event) {
        String filename = event.getOriginalFilename();
        String bucketName = filename.concat(UUID.randomUUID().toString());
        log.info("Iniciando procesamiento del archivo '{}' en el hilo: {}", filename, Thread.currentThread().getName());
        RipsResumen ripsResumen = null;
        try {

            String jsonContent = new String(event.getFileContent(), StandardCharsets.UTF_8);
            Rips ripsDocument = objectMapper.readValue(jsonContent, Rips.class);
            ripsResumen = createSummary(ripsDocument,filename,bucketName, event.getNit());
            log.info("Resumen creado para el archivo '{}' con ID: {}", filename, ripsResumen.getId());
            ripsDocument.setIdResumen(ripsResumen.getId());
            ripsService.saveRips(ripsDocument);
            validateRips(ripsDocument,ripsResumen);
        } catch (JsonProcessingException  e) {
            log.error("Error parseardo el archivo '{}' en el hilo: {}. Causa: {}", filename, Thread.currentThread().getName(), e.getMessage());
           // e.printStackTrace();
            createSummaryErrorInit(filename,bucketName, event.getNit());
        }
    }



    private RipsResumen createSummary(Rips ripsDocument, String filename, String bucketName, String nit) {
        return ripsResumenService.save(
                RipsResumen.builder()
                        .estado(StatusRips.PROCESANDO.getValue())
                        .estadoValidaciones(StatusValidation.PROCESANDO.getValue())
                        .numFactura(ripsDocument.getNumFactura())
                        .numNota(ripsDocument.getNumNota())
                        .tipoNota(ripsDocument.getTipoNota())
                        .nombreArchivo(filename)
                        .nombreBucket(bucketName)
                        .build()
        );
    }

    private void createSummaryErrorInit(String filename, String bucketName, String nit) {
        ripsResumenService.save(
                RipsResumen.builder()
                        .estado(StatusRips.VALIDADO.getValue())
                        .estadoValidaciones(StatusValidation.RECHAZADO.getValue())
                        .nombreArchivo(filename)
                        .nombreBucket(bucketName)
                        .build()
        );
    }

    private void updateSummary(RipsResumen ripsSummary, String validateState) {
        ripsSummary.setEstadoValidaciones(validateState);
        ripsSummary.setEstado(StatusRips.VALIDADO.getValue());
         ripsResumenService.save(ripsSummary);
    }

    private void validateRips(Rips ripsDocument, RipsResumen ripsSummary) {
        /*
        List<MensajeErrores> errors = new ArrayList<>();
        Optional<XmlData> invoices = null;
        Optional<Cuv> cuv = cuvServices.findByNitAndNumFactura(ripsDocument.getNumDocumentoIdObligado(),
                ripsDocument.getNumFactura());
        if (invoices.isEmpty()) {
            updateSummary(ripsSummary,StatusValidation.RECHAZADO.getValue());
            errors.add(createMessageError("Factura no registada en el sistema","RV001"));
        }
        if (cuv.isEmpty()) {
            updateSummary(ripsSummary,StatusValidation.RECHAZADO.getValue());
            errors.add(createMessageError("CUV no registada en el sistema","RV002"));
        }
        if (errors.isEmpty()) {
            updateSummary(ripsSummary,StatusValidation.ACEPTADO.getValue());
        }else {
            createError(ripsSummary,errors);
        }

         */
    }
/*
    private MensajeErrores createMessageError(String message, String code) {
        return MensajeErrores.builder()
                .mensaje(message)
                .code(code)
                .build();
    }

    private void createError(RipsResumen ripsSummary, List<MensajeErrores> mensajeErrores) {
        erroresService.save(
                Errores.builder()
                        .errores(mensajeErrores)
                        .idResumenRips(ripsSummary.getId())
                        .build()
        );
    }

 */
}