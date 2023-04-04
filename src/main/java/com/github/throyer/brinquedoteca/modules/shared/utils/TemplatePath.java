package com.github.throyer.brinquedoteca.modules.shared.utils;

public enum TemplatePath {

    /*templates publicos*/
    INSTALL("install"),
    LOGIN("login"),
    PESQUISA("pesquisar"),
    BOAS_VINDAS("index"),
    CONTATO("contato"),
    CANTO("canto"),
    OBJETO("objeto"),
    CONTA("conta"),
    RELEASES("releases"),

    /*gerenciamentos*/
    OBJETO_LISTA("management/objetos/objeto-list"),
    OBJETO_FORMULARIO("management/objetos/objeto-form"),
    CANTO_LISTA("management/cantos/canto-list"),
    CANTO_FORMULARIO("management/cantos/canto-form"),
    CURADOR_LISTA("management/curadores/curador-list"),
    CURADOR_FORMULARIO("management/curadores/curador-form"),
    GERENCIAR_SISTEMA("management/sistema/gerenciamento-sistema"),
    
    /*templates de tutorial*/
    TUTORIAL_INICIO("tutoriais/tutorial-brinquedoteca"),
    TUTORIAL_OBJETO("tutoriais/tutorial-objeto"),
    TUTORIAL_CANTO("tutoriais/tutorial-canto"),
    TUTORIAL_EDITOR("tutoriais/tutorial-editor");

    private final String PATH;

    TemplatePath(String PATH) {
        this.PATH = PATH;
    }

    public String getPath() {
        return PATH;
    }
}
