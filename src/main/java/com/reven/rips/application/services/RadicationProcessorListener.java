package com.reven.rips.application.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reven.rips.application.assembler.XmlDataAssembler;
import com.reven.rips.application.dto.*;
import com.reven.rips.application.events.RadicationFileReceivedEvent;
import com.reven.rips.application.events.RipsFileReceivedEvent;
import com.reven.rips.infrastructure.domain.FacturaService;
import com.reven.rips.infrastructure.domain.RadicacionServices;
import com.reven.rips.infrastructure.entity.factura.XmlData;
import com.reven.rips.infrastructure.entity.radicacion.*;
import com.reven.rips.shared.Utility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class RadicationProcessorListener {

    private ValidateInvoices validateInvoices;
    private ValidationCuv validationCuv;
    private ValidateRips validateRips;
    private RadicacionServices radicacionServices;

    @Async
    @EventListener
    public void handleRipsFileReceived(RadicationFileReceivedEvent event) {
        String filename = event.getOriginalFilename();
        String bucketName = filename.concat(UUID.randomUUID().toString());
        log.info("Iniciando procesamiento del archivo '{}' en el hilo: {}", filename, Thread.currentThread().getName());
        List<AttachmentDto> attachments = Utility.readFilesFromBase64(event.getFileContent());
        InfoFactura infoFactura = validateInvoices.processAndValidateInvoice(attachments,event.getNit());
        InfoCuv infoCuv = validationCuv.processAndValidateInvoice(attachments,event.getNit());
        InfoRips infoRips = validateRips.processAndValidateInvoice(attachments,event.getNit());

        radicacionServices.save(createRadicacion(infoFactura, infoCuv, infoRips, event.getNit()));
    }

    private String getState(InfoFactura infoFactura, InfoCuv infoCuv, InfoRips infoRips) {
        if (infoFactura == null || infoCuv == null || infoRips == null) {
            return "PENDIENTE_CORRECCION";
        }else {
            if (infoFactura.getValidacion().getEstado().equals("EXITOSO")
                    && infoCuv.getValidacion().getEstado().equals("EXITOSO")
            && infoRips.getValidacion().getEstado().equals("EXITOSO")) {
                return "VALIDADO";
            }
        }
        return "PENDIENTE_CORRECCION";
    }

    private Radicacion createRadicacion(InfoFactura infoFactura, InfoCuv infoCuv, InfoRips infoRips, Long nit) {
        String invoicesNumber = getInvoicesNumber(infoFactura, infoCuv, infoRips);
        String radicationNumber = nit + "-" + invoicesNumber + "-" + LocalDate.now();
       return Radicacion.builder()
                .cuv(infoCuv)
                .factura(infoFactura)
                .rips(infoRips)
                .estadoGeneral(getState(infoFactura, infoCuv, infoRips))
                .nit(nit)
                .numeroFactura(invoicesNumber)
                .valor(getInvoicesValue(infoFactura))
                .numeroRadicacion(radicationNumber)
                .build();

    }

    private String getInvoicesNumber(InfoFactura infoFactura, InfoCuv infoCuv, InfoRips infoRips) {
        if(Objects.nonNull(infoFactura)){
            if(!infoFactura.getXmls().isEmpty()){
                return infoFactura.getXmls().get(0).getBillId();
            }
        }
        if(Objects.nonNull(infoCuv)){
            if(!infoRips.getRips().isEmpty()){
                return infoRips.getRips().get(0).getNumFactura();
            }
        }
        if(Objects.nonNull(infoCuv)){
            if (!infoCuv.getCuvs().isEmpty()){
                return infoCuv.getCuvs().get(0).getNumFactura();
            }
        }
        return null;
    }

    private BigDecimal getInvoicesValue(InfoFactura infoFactura){
        if(Objects.nonNull(infoFactura)){
            if(!infoFactura.getXmls().isEmpty()){
                return infoFactura.getXmls().get(0).getSubTotal();
            }
        }
        return null;
    }





}
