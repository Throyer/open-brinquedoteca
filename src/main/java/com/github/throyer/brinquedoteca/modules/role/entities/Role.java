package com.github.throyer.brinquedoteca.modules.role.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Table(name = "cargo")
@Entity(name = "role")
public class Role implements GrantedAuthority {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    public Role() { }

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.getName();
    }

    @Override
    public String toString() {
        return getName();
    }
}
