package com.github.throyer.brinquedoteca.services;

import java.util.List;

import com.github.throyer.brinquedoteca.domain.model.Canto;
import com.github.throyer.brinquedoteca.domain.repository.CantoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CantoService {

    @Autowired
    private CantoRepository repository;

    public Canto obterPorId(Long id) {
        return repository.findById(id).get();
    }

    public List<Canto> obterTodos() {
        return repository.findAllByOrderByNomeAsc();
    }

    public Canto ObterCantoPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public void salvarCanto(Canto canto) {
        repository.save(canto);
    }

    public void removerCanto(Canto canto) {
        repository.delete(canto);
    }

    public boolean nomeExiste(String nome) {
        return repository.findByNome(nome) != null;
    }
}
