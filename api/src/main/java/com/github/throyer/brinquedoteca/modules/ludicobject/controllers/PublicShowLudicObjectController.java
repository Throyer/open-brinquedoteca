package com.github.throyer.brinquedoteca.modules.ludicobject.controllers;

import com.github.rjeschke.txtmark.Processor;
import com.github.throyer.brinquedoteca.modules.ludicobject.entities.LudicObject;
import com.github.throyer.brinquedoteca.modules.ludicobject.repositories.LudicObjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.github.throyer.brinquedoteca.modules.shared.utils.Templates.PUBLIC.LUDIC_OBJECT;

@Controller
@AllArgsConstructor
@RequestMapping("/objetos")
public class PublicShowLudicObjectController {
    private final LudicObjectRepository ludicObjectRepository;

    @GetMapping("/{id}")
    public String exibirObjeto(@PathVariable Long id, Model model) {
        var found = ludicObjectRepository.findById(id);

        if (found.isEmpty()) {
            // TODO: toast object not found
            return "redirect:/";
        }

        LudicObject ludicObject = found.get();

        ludicObject.setDescription(Processor.process(ludicObject.getDescription()));
        ludicObject.setReferences(Processor.process(ludicObject.getReferences()));

        model.addAttribute("objeto", ludicObject);
        model.addAttribute("canto", ludicObject.getCorner());

        return LUDIC_OBJECT;
    }
}
