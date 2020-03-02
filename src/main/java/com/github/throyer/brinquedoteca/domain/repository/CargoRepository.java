package com.github.throyer.brinquedoteca.domain.repository;

import com.github.throyer.brinquedoteca.domain.model.Cargo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    
    Cargo findByNome(String nome);
    
}
