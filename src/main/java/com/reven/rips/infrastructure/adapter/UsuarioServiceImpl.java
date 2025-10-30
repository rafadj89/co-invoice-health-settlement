package com.reven.rips.infrastructure.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/*
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioServices {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario create(Usuario usuario) {
        String contraseniaPlana = usuario.getContrasenia();
        String contraseniaHasheada = passwordEncoder.encode(contraseniaPlana);
        usuario.setContrasenia(contraseniaHasheada);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByCorreo(email);
    }

}

 */