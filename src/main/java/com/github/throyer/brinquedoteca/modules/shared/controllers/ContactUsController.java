package com.github.throyer.brinquedoteca.modules.shared.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.github.throyer.brinquedoteca.modules.shared.utils.Templates.PUBLIC.CONTACT_US;

@Controller
@AllArgsConstructor
@RequestMapping("/contato")
public class ContactUsController {
    @GetMapping
    public String contact() {
        return CONTACT_US;
    }
}
