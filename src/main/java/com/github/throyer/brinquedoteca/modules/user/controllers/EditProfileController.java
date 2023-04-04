package com.github.throyer.brinquedoteca.modules.user.controllers;

import com.github.throyer.brinquedoteca.modules.shared.utils.TemplatePath;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/conta")
@AllArgsConstructor
public class EditProfileController {
    @GetMapping
    public String conta(Model model) {
        return TemplatePath.CONTA.getPath();
    }
}
