package com.reven.rips.infrastructure.adapter;

import com.reven.rips.infrastructure.domain.RadicacionServices;
import com.reven.rips.infrastructure.entity.radicacion.Radicacion;
import com.reven.rips.infrastructure.repository.RadicacionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RadicacionServicesImpl implements RadicacionServices {

    private RadicacionRepository radicacionRepository;

    @Override
    public void save(Radicacion radication) {
        radicacionRepository.save(radication);
    }

    @Override
    public List<Radicacion> findAll() {
        return radicacionRepository.findAll();
    }

    @Override
    public Radicacion findById(String id) {
        Optional<Radicacion> radicacion = radicacionRepository.findById(id);
        return radicacion.orElse(null);
    }

    @Override
    public Radicacion finByNitAndFactura(Long nit, String invoicesNumber) {
        return radicacionRepository.findByNitAndNumeroFactura(nit, invoicesNumber);
    }


}
