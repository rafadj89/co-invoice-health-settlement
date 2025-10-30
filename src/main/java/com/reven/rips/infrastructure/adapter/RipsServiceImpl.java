package com.reven.rips.infrastructure.adapter;

import com.reven.rips.infrastructure.domain.RipsService;
import com.reven.rips.infrastructure.entity.rips.Rips;
import com.reven.rips.infrastructure.repository.RipsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RipsServiceImpl implements RipsService {

    private final RipsRepository ripsRepository;

    @Override
    public void saveRips(Rips rips) {
        ripsRepository.save(rips);
    }
}