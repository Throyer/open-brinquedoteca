package com.github.throyer.brinquedoteca.app.controllers;

import com.github.throyer.brinquedoteca.app.utils.Templates.MAIN;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("home", "active");
    }

    @RequestMapping("/")
    public String Index() {
        return MAIN.INDEX;
    }
}
