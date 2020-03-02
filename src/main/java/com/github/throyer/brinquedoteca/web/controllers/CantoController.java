package com.github.throyer.brinquedoteca.web.controllers;

import javax.validation.Valid;

import com.github.throyer.brinquedoteca.domain.model.Canto;
import com.github.throyer.brinquedoteca.services.CantoService;
import com.github.throyer.brinquedoteca.services.UsuarioService;
import com.github.throyer.brinquedoteca.utils.TemplatePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CantoController {

    /* Serviços usados */
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CantoService cantoService;

    /**
     *
     * @param global model global para as views de canto.
     */
    @ModelAttribute
    public void addAttributes(Model global) {
        /* Passando o usuario logado */
        global.addAttribute("usuario", usuarioService.getUsuarioLogado());

        /* Passando cantos para o navbar */
        global.addAttribute("cantos", cantoService.obterTodos());
    }

    /**
     * Pagina inicial de Gerenciamento de Cantos. Direciona o Curador para a
     * pagina Gerenciar Cantos.
     *
     * @return view 'listagem de cantos'
     */
    @RequestMapping(value = {"/canto", "/canto/gerenciamento"})
    public String index() {
        return TemplatePath.CANTO_LISTA.getPath();
    }

    /**
     * Formulario de cadastro de Canto. Direciona o Curador para o formulario de
     * cadastro de um novo canto.
     *
     * @param formulario.
     * @return view 'formulario de canto vazio'
     */
    @GetMapping("/canto/formulario")
    public String formulario(Model formulario) {

        /* novo canto vazio para o formulario */
        formulario.addAttribute("canto_", new Canto());

        return TemplatePath.CANTO_FORMULARIO.getPath();
    }

    /**
     * Salvar Canto.
     *
     * @param result
     * @param canto
     * @param formulario
     * @param redirect
     * @return
     */
    @PostMapping("/canto/formulario")
    public String salvar(@Valid Canto canto, BindingResult result, Model formulario, RedirectAttributes redirect) {

        /* verificando se é uma edição ou um novo registro */
        boolean novo = canto.getId() == null;

        /* Caso exista um canto com o mesmo nome */
        if (novo && cantoService.nomeExiste(canto.getNome())) {
            
            result.rejectValue("nome", "error.nome", "Já existe um canto com o nome <strong>"
                    + canto.getNome() + "</strong> .<br>Por favor digite um nome diferente.");
        }

        /* Caso existam erros no formulario */
        if (result.hasErrors()) {
            
            /* Devolvendo os dados para o formulario */
            formulario.addAttribute("canto_", canto);
            
            return TemplatePath.CANTO_FORMULARIO.getPath();

        } else {

            if (novo) {
                
                /* Passando usuario */
                canto.setUsuario(usuarioService.getUsuarioLogado());
                
                /*Salvando canto*/
                cantoService.salvarCanto(canto);
                
                /* Passando dados desse novo edificio para o 'redirect' */
                redirect.addFlashAttribute("cadastro", canto);
                
                /* Redireciona para formulario novamente, para um novo cadastro */
                return "redirect:/canto/formulario";
                
            } else {
                
                /* Passando usuario */
                canto.setUsuario(usuarioService.getUsuarioLogado());
                
                /*Salvando edificio*/
                cantoService.salvarCanto(canto);

                /* Passando dados desse edificio para o 'redirect' */
                redirect.addFlashAttribute("atualizado", canto);

                /* Redireciona para a lista de edificios depois da edição */
                return "redirect:/canto";
            }
        }
    }

    /**
     * Remover Canto.
     *
     * @param id chave primaria do Canto.
     * @param redirect
     * @return redirecionado para URL '/curador/gerenciar/canto'
     */
    @PostMapping("/canto/remover")
    public String remover(@RequestParam("id") Long id, RedirectAttributes redirect) {
        
        redirect.addFlashAttribute("removeu", cantoService.obterPorId(id));

        /* Removendo Canto. */
        cantoService.removerCanto(cantoService.obterPorId(id));

        /* redirecionado para URL gerenciar cantos */
        return "redirect:" + "/canto";
    }

    /**
     * Editar um objeto a partir do id.
     *
     * @param formulario modelo
     * @param id chave primaria do Canto.
     * @return view 'formulario de canto populado'
     */
    @GetMapping(value = "/canto/editar/{id}")
    public String editar(Model formulario, @PathVariable Long id) {

        /* Passando canto para o model. */
        formulario.addAttribute("canto_", cantoService.obterPorId(id));

        return TemplatePath.CANTO_FORMULARIO.getPath();
    }
}
