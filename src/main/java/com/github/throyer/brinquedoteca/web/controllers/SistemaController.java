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
public class SistemaController {

    /* Serviços usados */
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CantoService cantoService;

    /**
     * Alimentando a navbar.
     *
     * @param global model.
     */
    @ModelAttribute
    public void addAttributes(Model global) {

        /* Passando o usuario logado */
        global.addAttribute("usuario", usuarioService.getUsuarioLogado());

        /* Passando cantos para o navbar */
        global.addAttribute("cantos", cantoService.obterTodos());

    }

    /**
     * Pagina inicial de Gerenciamento do Sistema. Redireciona o Administrador para
     * a pagina Gerenciar Sistema.
     *
     * @param model
     * @return view gerenciar sistema
     */
    @RequestMapping(value = "/sistema")
    public String paginaInicial(Model model) {
        return TemplatePath.GERENCIAR_SISTEMA.getPath();
    }
}
