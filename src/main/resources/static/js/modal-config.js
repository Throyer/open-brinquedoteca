/**
 * Configuração do modal de exclusão de cantos.
 */
$('#modal-canto').on('show.bs.modal', function (event) {
  
  var button = $(event.relatedTarget);
  var nome = button.data('nome');
  var id = button.data('id');
  
  var modal = $(this);
  modal.find('#nome').text(nome);
  modal.find('#excluir').attr('value', id);
});


/**
 * Configuração do modal de exclusão de objeto.
 */
$('#modal-objeto').on('show.bs.modal', function (event) {
  
  var button = $(event.relatedTarget);
  var nome = button.data('nome');
  var id = button.data('id');
  
  var modal = $(this);
  modal.find('#nome').text(nome);
  modal.find('#excluir').attr('value', id);
});

/**
 * Configuração do modal de exclusão de objeto.
 */
$('#modal-curador').on('show.bs.modal', function (event) {
  
  var button = $(event.relatedTarget);
  var nome = button.data('nome');
  var id = button.data('id');
  
  var modal = $(this);
  modal.find('#nome').text(nome);
  modal.find('#curador').text(nome);
  modal.find('#excluir').attr('value', id);
});
