package com.github.throyer.brinquedoteca.web.controllers;

import java.util.Arrays;

import javax.validation.Valid;

import com.github.throyer.brinquedoteca.domain.model.Cargo;
import com.github.throyer.brinquedoteca.domain.model.Usuario;
import com.github.throyer.brinquedoteca.services.CantoService;
import com.github.throyer.brinquedoteca.services.CargoService;
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

import static java.util.Arrays.asList;

@Controller
public class CuradorController {

    /* Serviços usados */
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CantoService cantoService;

    @Autowired
    private CargoService cargoService;

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
     * Pagina inicial de Gerenciamento de Curadores. Redireciona o Administrador
     * para a pagina Gerenciar Sistema.
     *
     * @param listar
     * @return view listagem de curadores
     */
    @RequestMapping(value = { "/curador", "/curador/gerenciamento" })
    public String paginaInicial(Model listar) {
        listar.addAttribute("curadores", usuarioService.findByCargo("CURADOR"));
        return TemplatePath.CURADOR_LISTA.getPath();
    }

    /**
     * Pagina com o formulario. Para o cadastro de um novo curador.
     *
     * @param formulario
     * @return view formulario de curador
     */
    @GetMapping(value = "/curador/formulario")
    public String formulario(Model formulario) {

        /* passando cargos para o model */
        formulario.addAttribute("cargos", cargoService.obterTodos());

        /* passando novo curador com o cargo de curador para o model */
        formulario.addAttribute("curador", new Usuario(asList(cargoService.ObterCargoPorNome("CURADOR"))));

        return TemplatePath.CURADOR_FORMULARIO.getPath();
    }

    /**
     * Salvar curador.
     *
     * @param curador
     * @param result
     * @param formulario     modelo.
     * @param redirect
     * @param confirmarSenha
     * @param cargo
     * @paramirmaSenha
     * @return
     */
    @PostMapping(value = "/curador/formulario")
    public String salvar(@Valid @ModelAttribute("curador") Usuario curador, BindingResult result, Model formulario,
            RedirectAttributes redirect, @RequestParam("confirmarSenha") String confirmarSenha,
            @RequestParam("cargo") String cargo) {

        /* verificando se é uma edição ou um novo registro */
        boolean novo = curador.getId() == null;

        /* Caso exista um canto com o mesmo nome */
        if (novo && usuarioService.existeEmail(curador.getEmail())) {

            result.rejectValue("email", "error.email", "O email: <strong>" + curador.getEmail()
                    + "</strong> já é utilizado.<br>Por favor digite um email diferente.");
        }

        /* Caso da senha não for confirmada */
        if (!curador.getSenha().equals(confirmarSenha)) {

            result.rejectValue("senha", "error.senha", "Confirmação da senha incorreta. Por favor digite novamente.");
        }

        /* Caso existam erros no formulario */
        if (result.hasErrors()) {

            /* passando cargos para o model */
            formulario.addAttribute("cargos", cargoService.obterTodos());

            /* passando novo curador com o cargo de curador para o model */
            curador.setCargos(asList(cargoService.ObterCargoPorNome("CURADOR")));
            formulario.addAttribute("curador", curador);

            return TemplatePath.CURADOR_FORMULARIO.getPath();

        } else {

            Cargo cargoCurador = cargoService.ObterCargoPorNome("CURADOR");
            Cargo cargoAdministrador = cargoService.ObterCargoPorNome("ADMINISTRADOR");

            if (cargo.equals("CURADOR")) {

                /* Definindo o cargo de CURADOR */
                curador.setCargos(asList(cargoCurador));

            } else if (cargo.equals("ADMINISTRADOR")) {

                /* Definindo o cargo de CURADOR e ADMINISTRADOR */
                curador.setCargos(asList(cargoCurador, cargoAdministrador));
            }

            if (novo) {

                /* Salvando curador */
                usuarioService.salvarUsuario(curador);

                /* Passando dados desse novo edificio para o 'redirect' */
                redirect.addFlashAttribute("cadastro", curador);

                /* Redireciona para formulario novamente, para um novo cadastro */
                return "redirect:/curador/formulario";

            } else {

                /* Salvando curador */
                usuarioService.salvarUsuario(curador);

                /* Passando dados desse curador para o 'redirect' */
                redirect.addFlashAttribute("atualizado", curador);

                /* Redireciona para a lista de curadores depois da edição */
                return "redirect:/curador";

            }
        }
    }

    /**
     * Exclui um objeto lúdico no banco de dados.
     *
     * @param id       chave primaria do objeto a ser removido.
     * @param redirect
     * @return redireciona o usuario para a pagina de controle
     */
    @PostMapping(value = "/curador/remover")
    public String remover(@RequestParam("id") Long id, RedirectAttributes redirect) {

        /* Buscando o Objeto com id recebido no banco de dados */
        Usuario curador = usuarioService.obterPorId(id);

        /* removendo ele do banco */
        usuarioService.removerUsuario(curador);

        return "redirect:" + "/curador";
    }

    /**
     * Editar um objeto a partir do id.
     *
     * @param model modelo
     * @param id    chave primaria do Canto.
     * @return view 'formulario_canto_editar.html'
     */
    @GetMapping(value = "/curador/editar/{id}")
    public String editar(Model model, @PathVariable Long id) {

        /* Passando lista de cargos para o model. */
        model.addAttribute("cargos", cargoService.obterTodos());

        /* Passando curador para o model. */
        model.addAttribute("curador", usuarioService.obterPorId(id));

        return TemplatePath.CURADOR_FORMULARIO.getPath();
    }
}
