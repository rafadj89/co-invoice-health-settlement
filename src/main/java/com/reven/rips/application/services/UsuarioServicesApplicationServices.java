package com.reven.rips.application.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.reven.rips.application.dto.AuthResponseDto;
import com.reven.rips.application.dto.FacturaDto;
import com.reven.rips.application.dto.LoginRequestDto;
import com.reven.rips.application.dto.UsuarioDto;
import com.reven.rips.config.JwtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@AllArgsConstructor
public class UsuarioServicesApplicationServices {
/*
    private UsuarioServices usuarioServices;
    private ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

        public AuthResponseDto createUsuario(UsuarioDto usuarioDto) {
            Usuario nuevoUsuario = usuarioServices.create(convertToEntity(usuarioDto));
            String jwtToken = jwtService.generateToken(nuevoUsuario);
            return AuthResponseDto.builder().token(jwtToken).build();
        }

        public AuthResponseDto login(LoginRequestDto request) {

            Usuario usuario = usuarioServices.findByEmail(request.getEmail());
            if(Objects.isNull(usuario)) {
                throw new RuntimeException("Usuario no encontrado");
            }

            if (!passwordEncoder.matches(request.getPassword(), usuario.getContrasenia())) {
                throw new RuntimeException("Contraseña incorrecta");
            }

            String jwtToken = jwtService.generateToken(usuario);
            return AuthResponseDto.builder().token(jwtToken).build();
        }


    private UsuarioDto convertToDto(Usuario user) {
        return objectMapper.convertValue(user, UsuarioDto.class);
    }

    private Usuario convertToEntity(UsuarioDto user) {
        return objectMapper.convertValue(user, Usuario.class);
    }


 */

}
