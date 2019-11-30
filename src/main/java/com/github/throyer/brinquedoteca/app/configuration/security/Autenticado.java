/*
 * Copyright (C) 2019 Renato Henrique
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.throyer.brinquedoteca.app.configuration.security;

import java.util.Collection;

import com.github.throyer.brinquedoteca.app.domain.models.Usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Classe de usuario Autenticado.
 * 
 * Usuario atenticado na sessão do spring boot.
 * @author Renato Henrique.
 * @since 3.0.0.
 */
public class Autenticado extends User {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String apelido;
    
    public Autenticado(
        Long id,
        String apelido,
        String username,
        String password,
        boolean enabled,
        boolean accountNonExpired,
        boolean credentialsNonExpired,
        boolean accountNonLocked,
        Collection<? extends GrantedAuthority> authorities
    ) {
        super(
            username,
            password,
            enabled,
            accountNonExpired,
            credentialsNonExpired,
            accountNonLocked,
            authorities
        );
        this.id = id;
        this.apelido = apelido;
    }

    public Autenticado(Usuario usuario) {
        super(
            usuario.getEmail(),
            usuario.getSenha(),
            usuario.isAtivo(),
            true,
            true,
            true,
            usuario.getAuthorities()
        );
        this.id = usuario.getId();
        this.apelido = usuario.getApelido();
    }    

    public String getApelido() {
        return apelido;
    }    

    public Long getId() {
        return id;
    }    
}
