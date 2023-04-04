package com.github.throyer.brinquedoteca.modules.user.controllers;

import com.github.throyer.brinquedoteca.modules.user.dtos.CreateOrUpdateUser;
import com.github.throyer.brinquedoteca.modules.user.entities.User;
import com.github.throyer.brinquedoteca.modules.role.repositories.RoleRepository;
import com.github.throyer.brinquedoteca.modules.shared.utils.Roles;
import com.github.throyer.brinquedoteca.modules.user.repositories.UserRepository;
import com.github.throyer.brinquedoteca.modules.user.services.CreateOrUpdateCuratorService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

import static com.github.throyer.brinquedoteca.modules.shared.utils.Templates.MANAGEMENT.CURATOR.FORM;

@Controller
@AllArgsConstructor
@RequestMapping({
    "/curador",
    "/curador/gerenciamento"
})
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
public class CreateOrEditCuratorController {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private CreateOrUpdateCuratorService service;

    @GetMapping("/formulario")
    public String newCuratorForm(Model model) {
      
      model.addAttribute("roles", roleRepository.findAll());
      model.addAttribute("user", new CreateOrUpdateUser(Roles.CURATOR));

      return FORM;
    }

    @GetMapping("/editar/{id}")
    public String editCuratorForm(Model model, @PathVariable Long id) {
      var user = userRepository.findById(id)
        .map(CreateOrUpdateUser::new);

      if (user.isEmpty()) {
        // TODO: show alert user not found
        return "redirect:/curador";
      }

      model.addAttribute("roles", roleRepository.findAll());
      model.addAttribute("user", user.get());

      return FORM;
    }

    @PostMapping(value = "/formulario")
    public String createOrUpdate(
      @Valid @ModelAttribute("user") CreateOrUpdateUser user,
      BindingResult result,
      Model model,
      RedirectAttributes redirect
    ) {
        return service.createOrUpdate(user, model, result, redirect);
    }
}
