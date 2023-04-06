package com.github.throyer.brinquedoteca.modules.ludicobject.controllers;

import com.github.throyer.brinquedoteca.modules.ludicobject.entities.LudicObject;
import com.github.throyer.brinquedoteca.modules.ludicobject.repositories.LudicObjectRepository;
import com.github.throyer.brinquedoteca.modules.ludicobject.services.CreateOrUpdateLudicObjectService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static com.github.throyer.brinquedoteca.modules.shared.utils.Templates.MANAGEMENT.LUDIC_OBJECT.FORM;

@Controller
@AllArgsConstructor
@RequestMapping({
  "/objeto",
  "/objeto/gerenciamento"
})
@PreAuthorize("hasAnyAuthority('CURATOR', 'ADMINISTRADOR')")
public class CreateOrEditLudicObjectController {
  private final LudicObjectRepository repository;
  private final CreateOrUpdateLudicObjectService createOrUpdateLudicObjectService;

  @GetMapping("/formulario")
  public String createLudicObjectFrom(Model model) {
    model.addAttribute("objetoLudico", new LudicObject());
    return FORM;
  }

  @GetMapping("/formulario/{id}")
  public String editLudicObjectFrom(
    Model model,
    @PathVariable Long id
  ) {
    var found = repository.findById(id);

    if (found.isEmpty()) {
      // TODO: toast not found message
      return "redirect:/objeto";
    }

    model.addAttribute("objetoLudico", found.get());

    return FORM;
  }

  @PostMapping("/formulario")
  public String salvar(
    @Valid @ModelAttribute("objetoLudico") LudicObject ludicObject,
    BindingResult result,
    Model model,
    @RequestParam("imagem") MultipartFile imagem,
    RedirectAttributes redirect
  ) {
    return createOrUpdateLudicObjectService.createOrUpdate(
      ludicObject,
      result,
      model,
      imagem,
      redirect
    );
  }
}
