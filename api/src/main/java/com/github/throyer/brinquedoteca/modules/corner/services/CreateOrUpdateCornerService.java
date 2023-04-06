package com.github.throyer.brinquedoteca.modules.corner.services;

import com.github.throyer.brinquedoteca.modules.corner.entities.Corner;
import com.github.throyer.brinquedoteca.modules.corner.repositories.CornerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.github.throyer.brinquedoteca.modules.shared.utils.Templates.MANAGEMENT.CORNER.FORM;

@Service
@AllArgsConstructor
public class CreateOrUpdateCornerService {
    private final CornerRepository cornerRepository;

    public String createOrUpdate(
        Corner corner,
        BindingResult result,
        Model formulario,
        RedirectAttributes redirect
    ) {
        /* verificando se é uma edição ou um novo registro */
        boolean novo = corner.getId() == null;

        /* Caso exista um canto com o mesmo nome */
        if (cornerRepository.existsByName(corner.getName())) {

            result.rejectValue("nome", "error.nome", "Já existe um canto com o nome <strong>"
                + corner.getName() + "</strong> .<br>Por favor digite um nome diferente.");
        }

        /* Caso existam erros no formulario */
        if (result.hasErrors()) {

            /* Devolvendo os dados para o formulario */
            formulario.addAttribute("canto_", corner);

            return FORM;

        } else {
            corner.setCreatedBy(null);
            cornerRepository.save(corner);

            if (novo) {
                /* Passando dados desse novo edificio para o 'redirect' */
                redirect.addFlashAttribute("cadastro", corner);

                /* Redireciona para formulario novamente, para um novo cadastro */
                return "redirect:/canto/formulario";
            } else {
                /* Passando dados desse edificio para o 'redirect' */
                redirect.addFlashAttribute("atualizado", corner);

                /* Redireciona para a lista de edificios depois da edição */
                return "redirect:/canto";
            }
        }
    }
}
