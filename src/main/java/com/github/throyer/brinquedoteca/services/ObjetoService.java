package com.github.throyer.brinquedoteca.services;

import java.util.List;

import com.github.throyer.brinquedoteca.domain.model.Canto;
import com.github.throyer.brinquedoteca.domain.model.ObjetoLudico;
import com.github.throyer.brinquedoteca.domain.repository.ObjetoLudicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ObjetoService {

    @Autowired
    ObjetoLudicoRepository repository;

    public ObjetoLudico obterPorId(Long id) {
        return repository.findById(id).get();
    }

    public List<ObjetoLudico> obterTodos() {
        return repository.findAll();
    }

    public Page<ObjetoLudico> obterPorCanto(Canto canto, Pageable pageable) {
        return repository.findByCantoOrderByNomeAsc(canto, pageable);
    }

    public void salvarObjeto(ObjetoLudico objetoLudico) {
        repository.save(objetoLudico);
    }

    public void removerObjeto(ObjetoLudico objetoLudico) {
        repository.delete(objetoLudico);
    }

    public Page<ObjetoLudico> obterPorCantoENome(Canto canto, String nome, Pageable pageable) {
        return repository.findByCantoAndNomeContainingOrderByNomeAsc(canto, nome, pageable);
    }

    public String obterImagemPeloId(Long id) {
        return repository.findUrlImagemById(id);
    }
}
