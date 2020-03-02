package com.github.throyer.brinquedoteca.web.controllers.util;

import java.util.Arrays;

import javax.validation.Valid;

import com.github.throyer.brinquedoteca.domain.model.Cargo;
import com.github.throyer.brinquedoteca.domain.model.Usuario;
import com.github.throyer.brinquedoteca.domain.repository.CargoRepository;
import com.github.throyer.brinquedoteca.services.CantoService;
import com.github.throyer.brinquedoteca.services.CargoService;
import com.github.throyer.brinquedoteca.services.SistemaService;
import com.github.throyer.brinquedoteca.services.UsuarioService;
import com.github.throyer.brinquedoteca.utils.TemplatePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class InstallController {

    @Autowired
    private CargoRepository cargoRepository;

    /* Serviços usados */
    @Autowired
    private SistemaService sistemaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private CantoService cantoService;
    @Autowired
    private CargoService cargoService;

    /* Cargos */
    private final static String CURADOR = "CURADOR";
    private final static String ADMINISTRADOR = "ADMINISTRADOR";

    @ModelAttribute
    public void addAttributes(Model model) {

        /* Passando o usuario logado */
        model.addAttribute("usuario", usuarioService.getUsuarioLogado());

        /* Passando cantos para o navbar */
        model.addAttribute("cantos", cantoService.obterTodos());
    }

    /**
     * Formulario de cadastro do administrador. Se o sistema já possui algum
     * administrador, o usuario é redirecionado para a pagina inicial. se não
     * exibe o formulario de cadastro de administrador.
     *
     * @param install
     * @param redirect
     * @return pagina de boas vindas ou formulario de administrador.
     */
    @GetMapping(value = "/install")
    public String formulario(Model install, RedirectAttributes redirect) {

        /**
         *
         */
        if (sistemaService.isConfigured()) {

            /* passando para o model o status de configuração */
            redirect.addFlashAttribute("config", sistemaService.isConfigured());

            /* redireciona para a pagina inicial */
            return "redirect:" + "/";

        } else {

            /* passando um novo usuario para o formulario de administrador */
            install.addAttribute("administrador", new Usuario());

            /* retornando a view do formulario de administrador */
            return TemplatePath.INSTALL.getPath();

        }

    }

    /**
     * Cadastro do primeiro administrador do sistema.
     *
     * @param administrador
     * @param result
     * @param install
     * @param redirect
     * @param confirmarSenha
     * @return pagina de boas vindas.
     */
    @PostMapping(value = "/install")
    public String salvar(@Valid @ModelAttribute("administrador") Usuario administrador,
            BindingResult result,
            Model install,
            RedirectAttributes redirect,
            @RequestParam("confirmarSenha") String confirmarSenha) {

        if (!sistemaService.isConfigured()) {

            /* Caso da senha não for confirmada */
            if (!administrador.getSenha().equals(confirmarSenha)) {

                result.rejectValue("senha", "error.senha", "Senhas não conferem."
                        + "Por favor digite novamene.");
            }

            /* Caso existam erros no install */
            if (result.hasErrors()) {

                /* Devolvendo os dados para o install */
                install.addAttribute("administrador", administrador);

                return TemplatePath.INSTALL.getPath();

            } else {

                /* Criando o cargo de ADMINISTRADOR */
                cargoRepository.save(new Cargo(ADMINISTRADOR));

                /* Criando o cargo de CURADOR */
                cargoRepository.save(new Cargo(CURADOR));

                /* cargos de adminstrador (administrador tambem pode ser curador) */
                administrador.setCargos(Arrays.asList(
                        cargoService.ObterCargoPorNome(CURADOR),
                        cargoService.ObterCargoPorNome(ADMINISTRADOR)));

                /* Salvando adminstrador */
                usuarioService.salvarUsuario(administrador);

                /* Dados do ADMINISTRADOR */
                redirect.addFlashAttribute("adminstrador", administrador);

                /* Redireciona para a pagina inicial */
                return "redirect:" + "/";
            }

        } else {

            redirect.addFlashAttribute("config", sistemaService.isConfigured());

            return "redirect:" + "/";
        }
    }
}
