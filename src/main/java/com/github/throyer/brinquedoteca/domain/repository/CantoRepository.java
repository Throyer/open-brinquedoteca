package com.github.throyer.brinquedoteca.domain.repository;

import java.util.List;

import com.github.throyer.brinquedoteca.domain.model.Canto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CantoRepository extends JpaRepository<Canto, Long> {

    Canto findByNome(String nome);

    List<Canto> findAllByOrderByNomeAsc();
}
