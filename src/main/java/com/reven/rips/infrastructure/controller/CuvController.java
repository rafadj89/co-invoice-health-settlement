package com.reven.rips.infrastructure.controller;

import com.reven.rips.application.services.CuvApplicationServices;
import com.reven.rips.application.services.RipsApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CuvController {

    private final CuvApplicationServices ripsApplicationService;

    @PostMapping("/upload/cuv")
    public ResponseEntity<?> uploadMultipleRipsFiles(@RequestParam("files") MultipartFile[] files, @RequestParam("nit") Long nit) {
        ripsApplicationService.saveCuv(files,nit);
        return ResponseEntity.ok().build();
    }
}