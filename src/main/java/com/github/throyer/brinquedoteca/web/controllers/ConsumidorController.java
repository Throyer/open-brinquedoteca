package com.github.throyer.brinquedoteca.web.controllers;

import java.util.Optional;

import com.github.rjeschke.txtmark.Processor;
import com.github.throyer.brinquedoteca.domain.model.Canto;
import com.github.throyer.brinquedoteca.domain.model.ObjetoLudico;
import com.github.throyer.brinquedoteca.services.CantoService;
import com.github.throyer.brinquedoteca.services.ObjetoService;
import com.github.throyer.brinquedoteca.services.UsuarioService;
import com.github.throyer.brinquedoteca.utils.TemplatePath;
import com.github.throyer.brinquedoteca.web.controllers.util.Pager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConsumidorController {

    /* Serviços usados */
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CantoService cantoService;
    
    @Autowired
    private ObjetoService objetoService;

    private static final int BOTOES_PARA_MOSTRAR = 5; // numero maximo de botões na paginação.
    private static final int PAGINA_INICIAL = 0;
    private static final int ITENS_POR_PAGINA = 3;

    @ModelAttribute
    public void addAttributes(Model model) {
        /* Passando o usuario logado */
        model.addAttribute("usuario", usuarioService.getUsuarioLogado());

        /* Passando cantos para o navbar */
        model.addAttribute("cantos", cantoService.obterTodos());
    }

    /**
     * Pagina inicial. Redireciona o Consumidor para a pagina inicial.
     *
     * @return view 'index'.
     */
    @RequestMapping(value = { "/", "/home" })
    public String paginaInicial() {
        return TemplatePath.BOAS_VINDAS.getPath();
    }

    /**
     * Pagina inicial. Redireciona o Consumidor para a pagina sobre a Brinquedoteca.
     *
     * @return view 'contato'.
     */
    @RequestMapping(value = "/contato")
    public String contato() {
        return TemplatePath.CONTATO.getPath();
    }

    /**
     * Pagina com o formulario para sugestão de conteudo. Redireciona o Consumidor
     * para o formulario de sugestão.
     *
     * @return view 'formulario_sugestao.html'.
     */
    @GetMapping(value = "/sugestao")
    public String formularioSugestao() {
        return TemplatePath.CONTATO.getPath();
    }

    /**
     * Envia email. Envia um email com o conteudo preenchido no formulario de
     * sugestão para o endereço configurando no serviço de email.
     *
     * @return redireciona para a view 'formulario_sugestao.html'.
     */
    @PostMapping(value = "/sugestao")
    public String enviarEmailSugestao() {
        return "redirect:" + "/sugestao";
    }

    /**
     * Pagina de Canto. Redireciona o Consumidor para a pagina de um canto, contendo
     * uma lista com todos os objetos lúdicos pertencentes a este canto.
     *
     * @param id     chave primaria do canto.
     * @param pagina de objetos.
     * @param model  model de objetos.
     * @return view 'canto.html'.
     */
    @RequestMapping("/canto/exibe")
    public String exibirCanto(@RequestParam("id") Long id, @RequestParam("page") Optional<Integer> pagina,
            Model model) {

        int evalPage = (pagina.orElse(0) < 1) ? PAGINA_INICIAL : pagina.get() - 1;

        Canto canto = cantoService.obterPorId(id);

        if (canto.getDescricao().length() == 0) {
            /* Adiconando informativo de descrição vazia */
            canto.setDescricao("Descrição do canto não informada.");
        } else {
            /* Transformando markdown em HTML */
            canto.setDescricao(Processor.process(canto.getDescricao()));
        }

        Page<ObjetoLudico> objetos = objetoService.obterPorCanto(canto, PageRequest.of(evalPage, ITENS_POR_PAGINA));
        Pager pager = new Pager(objetos.getTotalPages(), objetos.getNumber(), BOTOES_PARA_MOSTRAR);

        model.addAttribute("canto", canto);
        model.addAttribute("objetos", objetos);
        model.addAttribute("objetoList", objetos.getContent());
        model.addAttribute("pager", pager);

        return TemplatePath.CANTO.getPath();
    }

    /**
     * Pagina de Objeto Lúdico. exibe os dados de um objeto ludico.
     *
     * @param exibirObjeto
     * @param id           chave primaria do objeto.
     * @return view 'objeto.html'.
     */
    @GetMapping(value = "/objeto/view/{id}")
    public String exibirObjeto(Model exibirObjeto, @PathVariable Long id) {

        /* Obtendo Objeto Lúdifco */
        ObjetoLudico objeto = objetoService.obterPorId(id);

        /* passando Markdown para HTML */
        objeto.setDescricao(Processor.process(objeto.getDescricao()));
        objeto.setReferencias(Processor.process(objeto.getReferencias()));

        /* Passando objeto para a view */
        exibirObjeto.addAttribute("objeto", objeto);

        /* Passando canto para a view */
        exibirObjeto.addAttribute("canto", objeto.getCanto());

        return TemplatePath.OBJETO.getPath();
    }

    /**
     * 
     * @param id
     * @param nome
     * @param model
     * @return
     */
    @GetMapping("/canto/pesquisa")
    private String pesquisarPorObjetos(@RequestParam("id") Long id, @RequestParam("nome") String nome, Model model) {

        int evalPage = 0;

        Canto canto = cantoService.obterPorId(id);

        if (canto.getDescricao().length() == 0) {
            /* Adiconando informativo de descrição vazia */
            canto.setDescricao("Descrição do canto não informada.");
        } else {
            /* Transformando markdown em HTML */
            canto.setDescricao(Processor.process(canto.getDescricao()));
        }

        Page<ObjetoLudico> objetos = objetoService.obterPorCantoENome(canto, nome,
                PageRequest.of(evalPage, ITENS_POR_PAGINA));
        Pager pager = new Pager(objetos.getTotalPages(), objetos.getNumber(), BOTOES_PARA_MOSTRAR);

        model.addAttribute("canto", canto);
        model.addAttribute("objetos", objetos);
        model.addAttribute("objetoList", objetos.getContent());
        model.addAttribute("pager", pager);

        return TemplatePath.CANTO.getPath();
    }
}
