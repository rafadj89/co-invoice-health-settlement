package com.reven.rips.infrastructure.controller;

import com.reven.rips.application.dto.AuthResponseDto;
import com.reven.rips.application.dto.LoginRequestDto;
import com.reven.rips.application.dto.UsuarioDto;
import com.reven.rips.application.services.UsuarioServicesApplicationServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServicesApplicationServices authService;
/*
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> crearUsuario(@RequestBody UsuarioDto usuarioDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.createUsuario(usuarioDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

 */
}