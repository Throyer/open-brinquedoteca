package com.github.throyer.brinquedoteca.services;

import java.util.List;

import com.github.throyer.brinquedoteca.domain.model.Cargo;
import com.github.throyer.brinquedoteca.domain.repository.CargoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoService {
    
    @Autowired
    private CargoRepository repository;
        
    public List<Cargo> obterTodos() {
        return repository.findAll();
    }
    
    public Cargo ObterCargoPorNome(String nome) {
        return repository.findByNome(nome);
    }
}
