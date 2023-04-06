package com.github.throyer.brinquedoteca.modules.user.controllers.api;

import com.github.throyer.brinquedoteca.modules.user.dtos.CreateUserProps;
import com.github.throyer.brinquedoteca.modules.user.dtos.UserInformation;
import com.github.throyer.brinquedoteca.modules.user.services.CreateUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UsersController {
  private final CreateUserService createUserService;
  
  @PostMapping
  public UserInformation create(@RequestBody CreateUserProps props) {
    var created = createUserService.create(props);
    return new UserInformation(created);
  }
}
