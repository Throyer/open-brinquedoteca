package com.github.throyer.brinquedoteca.modules.role.repositories;

import com.github.throyer.brinquedoteca.modules.role.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
  Optional<Role> findOptionalByName(String name);
}
