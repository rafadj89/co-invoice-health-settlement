package com.reven.rips.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;
/*
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UsuarioRepository usuarioRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Usuario usuario = usuarioRepository.findByCorreo(username);
            if (usuario == null) {
                throw new UsernameNotFoundException("Usuario no encontrado con el email: " + username);
            }
            return usuario;
        };
    }
}

 */