package com.github.throyer.brinquedoteca.services;

import com.github.throyer.brinquedoteca.domain.model.Cargo;
import com.github.throyer.brinquedoteca.domain.repository.CargoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SistemaService {

    @Autowired
    private CargoRepository repository;

    public boolean isConfigured() {

        Cargo administrador = repository.findByNome("ADMINISTRADOR");

        if (administrador != null) {
            if (administrador.getUsuarios() != null) {
                return !(administrador.getUsuarios().isEmpty());
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
