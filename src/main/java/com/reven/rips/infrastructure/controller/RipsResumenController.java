package com.reven.rips.infrastructure.controller;

import com.reven.rips.application.dto.RipsResumenDto;
import com.reven.rips.application.services.RipsResumenApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rips-resumen")
@RequiredArgsConstructor
public class RipsResumenController {

    private final RipsResumenApplicationService ripsResumenApplicationService;

    @GetMapping
    public List<RipsResumenDto> findAll() {
        List<RipsResumenDto> ripsSummary = ripsResumenApplicationService.findAll();
        return ripsResumenApplicationService.findAll();
    }

    @GetMapping("/{id}")
    public RipsResumenDto findById(@PathVariable String id) {
        return ripsResumenApplicationService.findById(id);
    }
}
