package com.reven.rips.infrastructure.adapter;

import com.reven.rips.infrastructure.domain.CuvServices;
import com.reven.rips.infrastructure.entity.cuv.Cuv;
import com.reven.rips.infrastructure.repository.CuvRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CuvServicesImpl implements CuvServices {

    private final CuvRepository cuvRepository;

    @Override
    public Cuv save(Cuv cuv) {
        return cuvRepository.save(cuv);
    }

    @Override
    public Optional<Cuv> findByNitAndNumFactura(String nit, String numFactura) {
        return cuvRepository.findByNitAndNumFactura(nit, numFactura);
    }

}
