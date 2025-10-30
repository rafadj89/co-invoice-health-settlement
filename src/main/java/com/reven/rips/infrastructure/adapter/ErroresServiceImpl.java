package com.reven.rips.infrastructure.adapter;

import com.reven.rips.infrastructure.domain.ErroresService;
import com.reven.rips.infrastructure.entity.radicacion.Errores;
import com.reven.rips.infrastructure.repository.ErroresRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ErroresServiceImpl implements ErroresService {

    private final ErroresRepository erroresRepository;

    @Override
    public void save(Errores errors) {
        erroresRepository.save(errors);
    }


}
