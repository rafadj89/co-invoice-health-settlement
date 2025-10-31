package com.reven.rips.application.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reven.rips.application.dto.CuvDto;
import com.reven.rips.application.events.CuvFileReceivedEvent;
import com.reven.rips.application.events.RipsFileReceivedEvent;
import com.reven.rips.infrastructure.domain.CuvServices;
import com.reven.rips.infrastructure.entity.cuv.Cuv;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
public class CuvApplicationServices {

    private final CuvServices cuvServices;
    private final ObjectMapper objectMapper;

    public CuvDto saveCuv(CuvDto cuvDto) {
        return convertToDto(cuvServices.save(convertToEntity(cuvDto)));
    }

    private CuvDto convertToDto(Cuv cuv) {
        return objectMapper.convertValue(cuv, CuvDto.class);
    }

    private Cuv convertToEntity(CuvDto cuv) {
        return objectMapper.convertValue(cuv, Cuv.class);
    }

}
