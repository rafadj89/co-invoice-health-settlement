package com.reven.rips.application.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reven.rips.application.dto.CuvDto;
import com.reven.rips.application.events.CuvFileReceivedEvent;
import com.reven.rips.infrastructure.entity.cuv.Cuv;
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
public class CuvFileProcessorListener {

    private final CuvApplicationServices cuvApplicationServices;
    private final ObjectMapper objectMapper;

    @Async
    @EventListener
    public void handleRipsFileReceived(CuvFileReceivedEvent event){
        String filename = event.getOriginalFilename();
        String bucketName = filename.concat(UUID.randomUUID().toString());
        log.info("Iniciando procesamiento del archivo '{}' en el hilo: {}", filename, Thread.currentThread().getName());

        String cuvId = null;
        try {

            String jsonContent = new String(event.getFileContent(), StandardCharsets.UTF_8);
            Cuv cuvDocument = objectMapper.readValue(jsonContent, Cuv.class);
            cuvId = createCuv(cuvDocument,filename,bucketName, event.getNit());
            log.info("Resumen creado para el archivo '{}' con ID: {}", filename, cuvId);

        } catch (JsonProcessingException e) {
            log.error("Error parseardo el archivo '{}' en el hilo: {}. Causa: {}", filename, Thread.currentThread().getName(), e.getMessage());
            // e.printStackTrace();
            createCuvError(filename,bucketName, event.getNit());
        }

    }

    private String createCuv(Cuv cuv, String filename, String bucketName, Long nit) {
        return cuvApplicationServices.saveCuv(
                CuvDto.builder()
                        .estado(StatusRips.PROCESANDO.getValue())
                        .estadoValidaciones(StatusValidation.PROCESANDO.getValue())
                        .nit(nit)
                        .codigoUnicoValidacion(cuv.getCodigoUnicoValidacion())
                        .numFactura(cuv.getNumFactura())
                        .nombreArchivo(filename)
                        .nombreBucket(bucketName)
                        .build()
        ).getId();
    }

    private String createCuvError(String filename, String bucketName, Long nit) { /// guardar errores
        return cuvApplicationServices.saveCuv(
                CuvDto.builder()
                        .estado(StatusRips.VALIDADO.getValue())
                        .estadoValidaciones(StatusValidation.RECHAZADO.getValue())
                        .nit(nit)
                        .nombreArchivo(filename)
                        .nombreBucket(bucketName)
                        .build()
        ).getId();
    }

}
