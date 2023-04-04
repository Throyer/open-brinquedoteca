package com.github.throyer.brinquedoteca.modules.user.services;

import com.github.throyer.brinquedoteca.modules.role.entities.Role;
import com.github.throyer.brinquedoteca.modules.role.repositories.RoleRepository;
import com.github.throyer.brinquedoteca.modules.shared.utils.Roles;
import com.github.throyer.brinquedoteca.modules.user.entities.User;
import com.github.throyer.brinquedoteca.modules.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    Model formulario,
    BindingResult result,
    RedirectAttributes redirect,
    User curador,
    String confirmarSenha,
    String cargo
  ) {
    boolean novo = isNull(curador.getId());

    if (userRepository.existsByEmail(curador.getEmail())) {
      var html = """
          O email: <strong>%s</strong> já é utilizado.
          <br>
          Por favor digite um email diferente.
        """;

      result.rejectValue(
        "email",
        "error.email",
        format(html, curador.getEmail())
      );
    }

    if (!curador.getPassword().equals(confirmarSenha)) {

      result.rejectValue(
        "senha",
        "error.senha",
        "Confirmação da senha incorreta. Por favor digite novamente."
      );
    }

    if (result.hasErrors()) {
      
      formulario.addAttribute("cargos", roleRepository.findAll());
      curador.setRoles(List.of(roleRepository.findByName(Roles.CURATOR)));
      formulario.addAttribute("curador", curador);

      return FORM;
    } else {

      Role roleCurador = roleRepository.findByName(Roles.CURATOR);
      Role roleAdministrador = roleRepository.findByName(Roles.ADMINISTRATOR);

      if (cargo.equals(Roles.CURATOR)) {
        curador.setRoles(List.of(roleCurador));
      } else if (cargo.equals(Roles.ADMINISTRATOR)) {
        curador.setRoles(List.of(roleCurador, roleAdministrador));
      }

      userRepository.save(curador);

      if (novo) {
        redirect.addFlashAttribute("cadastro", curador);
        return "redirect:/curador/formulario";
      } else {
        redirect.addFlashAttribute("atualizado", curador);
        return "redirect:/curador";
      }
    }
  }
}
