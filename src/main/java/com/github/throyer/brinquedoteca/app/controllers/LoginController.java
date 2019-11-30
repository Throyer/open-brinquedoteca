package com.github.throyer.brinquedoteca.app.controllers;

import com.github.throyer.brinquedoteca.app.utils.Templates.LOGIN;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author Renato Henrique
 */
@Controller
public class LoginController {

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("login", "active");
    }

    @GetMapping("/login")
    public String login() {
        return LOGIN.LOGIN;
    }

}
