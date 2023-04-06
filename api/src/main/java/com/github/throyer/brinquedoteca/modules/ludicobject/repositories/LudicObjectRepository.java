package com.github.throyer.brinquedoteca.modules.ludicobject.repositories;

import com.github.throyer.brinquedoteca.modules.corner.entities.Corner;
import com.github.throyer.brinquedoteca.modules.ludicobject.entities.LudicObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LudicObjectRepository extends JpaRepository<LudicObject, Long> {
    Page<LudicObject> findByCornerOrderByNameAsc(Corner corner, Pageable pageable);
    Page<LudicObject> findByCornerAndNameContainingOrderByNameAsc(Corner corner, String nome, Pageable pageable);
}
