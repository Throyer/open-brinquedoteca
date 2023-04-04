package com.github.throyer.brinquedoteca.modules.ludicobject.services;

import com.github.throyer.brinquedoteca.modules.corner.repositories.CornerRepository;
import com.github.throyer.brinquedoteca.modules.ludicobject.entities.LudicObject;
import com.github.throyer.brinquedoteca.modules.ludicobject.repositories.LudicObjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.github.throyer.brinquedoteca.modules.shared.utils.Templates.MANAGEMENT.LUDIC_OBJECT.FORM;

@Service
@AllArgsConstructor
public class CreateOrUpdateLudicObjectService {
    private final CornerRepository cornerRepository;
    private final LudicObjectRepository ludicObjectRepository;

    public String createOrUpdate(
        LudicObject ludicObject,
        BindingResult bindingResult,
        Model model,
        MultipartFile multipartFile,
        RedirectAttributes redirectAttributes
    ) {
        /* verificando se é uma edição ou um novo registro */
        boolean novo = ludicObject.getId() == null;

        /* Caso existam erros no formulario */
        if (bindingResult.hasErrors()) {

            model.addAttribute("ObjetoLudico", ludicObject);

          return FORM;

        } else {

            if (novo) {
                ludicObject.setCreatedBy(null);
                ludicObjectRepository.save(ludicObject);

                redirectAttributes.addFlashAttribute("cadastro", ludicObject);

                return "redirect:/objeto/formulario";

            } else {

//                if (multipartFile.getContentType().contains("image") && !multipartFile.getOriginalFilename().isEmpty()) {
//                    storage.armazenarCapaObjeto(multipartFile, ludicObject.getId());
//                    ludicObject.setImageUrl("/imagem/objeto/capa/" + ludicObject.getId());
//                } else {
//                    ludicObject.setImageUrl(objetoService.obterImagemPeloId(ludicObject.getId()));
//                }

                ludicObject.setCreatedBy(null);
                ludicObjectRepository.save(ludicObject);

                redirectAttributes.addFlashAttribute("atualizado", ludicObject);

                return "redirect:/objeto";
            }
        }
    }
}
