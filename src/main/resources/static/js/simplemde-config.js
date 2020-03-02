/**
 * Configurações do editor de Markdown SimpleMDE.
 * mais configurações em: https://github.com/sparksuite/simplemde-markdown-editor#configuration
 * @type SimpleMDE
 */
var descricao = new SimpleMDE(
        {element: document.getElementById("editor-markdown-descricao"),
            toolbar: [{
                    name: "negrito",
                    action: SimpleMDE.toggleBold,
                    className: "icone-negrito",
                    title: "Negrito"
                }, {
                    name: "italico",
                    action: SimpleMDE.toggleItalic,
                    className: "fas fa-italic",
                    title: "Italico"
                }, {
                    name: "titulo",
                    action: SimpleMDE.toggleHeadingSmaller,
                    className: "icone-titulo",
                    title: "Titulo"
                }, "|", {
                    name: "citacao",
                    action: SimpleMDE.toggleBlockquote,
                    className: "fas fa-quote-left",
                    title: "Citação"
                }, {
                    name: "lista",
                    action: SimpleMDE.toggleUnorderedList,
                    className: "fas fa-list-ul",
                    title: "Lista"
                }, "|", {
                    name: "link",
                    action: SimpleMDE.drawLink,
                    className: "fas fa-link",
                    title: "Inserir link"
                }, {
                    name: "imagem",
                    action: SimpleMDE.drawImage,
                    className: "far fa-image",
                    title: "Inserir imagem"
                }, "|", {
                    name: "visualizar",
                    action: SimpleMDE.togglePreview,
                    className: "far fa-eye",
                    title: "Visualizar resultado"
                }, "side-by-side", "fullscreen", "|", {
                    name: "ajuda",
                    action: function customFunction(editor) {
                        window.open("/tutorial/editor", "_blank");
                    },
                    className: "fas fa-question-circle",
                    title: "Abrir ajuda"
                }
            ],
            autoDownloadFontAwesome: false,
            spellChecker: false
        }
);

var referencias = new SimpleMDE(
        {element: document.getElementById("editor-markdown-referencias"),
            toolbar: [{
                    name: "negrito",
                    action: SimpleMDE.toggleBold,
                    className: "icone-negrito",
                    title: "Negrito"
                }, {
                    name: "italico",
                    action: SimpleMDE.toggleItalic,
                    className: "fas fa-italic",
                    title: "Italico"
                }, {
                    name: "titulo",
                    action: SimpleMDE.toggleHeadingSmaller,
                    className: "icone-titulo",
                    title: "Titulo"
                }, "|", {
                    name: "citacao",
                    action: SimpleMDE.toggleBlockquote,
                    className: "fas fa-quote-left",
                    title: "Citação"
                }, {
                    name: "lista",
                    action: SimpleMDE.toggleUnorderedList,
                    className: "fas fa-list-ul",
                    title: "Lista"
                }, "|", {
                    name: "link",
                    action: SimpleMDE.drawLink,
                    className: "fas fa-link",
                    title: "Inserir link"
                }, {
                    name: "imagem",
                    action: SimpleMDE.drawImage,
                    className: "far fa-image",
                    title: "Inserir imagem"
                }, "|", {
                    name: "visualizar",
                    action: SimpleMDE.togglePreview,
                    className: "far fa-eye",
                    title: "Visualizar resultado"
                }, "side-by-side", "fullscreen", "|", {
                    name: "ajuda",
                    action: function customFunction(editor) {
                        window.open("/tutorial/editor", "_blank");
                    },
                    className: "fas fa-question-circle",
                    title: "Abrir ajuda"
                }
            ],
            autoDownloadFontAwesome: false,
            spellChecker: false
        }
);