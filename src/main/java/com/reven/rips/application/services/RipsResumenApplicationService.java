package com.reven.rips.application.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reven.rips.application.dto.RipsResumenDto;
import com.reven.rips.infrastructure.domain.RipsResumenService;
import com.reven.rips.infrastructure.entity.resumen.RipsResumen;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class RipsResumenApplicationService {

    private final RipsResumenService ripsResumenService;
    private ObjectMapper objectMapper = new ObjectMapper();

    public String save(RipsResumenDto ripsSummary) {
        ObjectMapper objectMapper = new ObjectMapper();
        RipsResumen ripsResumen = objectMapper.convertValue(ripsSummary, RipsResumen.class);
        return ripsResumenService.save(ripsResumen).getId();
    }

    public List<RipsResumenDto> findAll(){
        return ripsResumenService.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private RipsResumenDto convertToDto(RipsResumen ripsSummary) {
        return objectMapper.convertValue(ripsSummary, RipsResumenDto.class);
    }

    public RipsResumenDto findById(String id){
        Optional<RipsResumen> ripsSummary = ripsResumenService.findById(id);
        if(ripsSummary.isPresent()){
            return convertToDto(ripsSummary.get());
        }
        return null;
    }
}
