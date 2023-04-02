package com.github.throyer.brinquedoteca.web.controllers;

import javax.validation.Valid;

import com.github.throyer.brinquedoteca.domain.model.ObjetoLudico;
import com.github.throyer.brinquedoteca.services.CantoService;
import com.github.throyer.brinquedoteca.services.ObjetoService;
import com.github.throyer.brinquedoteca.services.StorageService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ObjetoController {

    /* Serviços usados */
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ObjetoService objetoService;

    @Autowired
    private CantoService cantoService;

    @Autowired
    StorageService storage;

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
     * Pagina inicial de Gerenciamento de Objetos Lúdicos. Redireciona o Curador
     * para a pagina Gerenciar Objetos Lúdicos.
     *
     * @param listagem
     * @return view index
     */
    @RequestMapping(value = { "/objeto", "/objeto/gerenciamento" })
    public String paginaInicial(Model listagem) {

        /* passando todos objetos lúdicos para o model */
        listagem.addAttribute("objetos", objetoService.obterTodos());

        return TemplatePath.OBJETO_LISTA.getPath();
    }

    /**
     * Formulario de cadastro de Objato. Direciona o Curador para o formulario de
     * cadastro de um novo objeto lúdico.
     *
     * @param formulario
     * @return view objeto/formulario
     */
    @GetMapping(value = "/objeto/formulario")
    public String formulario(Model formulario) {

        formulario.addAttribute("objetoLudico", new ObjetoLudico());

        return TemplatePath.OBJETO_FORMULARIO.getPath();
    }

    /**
     * Salvar Objeto Lúdico. observação importante: sempre deixar o BindingResult
     * logo em seguida de @Valid objeto ludico, se não vai dar erro (400 bad
     * request) na validação e não vai exibir as mensagens no formulario.
     *
     * @param objetoLudico
     * @param result       resultado do formulario.
     * @param formulario   model do formulario de objeto lúdico.
     * @param imagem       arquivo de imagem de capa do objeto lúdico.
     * @param redirect
     * @return redireciona para o formulario limpo, para um novo cadastro.
     */
    @PostMapping(value = "/objeto/formulario")
    public String salvar(
        @Valid @ModelAttribute("objetoLudico") ObjetoLudico objetoLudico,
        BindingResult result,
        Model formulario,
        @RequestParam("imagem") MultipartFile imagem,
        RedirectAttributes redirect
    ) {

        /* verificando se é uma edição ou um novo registro */
        boolean novo = objetoLudico.getId() == null;

        /* Caso existam erros no formulario */
        if (result.hasErrors()) {

            formulario.addAttribute("ObjetoLudico", objetoLudico);

            return TemplatePath.OBJETO_FORMULARIO.getPath();

        } else {

            if (novo) {

                /* Passando canto */
                objetoLudico.setCanto(cantoService.obterPorId(objetoLudico.getCanto().getId()));

                /* Passando usuario */
                objetoLudico.setCreatedBy(usuarioService.getUsuarioLogado());

                /* Salvando edificio */
                objetoService.salvarObjeto(objetoLudico);

                /* Passando dados desse novo edificio para o 'redirect' */
                redirect.addFlashAttribute("cadastro", objetoLudico);

                /* Redireciona para formulario novamente, para um novo cadastro */
                return "redirect:" + "/objeto/formulario";

            } else {

                if (imagem.getContentType().contains("image") && !imagem.getOriginalFilename().isEmpty()) {
                    storage.armazenarCapaObjeto(imagem, objetoLudico.getId());
                    objetoLudico.setUrlImagem("/imagem/objeto/capa/" + objetoLudico.getId());
                } else {
                    objetoLudico.setUrlImagem(objetoService.obterImagemPeloId(objetoLudico.getId()));
                }

                /* Passando canto */
                objetoLudico.setCanto(cantoService.obterPorId(objetoLudico.getCanto().getId()));

                /* Passando usuario */
                objetoLudico.setCreatedBy(usuarioService.getUsuarioLogado());

                /* Salvando edificio */
                objetoService.salvarObjeto(objetoLudico);

                /* Passando dados desse edificio para o 'redirect' */
                redirect.addFlashAttribute("atualizado", objetoLudico);

                /* Redireciona para a lista de edificios depois da edição */
                return "redirect:" + "/objeto";
            }
        }

    }

    /**
     * Editar um objeto a partir do id.
     *
     * @param model modelo
     * @param id    chave primaria do Canto.
     * @return view objeto/formulario
     */
    @GetMapping(value = "/objeto/editar/{id}")
    public String editar(Model model, @PathVariable Long id) {

        /* Passando canto para o model. */
        model.addAttribute("objetoLudico", objetoService.obterPorId(id));

        return TemplatePath.OBJETO_FORMULARIO.getPath();
    }

    /**
     * Remover um Objeto a partir do id.
     *
     * @param id       chave primaria do Objeto Lúdico.
     * @param redirect listagem dos objetos
     * @return redirecionado para listagem de objetos ludicos.
     */
    @PostMapping(value = "/objeto/remover")
    public String remover(@RequestParam("id") Long id, RedirectAttributes redirect) {

        System.out.println(id);

        redirect.addFlashAttribute("removeu", objetoService.obterPorId(id));

        /* Removendo Objeto. */
        objetoService.removerObjeto(objetoService.obterPorId(id));

        /* redirecionado para URL gerenciar objetos */
        return "redirect:" + "/objeto";
    }
}
