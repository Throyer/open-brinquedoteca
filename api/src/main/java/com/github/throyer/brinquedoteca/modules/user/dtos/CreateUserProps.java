package com.github.throyer.brinquedoteca.modules.user.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserProps {
  private String name;
  private String email;
  private String role;
}
