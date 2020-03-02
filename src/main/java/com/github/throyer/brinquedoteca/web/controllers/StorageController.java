package com.github.throyer.brinquedoteca.web.controllers;

import com.github.throyer.brinquedoteca.services.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StorageController {

    /* Serviço de armazenamento */
    @Autowired
    StorageService storage;

    /**
     * Capa de objeto lúdico.
     *
     * @param objetoId
     * @return
     */
    @GetMapping(value = "/imagem/objeto/capa/{objetoId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImagem(@PathVariable String objetoId) {
        return storage.carregarCapaObjeto(objetoId);
    }
}
