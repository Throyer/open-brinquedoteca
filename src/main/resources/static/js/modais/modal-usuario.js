$('#modal-deletar-usuario').on('shown.bs.modal', function (evento) {
    const button = $(evento.relatedTarget);

    let nome = button.data('nome');
    let id = button.data('id');

    let modal = $(this);

    modal.find('#nome-usuario')
        .text(nome);

    modal.find('#form-deletar-usuario')
        .attr('action', `/usuarios/deletar/${id}`);
})
