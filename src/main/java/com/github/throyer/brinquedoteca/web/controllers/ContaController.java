package com.github.throyer.brinquedoteca.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.github.throyer.brinquedoteca.domain.model.Usuario;
import com.github.throyer.brinquedoteca.services.CantoService;
import com.github.throyer.brinquedoteca.services.UsuarioService;
import com.github.throyer.brinquedoteca.utils.TemplatePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContaController {

    /* Serviços usados */
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private CantoService cantoService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @ModelAttribute
    public void addAttributes(Model conta) {

        conta.addAttribute("user_dados", usuarioService.getUsuarioLogado());

        conta.addAttribute("user_email", usuarioService.getUsuarioLogado());

        conta.addAttribute("user_senha", usuarioService.getUsuarioLogado());

        /* Passando o usuario logado */
        conta.addAttribute("usuario", usuarioService.getUsuarioLogado());

        /* Passando cantos para o navbar */
        conta.addAttribute("cantos", cantoService.obterTodos());
    }

    /**
     *
     * @param conta
     * @return
     */
    @RequestMapping(value = "/conta")
    public String conta(Model conta) {

        conta.addAttribute("user_dados", usuarioService.getUsuarioLogado());

        conta.addAttribute("user_email", usuarioService.getUsuarioLogado());

        conta.addAttribute("user_senha", usuarioService.getUsuarioLogado());

        return TemplatePath.CONTA.getPath();
    }

    /**
     *
     * @param conta
     * @return
     */
    @PostMapping(value = "/conta/mudar-senha")
    public String mudarSenha(Model conta) {
        return TemplatePath.CONTA.getPath();
    }

    /**
     *
     * @param usuario
     * @param redirect
     * @param result
     * @param formulario
     * @return
     */
    @PostMapping(value = "/conta/atualizar-email")
    public String atualizarEmail(@Valid @ModelAttribute("user_email") Usuario usuario, BindingResult result,
            Model formulario, RedirectAttributes redirect) {

        /* Caso exista um canto com o mesmo nome */
        if (usuarioService.existeEmail(usuario.getEmail())) {

            result.rejectValue("email", "error.email", "O email: <strong>" + usuario.getEmail()
                    + "</strong> já é utilizado.<br>Por favor digite um email diferente.");

        }

        /* Caso existam erros no formulario */
        if (result.hasErrors()) {

            formulario.addAttribute("perfil", true);

            return TemplatePath.CONTA.getPath();

        } else {

            Usuario salvar = usuarioService.obterPorId(usuario.getId());

            salvar.setEmail(usuario.getEmail());

            /* Salvando curador */
            usuarioService.atualizarUsuario(salvar);

            /* Redireciona para a lista de curadores depois da edição */
            return "redirect:" + "/logout";

        }
    }

    /**
     *
     * @param usuario
     * @param redirect
     * @param result
     * @param formulario
     * @return
     */
    @PostMapping(value = "/conta/atualizar-dados")
    public String atualizarDados(@Valid @ModelAttribute("user_dados") Usuario usuario, BindingResult result,
            Model formulario, RedirectAttributes redirect) {

        /* Caso existam erros no formulario */
        if (result.hasErrors()) {

            formulario.addAttribute("perfil", true);

            return TemplatePath.CONTA.getPath();

        } else {

            Usuario salvar = usuarioService.obterPorId(usuario.getId());

            salvar.setNome(usuario.getNome());

            salvar.setSobrenome(usuario.getSobrenome());

            /* Salvando curador */
            usuarioService.atualizarUsuario(salvar);

            /* Passando dados desse curador para o 'redirect' */
            redirect.addFlashAttribute("atualizado", true);

            /* Redireciona para a lista de curadores depois da edição */
            return "redirect:" + "/conta";

        }
    }

    /**
     *
     * @param redirect
     * @param result
     * @param senha
     * @param formulario
     * @param confirmarSenha
     * @param senhaAnterior
     * @return
     */
    @PostMapping(value = "/conta/atualizar-senha")
    public String atualizarSenha(Model formulario, RedirectAttributes redirect,
            @RequestParam("senhaAnterior") String senhaAnterior, @RequestParam("senha") String senha,
            @RequestParam("confirmarSenha") String confirmarSenha) {

        List<String> erros = new ArrayList<>();

        /* Caso exista um canto com o mesmo nome */
        if (!encoder.matches(senhaAnterior, usuarioService.getUsuarioLogado().getSenha())) {

            erros.add("Senha anterior incorreta.");

        }

        /* Caso da senha não for confirmada */
        if (!senha.equals(confirmarSenha)) {

            erros.add("Confirmação de senha incorreta. Por favor digite novamene.");
        }

        /* Caso existam erros no formulario */
        if (!erros.isEmpty()) {

            formulario.addAttribute("erros", erros);

            formulario.addAttribute("seguranca", true);

            return TemplatePath.CONTA.getPath();

        } else {

            Usuario salvar = usuarioService.getUsuarioLogado();

            salvar.setSenha(senha);

            /* Salvando curador */
            usuarioService.salvarUsuario(salvar);

            /* Redireciona para a lista de curadores depois da edição */
            return "redirect:" + "/logout";

        }
    }
}
