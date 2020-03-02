package com.github.throyer.brinquedoteca.domain.repository;

import com.github.throyer.brinquedoteca.domain.model.Canto;
import com.github.throyer.brinquedoteca.domain.model.ObjetoLudico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjetoLudicoRepository extends JpaRepository<ObjetoLudico, Long> {

    Page<ObjetoLudico> findByCantoOrderByNomeAsc(Canto canto, Pageable pageable);

    Page<ObjetoLudico> findByCantoAndNomeContainingOrderByNomeAsc(Canto canto, String nome, Pageable pageable);
    
    @Query("SELECT o.UrlImagem FROM ObjetoLudico o where o.id = :id")
    String findUrlImagemById(@Param("id") Long id);
}
