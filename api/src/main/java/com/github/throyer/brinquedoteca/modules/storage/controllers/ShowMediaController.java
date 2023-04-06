package com.github.throyer.brinquedoteca.modules.storage.controllers;

import com.github.throyer.brinquedoteca.modules.storage.services.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShowMediaController {

    /* Serviço de armazenamento */
    @Autowired
    StorageService storage;

    @GetMapping(value = "/imagem/objeto/capa/{objetoId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImagem(@PathVariable String objetoId) {
        return storage.carregarCapaObjeto(objetoId);
    }
}
