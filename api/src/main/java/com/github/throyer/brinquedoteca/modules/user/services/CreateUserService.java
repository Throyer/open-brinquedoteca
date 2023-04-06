package com.github.throyer.brinquedoteca.modules.user.services;

import com.github.throyer.brinquedoteca.modules.role.entities.Role;
import com.github.throyer.brinquedoteca.modules.role.repositories.RoleRepository;
import com.github.throyer.brinquedoteca.modules.shared.utils.Roles;
import com.github.throyer.brinquedoteca.modules.user.dtos.CreateUserProps;
import com.github.throyer.brinquedoteca.modules.user.entities.User;
import com.github.throyer.brinquedoteca.modules.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CreateUserService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  
  public User create(CreateUserProps props) {
    List<Role> roles = new ArrayList<>();

    roles.add(roleRepository.findByName(Roles.CURATOR));

    if (props.getRole().equalsIgnoreCase(Roles.ADMINISTRATOR)) {
      roles.add(roleRepository.findByName(Roles.ADMINISTRATOR));
    }

    return userRepository.save(new User(props, roles));
  }  
}
