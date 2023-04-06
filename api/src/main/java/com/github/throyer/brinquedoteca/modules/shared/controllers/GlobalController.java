package com.github.throyer.brinquedoteca.modules.shared.controllers;

import com.github.throyer.brinquedoteca.modules.corner.repositories.CornerRepository;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@AllArgsConstructor
public class GlobalController {
    private final CornerRepository cornerRepository;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("cantos", cornerRepository.findAll());
    }
}
