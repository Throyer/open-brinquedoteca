package com.github.throyer.brinquedoteca.modules.authentication.models;

import com.github.throyer.brinquedoteca.modules.shared.utils.Roles;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import java.io.Serial;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

public class Authorized extends User {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String name;

    public Authorized(com.github.throyer.brinquedoteca.modules.user.entities.User user) {
        super(
            user.getEmail(),
            user.getPassword(),
            true,
            true,
            true,
            true,
            user.getRoles()
        );
        this.id = user.getId();
        this.name = user.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UsernamePasswordAuthenticationToken getAuthentication() {
        return new UsernamePasswordAuthenticationToken(this, null, getAuthorities());
    }

    public Boolean isAdmin() {
        return getAuthorities()
            .stream()
            .anyMatch((role) -> role.getAuthority().equals(Roles.ADMINISTRATOR));
    }

    public Boolean itsMeOrSessionIsADM(Long id) {
        var admin = isAdmin();
        var equals = getId().equals(id);
        if (admin) {
            return true;
        }
        return equals;
    }

    @Override
    public String toString() {
        return getId().toString();
    }

    public static Optional<Authorized> current() {
        return ofNullable(
            getContext().getAuthentication())
            .map(Authentication::getPrincipal)
            .map((principal) -> {
                if (principal instanceof Authorized authorized) {
                    return authorized;
                }
                return null;
            });
    }
}