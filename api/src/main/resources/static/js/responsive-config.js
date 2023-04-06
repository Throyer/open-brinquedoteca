/**
 * Adicionando classes do bootstrap no corpo dos objetos.
 */
$(document).ready(function () {
    ajustarImagens();
    ajustarVideos();
});

function ajustarImagens() {
    $('#responsive').find('img').each(function (i, element) {
        $(element).addClass('rounded img-fluid mx-auto d-block');
    });
}

function ajustarVideos() {
    $('iframe, video').each(function (i, element) {
        $(element).addClass('embed-responsive-item');
        $(element).after('<div></div>');
        let div = $(element).next().addClass('rounded embed-responsive embed-responsive-16by9');
        $(element).appendTo(div);
    });
}
