package com.github.throyer.brinquedoteca.modules.user.dtos;

import com.github.throyer.brinquedoteca.modules.role.entities.Role;
import com.github.throyer.brinquedoteca.modules.shared.utils.Roles;
import com.github.throyer.brinquedoteca.modules.user.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateOrUpdateUser {
  public CreateOrUpdateUser(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.email = user.getEmail();
    this.role = user.getRoles().stream()
      .findFirst()
        .map(Role::getName)
      .orElse(Roles.CURATOR);
  }

  public CreateOrUpdateUser(String role) {
    this.role = role;
  }

  private Long id;
  private String name;
  private String email;
  private String role;
}
