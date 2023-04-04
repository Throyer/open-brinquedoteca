package com.github.throyer.brinquedoteca.modules.authentication.services;

import com.github.throyer.brinquedoteca.modules.authentication.models.Authorized;
import com.github.throyer.brinquedoteca.modules.user.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService implements UserDetailsService {
    private final UserRepository repository;
    public AuthenticationService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = repository.findOptionalByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Senha ou usuário invalido."));
        return new Authorized(user);
    }
}