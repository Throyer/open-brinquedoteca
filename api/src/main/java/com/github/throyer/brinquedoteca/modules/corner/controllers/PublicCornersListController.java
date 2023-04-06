package com.github.throyer.brinquedoteca.modules.corner.controllers;

import com.github.rjeschke.txtmark.Processor;
import com.github.throyer.brinquedoteca.modules.corner.repositories.CornerRepository;
import com.github.throyer.brinquedoteca.modules.ludicobject.repositories.LudicObjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.github.throyer.brinquedoteca.modules.shared.utils.Templates.PUBLIC.CORNER;

@Controller
@AllArgsConstructor
@RequestMapping("/cantos")
public class PublicCornersListController {
    private final CornerRepository conerRepository;
    private final LudicObjectRepository ludicObjectRepository;
    @GetMapping("/{id}")
    public String exibirCanto(
        @PathVariable("id") Long id,
        Pageable pageable,
        Model model
    ) {
        var found = conerRepository.findById(id);

        if (found.isEmpty()) {
            // TODO: toast corner not found
            return "redirect:/";
        }

        var corner = found.get();

        if (corner.getDescription().length() == 0) {
            /* Adiconando informativo de descrição vazia */
            corner.setDescription("Descrição do canto não informada.");
        } else {
            /* Transformando markdown em HTML */
            corner.setDescription(Processor.process(corner.getDescription()));
        }

        var objectPage = ludicObjectRepository.findByCornerOrderByNameAsc(corner, pageable);


        model.addAttribute("canto", corner);
        model.addAttribute("objectPage", objectPage);

        return CORNER;
    }
}
