package com.github.throyer.brinquedoteca.modules.ludicobject.controllers;

import com.github.throyer.brinquedoteca.modules.ludicobject.repositories.LudicObjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@RequestMapping({
  "/objeto",
  "/objeto/gerenciamento"
})
@PreAuthorize("hasAnyAuthority('CURATOR', 'ADMINISTRADOR')")
public class RemoveLudicObjectByIdController {
    private final LudicObjectRepository repository;

    @PostMapping(value = "/remover/{id}")
    public String remover(
        @PathVariable("id") Long id,
        RedirectAttributes redirect
    ) {
        var found = repository.findById(id);

        if (found.isEmpty()) {
            //TODO: toast ludic object not found
            return "redirect:/objeto";
        }

        var ludicObject = found.get();

        repository.delete(ludicObject);
        redirect.addFlashAttribute("removeu", ludicObject);

        return "redirect:/objeto";
    }
}
