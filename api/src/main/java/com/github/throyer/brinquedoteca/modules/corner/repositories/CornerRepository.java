package com.github.throyer.brinquedoteca.modules.corner.repositories;

import java.util.List;

import com.github.throyer.brinquedoteca.modules.corner.entities.Corner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;

@Repository
public interface CornerRepository extends JpaRepository<Corner, Long> {
    Corner findByName(String name);
    Boolean existsByName(String name);
    List<Corner> findAllByOrderByNameAsc();
}
