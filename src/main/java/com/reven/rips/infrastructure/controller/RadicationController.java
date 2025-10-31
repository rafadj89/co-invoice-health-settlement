package com.reven.rips.infrastructure.controller;

import com.reven.rips.application.services.RadicationApplicationService;
import com.reven.rips.infrastructure.entity.radicacion.Radicacion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/radicacion")
@RequiredArgsConstructor
public class RadicationController {

    private final RadicationApplicationService radicationApplicationService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadMultipleRipsFiles(@RequestParam("files") MultipartFile[] files, @RequestParam("nit") Long nit) {
        radicationApplicationService.save(files,nit);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findAll")
    public List<Radicacion> getAll(){
        return radicationApplicationService.findAll();
    }

    @GetMapping("/findBy/{id}")
    public Radicacion findById(@PathVariable String id){
        return radicationApplicationService.findById(id);
    }

    @GetMapping("/finByNitAndFactura/{nit}/{invoicesNumber}")
    public Radicacion finByNitAndFactura(@PathVariable Long nit, @PathVariable String invoicesNumber){
        return radicationApplicationService.finByNitAndFactura(nit, invoicesNumber);
    }


}
