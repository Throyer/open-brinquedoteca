package com.github.throyer.brinquedoteca.modules.role.repositories;

import com.github.throyer.brinquedoteca.modules.role.entities.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
