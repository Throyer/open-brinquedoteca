package com.github.throyer.brinquedoteca.modules.user.dtos;

import com.github.throyer.brinquedoteca.modules.user.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Getter
@Setter
public class CreateOrUpdateCuratorData {
    public CreateOrUpdateCuratorData(
        Model model,
        BindingResult validation,
        RedirectAttributes redirect,
        User curator,
        String confirmarSenha,
        String cargo
    ) {
        this.model = model;
        this.validation = validation;
        this.redirect = redirect;
        this.payload = new Payload(
            curator,
            confirmarSenha,
            cargo
        );
    }

    private Model model;
    private BindingResult validation;
    private RedirectAttributes redirect;
    private Payload payload;
    @Getter
    @Setter
    public static class Payload {
        public Payload(
            User curator,
            String confirmarSenha,
            String cargo
        ) {
            this.curator = curator;
            this.confirmarSenha = confirmarSenha;
            this.cargo = cargo;
        }

        private User curator;
        private String confirmarSenha;
        private String cargo;
    }
}
