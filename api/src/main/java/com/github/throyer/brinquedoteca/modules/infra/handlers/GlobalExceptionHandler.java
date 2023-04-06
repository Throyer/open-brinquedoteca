package com.github.throyer.brinquedoteca.modules.infra.handlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String maxUploadError(MaxUploadSizeExceededException exception, RedirectAttributes redirect) {
        redirect.addFlashAttribute("maxUploadSize", true);
        return "redirect:/objeto";
    }
}
