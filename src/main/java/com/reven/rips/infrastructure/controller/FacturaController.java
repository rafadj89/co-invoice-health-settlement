package com.reven.rips.infrastructure.controller;


import com.reven.rips.application.services.FacturaApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaController {

    private final FacturaApplicationService facturaApplicationService;


    @PostMapping("/upload/invoices")
    public ResponseEntity<?> uploadMultipleRipsFiles(@RequestParam("files") MultipartFile files, @RequestParam("nit") String nit) throws IOException {
       // facturaApplicationService.getInvoiceById(files);
        return ResponseEntity.ok().build();
    }

}