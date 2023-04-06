package com.github.throyer.brinquedoteca.modules.authentication.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.github.throyer.brinquedoteca.modules.shared.utils.Templates.PUBLIC.LOGIN;

@Controller
@AllArgsConstructor
@RequestMapping("/login")
public class AuthenticationController {
    @GetMapping
    public String loginForm() {
      return LOGIN;
    }
}
