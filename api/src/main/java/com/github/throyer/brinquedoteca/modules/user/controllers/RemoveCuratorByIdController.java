package com.github.throyer.brinquedoteca.modules.user.controllers;

import com.github.throyer.brinquedoteca.modules.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
@RequestMapping(value = { "/curador", "/curador/gerenciamento" })
public class RemoveCuratorByIdController {
    private UserRepository userRepository;

    @PostMapping(value = "/curador/remover")
    public String remover(@RequestParam("id") Long id, RedirectAttributes redirect) {
        var user = userRepository.findById(id);
        user.ifPresent(this.userRepository::delete);
        return "redirect:/curador";
    }
}
