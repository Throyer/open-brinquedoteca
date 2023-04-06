package com.github.throyer.brinquedoteca.modules.corner.controllers;

import com.github.throyer.brinquedoteca.modules.corner.entities.Corner;
import com.github.throyer.brinquedoteca.modules.corner.repositories.CornerRepository;
import com.github.throyer.brinquedoteca.modules.corner.services.CreateOrUpdateCornerService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.github.throyer.brinquedoteca.modules.shared.utils.Templates.MANAGEMENT.CORNER.FORM;

@Controller
@AllArgsConstructor
@RequestMapping({
  "/canto",
  "/canto/gerenciamento"
})
@PreAuthorize("hasAnyAuthority('CURATOR', 'ADMINISTRADOR')")
public class CreateOrEditCornerController {
    private final CornerRepository cornerRepository;
    private final CreateOrUpdateCornerService createOrUpdateService;

    @GetMapping("/formulario")
    public String createCorner(Model model) {
        model.addAttribute("canto_", new Corner());
        return FORM;
    }

    @GetMapping("/editar/{id}")
    public String editCorner(Model model, @PathVariable Long id) {
        var found = cornerRepository.findById(id);

        if (found.isEmpty()) {
            return "redirect:/canto";
        }

        model.addAttribute("canto_", found.get());
        return FORM;
    }

    @PostMapping("/formulario")
    public String createOrUpdate(
        Corner corner,
        BindingResult result,
        Model formulario,
        RedirectAttributes redirect
    ) {
        return createOrUpdateService.createOrUpdate(
            corner,
            result,
            formulario,
            redirect
        );
    }
}
