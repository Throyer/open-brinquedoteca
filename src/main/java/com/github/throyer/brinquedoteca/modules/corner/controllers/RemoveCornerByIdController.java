package com.github.throyer.brinquedoteca.modules.corner.controllers;

import com.github.throyer.brinquedoteca.modules.corner.repositories.CornerRepository;
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
  "/canto",
  "/canto/gerenciamento"
})
@PreAuthorize("hasAnyAuthority('CURATOR', 'ADMINISTRADOR')")
public class RemoveCornerByIdController {
    private final CornerRepository repository;

    @PostMapping("/remover/{id}")
    public String remover(
        @PathVariable("id") Long id,
        RedirectAttributes redirect
    ) {
        var found = repository.findById(id);

        if (found.isEmpty()) {
            //TODO: toast corner not found
            return "redirect:/canto";
        }

        var corner = found.get();

        repository.delete(corner);
        redirect.addFlashAttribute("removeu", corner);

        return "redirect:" + "/canto";
    }
}
