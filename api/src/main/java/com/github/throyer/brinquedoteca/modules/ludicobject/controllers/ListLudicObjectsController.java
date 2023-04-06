package com.github.throyer.brinquedoteca.modules.ludicobject.controllers;

import com.github.throyer.brinquedoteca.modules.ludicobject.repositories.LudicObjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.github.throyer.brinquedoteca.modules.shared.utils.Templates.MANAGEMENT.LUDIC_OBJECT.LIST;

@Controller
@AllArgsConstructor
@RequestMapping({
  "/objeto",
  "/objeto/gerenciamento"
})
@PreAuthorize("hasAnyAuthority('CURATOR', 'ADMINISTRADOR')")
public class ListLudicObjectsController {
    private final LudicObjectRepository repository;

    @GetMapping
    public String list(Model model, Pageable pageable) {
        model.addAttribute("page", repository.findAll(pageable));
      return LIST;
    }
}
