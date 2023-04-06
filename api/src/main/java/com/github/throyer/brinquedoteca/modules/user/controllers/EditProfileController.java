package com.github.throyer.brinquedoteca.modules.user.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.github.throyer.brinquedoteca.modules.shared.utils.Templates.MANAGEMENT.USER.PROFILE;

@Controller
@RequestMapping("/conta")
@AllArgsConstructor
public class EditProfileController {
    @GetMapping
    public String conta(Model model) {
        return PROFILE;
    }
}
