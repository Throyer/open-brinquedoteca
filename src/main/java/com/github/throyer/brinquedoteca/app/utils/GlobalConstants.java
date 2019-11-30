package com.github.throyer.brinquedoteca.app.utils;

import org.springframework.validation.ObjectError;

public class GlobalConstants {
    public static final Integer FORCA_DA_CRIPTOGRAFIA_NA_SENHA = 10;
    public static final String SENHA_FORTE = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    public static final String MENSAGEM_SENHA_FORTE = "No mínimo 8 caracteres, com no mínimo um número, um caractere especial, uma letra maiúscula e uma letra minúscula.";
    public static final ObjectError ERRO_CONFIRMAR_SENHA = new ObjectError("Confirmação da senha", "Por favor, confirme a senha.");
    public static final ObjectError ERRO_EMAIL = new ObjectError("Email", "Email já utilizado por outro usuario. Por favor, utilize um e-mail diferente.");
}