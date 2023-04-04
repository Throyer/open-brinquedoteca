package com.github.throyer.brinquedoteca.modules.corner.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.github.throyer.brinquedoteca.modules.shared.utils.Templates.MANAGEMENT.CORNER.LIST;

@Controller
@AllArgsConstructor
@RequestMapping({
  "/canto",
  "/canto/gerenciamento"
})
@PreAuthorize("hasAnyAuthority('CURATOR', 'ADMINISTRADOR')")
public class ListConersController {
    @GetMapping
    public String index() {
      return LIST;
    }
}
