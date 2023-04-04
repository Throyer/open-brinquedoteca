package com.github.throyer.brinquedoteca.modules.user.controllers;

import com.github.throyer.brinquedoteca.modules.shared.utils.Roles;
import com.github.throyer.brinquedoteca.modules.shared.utils.TemplatePath;
import com.github.throyer.brinquedoteca.modules.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping({
  "/curador",
  "/curador/gerenciamento"
})
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
public class ListCuratorsController {
    private UserRepository userRepository;

    @GetMapping
    public String list(Model model, Pageable pageable) {
        var page = userRepository.findByRoles_name(Roles.CURATOR, pageable);

        model.addAttribute("page", page);

        return TemplatePath.CURADOR_LISTA.getPath();
    }
}
