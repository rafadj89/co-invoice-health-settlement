package com.reven.rips.application.services;

import com.reven.rips.application.events.RadicationFileReceivedEvent;
import com.reven.rips.application.events.RipsFileReceivedEvent;
import com.reven.rips.infrastructure.domain.RadicacionServices;
import com.reven.rips.infrastructure.entity.radicacion.Radicacion;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Slf4j
@Component
@AllArgsConstructor
public class RadicationApplicationService {

    private final ApplicationEventPublisher eventPublisher;
    private final RadicacionServices radicacionServices;

    public void save(MultipartFile[] files, Long nit) {

        for (MultipartFile file : files) {
            try {
                log.info("Publicando evento para el archivo: {}", file.getOriginalFilename());
                RadicationFileReceivedEvent event = new RadicationFileReceivedEvent(
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

    public List<Radicacion> findAll(){
       return radicacionServices.findAll();
    }

    public Radicacion findById(String id){
        return radicacionServices.findById(id);
    }

    public Radicacion finByNitAndFactura(Long nit, String invoicesNumber){
        return radicacionServices.finByNitAndFactura(nit, invoicesNumber);
    }


}
