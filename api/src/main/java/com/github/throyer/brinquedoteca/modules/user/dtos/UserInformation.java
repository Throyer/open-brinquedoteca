package com.github.throyer.brinquedoteca.modules.user.dtos;

import com.github.throyer.brinquedoteca.modules.role.entities.Role;
import com.github.throyer.brinquedoteca.modules.user.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserInformation {
  private Long id;
  private String name;
  private String email;
  private List<String> roles;

  public UserInformation(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.email = user.getEmail();
    this.roles = user.getRoles().stream()
      .map(Role::getAuthority)
      .toList();
  }
}
