package com.github.throyer.brinquedoteca.modules.user.controllers.api;

import com.github.throyer.brinquedoteca.modules.pagination.models.Page;
import com.github.throyer.brinquedoteca.modules.pagination.utils.Pagination;
import com.github.throyer.brinquedoteca.modules.shared.utils.Roles;
import com.github.throyer.brinquedoteca.modules.user.dtos.UserInformation;
import com.github.throyer.brinquedoteca.modules.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/curators")
public class CuratorsController {
  private final UserRepository userRepository;
  
  @GetMapping
  public Page<UserInformation> index(
    @RequestParam(name = "page", required = false) Optional<Integer> page,
    @RequestParam(name = "size", required = false) Optional<Integer> size
  ) {
    var users = userRepository.findByRoles_name(Roles.CURATOR, Pagination.of(page, size));
    return Page.of(users).map(UserInformation::new);
  }
}
