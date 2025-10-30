package com.reven.rips.infrastructure.adapter;

import com.reven.rips.infrastructure.domain.RipsResumenService;
import com.reven.rips.infrastructure.entity.resumen.RipsResumen;
import com.reven.rips.infrastructure.repository.RipsResumenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RipsResumenServiceImpl implements RipsResumenService {

    private final RipsResumenRepository repository;

    @Override
    public RipsResumen save(RipsResumen ripsSummary) {
        return repository.save(ripsSummary);
    }

    @Override
    public List<RipsResumen> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<RipsResumen> findById(String id) {
        return repository.findById(id);
    }
}
