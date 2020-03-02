package com.github.throyer.brinquedoteca.web.controllers;

import com.github.throyer.brinquedoteca.services.CantoService;
import com.github.throyer.brinquedoteca.services.UsuarioService;
import com.github.throyer.brinquedoteca.utils.TemplatePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TutorialController {

    /* Serviços usados */
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private CantoService cantoService;

    /**
     * Alimentando a navbar.
     *
     * @param global model
     */
    @ModelAttribute
    public void addAttributes(Model global) {

        /* Passando o usuario logado */
        global.addAttribute("usuario", usuarioService.getUsuarioLogado());

        /* Passando cantos para o navbar */
        global.addAttribute("cantos", cantoService.obterTodos());

    }

    @RequestMapping("/tutorial")
    public String navegacao(RedirectAttributes redirect) {
        return TemplatePath.TUTORIAL_INICIO.getPath();
    }

    @RequestMapping("/tutorial/objeto")
    public String objeto() {
        return TemplatePath.TUTORIAL_OBJETO.getPath();
    }

    @RequestMapping("/tutorial/canto")
    public String canto() {
        return TemplatePath.TUTORIAL_CANTO.getPath();
    }

    @RequestMapping("/tutorial/editor")
    public String editor() {
        return TemplatePath.TUTORIAL_EDITOR.getPath();
    }

    @RequestMapping("/releases")
    public String curador() {
        return TemplatePath.RELEASES.getPath();
    }
}
