package com.github.throyer.brinquedoteca.services;

import java.util.List;

import com.github.throyer.brinquedoteca.domain.model.Usuario;
import com.github.throyer.brinquedoteca.domain.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    /* Repositorios utilizados */
    @Autowired
    private UsuarioRepository repository;

    /* Classe de criptografia */
    @Autowired
    private BCryptPasswordEncoder encoder;

    public List<Usuario> obterTodos() {
        return repository.findAll();
    }

    public Usuario obterPorId(Long Id) {
        return repository.findById(Id).get();
    }

    public Usuario obterUsuarioPorEmail(String email) {
        return repository.findByEmail(email);
    }

    public void salvarUsuario(Usuario usuario) {

        /* criptografando a senha */
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        usuario.setAtividade(1);

        /* salvando */
        repository.save(usuario);
    }

    public void atualizarUsuario(Usuario usuario) {

        /* salvando */
        repository.save(usuario);
    }

    public void removerUsuario(Usuario usuario) {
        repository.delete(usuario);
    }

    public boolean existeEmail(String email) {
        return repository.findByEmail(email) != null;
    }

    public Usuario getUsuarioLogado() {

        /* Obtendo usuario logado */
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return repository.findByEmail(auth.getName());
    }
}
