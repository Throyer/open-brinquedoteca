package com.github.throyer.brinquedoteca.web.controllers;

import com.github.throyer.brinquedoteca.services.CantoService;
import com.github.throyer.brinquedoteca.services.UsuarioService;
import com.github.throyer.brinquedoteca.utils.TemplatePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    /* Serviços usados */
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CantoService cantoService;

    /**
     * Alimentando a navbar.
     *
     * @param global global
     */
    @ModelAttribute
    public void addAttributes(Model global) {
        /* Passando o usuario logado */
        global.addAttribute("usuario", usuarioService.getUsuarioLogado());

        /* Passando cantos para o navbar */
        global.addAttribute("cantos", cantoService.obterTodos());
    }

    /**
     * Pagina de Login. Se o usuario já estiver logado ele é redirecionado para
     * a pagina inicial.
     *
     * @return view login.
     */
    @RequestMapping("/login")
    public String login() {

        boolean logado = (usuarioService.getUsuarioLogado() != null);

        if (logado) {
            return TemplatePath.BOAS_VINDAS.getPath();
        } else {
            return TemplatePath.LOGIN.getPath();
        }
    }
}
