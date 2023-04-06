/**
 * Faz umas gambiarra quando o documento carrega.
 */
$(document).ready(function () {
    corrigirNavbar();
});

/**
 * Remove as propriedades que deixam o navbar fixado no topo da pagina.
 */
function corrigirNavbar() {

    //Corrigindo navbar
    var navbar = document.querySelector("#navbar");
    navbar.classList.remove('fixed-top');
    navbar.style.marginBottom = 0;

    //Corrigindo body
    var body = document.querySelector("body");
    body.style.paddingTop = 0;

}

