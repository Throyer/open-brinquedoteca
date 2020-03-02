package com.github.throyer.brinquedoteca.domain.repository;

import com.github.throyer.brinquedoteca.domain.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

}
