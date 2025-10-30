package com.reven.rips.application.services;

import com.reven.rips.application.events.RipsFileReceivedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@Component
@AllArgsConstructor
public class RipsApplicationService {

    private final ApplicationEventPublisher eventPublisher;

    public void saveRips(MultipartFile[] files, String nit) {

        for (MultipartFile file : files) {
            try {
                log.info("Publicando evento para el archivo: {}", file.getOriginalFilename());
                RipsFileReceivedEvent event = new RipsFileReceivedEvent(
                        this,
                        file.getBytes(),
                        file.getOriginalFilename(),
                        nit
                );
                eventPublisher.publishEvent(event);
            } catch (IOException e) {
                log.error("No se pudo leer el archivo '{}' para publicar el evento. Archivo omitido.", file.getOriginalFilename(), e);
            }
        }
    }

}
