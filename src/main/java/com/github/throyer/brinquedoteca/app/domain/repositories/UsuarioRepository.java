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
package com.github.throyer.brinquedoteca.app.domain.repositories;

import java.util.Optional;

import com.github.throyer.brinquedoteca.app.domain.models.Usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Renato Henrique
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Informa se existe já existe um Usuario com o email recebido.
     * @param email {String email do usuario}
     * @return retorna true se existe.
     */
    public Boolean existsByEmail(String email);
    
    /**
     * Procurar Usuario a partir do email.
     * @param email {String email do usuario}
     * @return retorna um <code>Optional</code> de <code>Usuario</code>.
     */
    public Optional<Usuario> findOptionalByEmail(String email);

    /**
     * Procurar Usuarios a partir do <code>Cargo</code>.
     * @param pageable {Pageable objeto de paginação do Spring}
     * @param nome {String nome do Cargo no usuario}
     * @return retorna um <code>Optional</code> de <code>Usuario</code>.
     */
    public Page<Usuario> findByCargo_Nome(Pageable pageable, String nome);
}
