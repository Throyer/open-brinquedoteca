package com.github.throyer.brinquedoteca.configurations.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * MaxUploadSizeExceededException.
     * Quando arquivos no upload passam do tamanho maximo permitido.
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String maxUploadError(MaxUploadSizeExceededException exception, RedirectAttributes redirect) {
        redirect.addFlashAttribute("maxUploadSize", true);
        return "redirect:" + "/objeto";
    }
}
