package com.github.throyer.brinquedoteca.modules.user.repositories;

import com.github.throyer.brinquedoteca.modules.user.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOptionalByEmail(String email);
    Boolean existsByEmail(String email);
    Page<User> findByRoles_name(String nome, Pageable pageable);
}
