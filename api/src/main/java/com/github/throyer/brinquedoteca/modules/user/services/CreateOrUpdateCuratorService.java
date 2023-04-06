package com.github.throyer.brinquedoteca.modules.user.services;

import com.github.throyer.brinquedoteca.modules.role.entities.Role;
import com.github.throyer.brinquedoteca.modules.role.repositories.RoleRepository;
import com.github.throyer.brinquedoteca.modules.shared.utils.Roles;
import com.github.throyer.brinquedoteca.modules.user.dtos.CreateOrUpdateUser;
import com.github.throyer.brinquedoteca.modules.user.entities.User;
import com.github.throyer.brinquedoteca.modules.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static com.github.throyer.brinquedoteca.modules.shared.utils.Templates.MANAGEMENT.CURATOR.FORM;
import static java.lang.String.format;
import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class CreateOrUpdateCuratorService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  public String createOrUpdate(
    CreateOrUpdateUser data,
    Model model,
    BindingResult validation,
    RedirectAttributes redirect
  ) {
    if (isNull(data.getId()) && userRepository.existsByEmail(data.getEmail())) {
      var html = """
          O email: <strong>%s</strong> já é utilizado.
          <br>
          Por favor digite um email diferente.
        """;

      validation.rejectValue(
        "email",
        "error.email",
        format(html, data.getEmail())
      );
    }
    
    if (validation.hasErrors()) {
      
      model.addAttribute("roles", roleRepository.findAll());
      model.addAttribute("user", data);

      return FORM;
    }
       
    List<Role> roles = new ArrayList<>();

    roles.add(roleRepository.findByName(Roles.CURATOR));
    
    if (data.getRole().equalsIgnoreCase(Roles.ADMINISTRATOR)) {
      roles.add(roleRepository.findByName(Roles.ADMINISTRATOR));
    }
        
    if (isNull(data.getId())) {
      userRepository.save(new User(data, roles));
      redirect.addFlashAttribute("cadastro", data);      
      return "redirect:/curador/formulario";
    }
 
    userRepository.findById(data.getId())
      .ifPresent(user -> {
        user.update(data, roles);
        userRepository.save(user);
      });
    
    redirect.addFlashAttribute("atualizado", data);
    return "redirect:/curador";
  }
}
