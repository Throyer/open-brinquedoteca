package com.github.throyer.brinquedoteca.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.FetchType.EAGER;
import static lombok.AccessLevel.NONE;

@Entity
@Getter
@Setter
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 255, message = "Forneça um nome valido.")
    @Column(nullable = false)
    private String nome;

    @Size(min = 5, max = 255, message = "Forneça um sobrenome valido.")
    @Column(nullable = false)
    private String sobrenome;

    @Size(max = 255, message = "Forneça um email valido.")
    @Column(nullable = false, unique = true)
    private String email;

    @Size(min = 8, max = 255, message = "{usuario.senha.tamanho}")
    @Column(nullable = false)
    private String senha;

    @Transient
    private Integer atividade = 0;

    @ManyToMany(cascade = DETACH, fetch = EAGER)
    @JoinTable(name = "usuario_cargo",
        joinColumns = {@JoinColumn(name = "usuario_id")},
        inverseJoinColumns = {@JoinColumn(name = "cargo_id")}
    )
    private List<Cargo> cargos;

    @Getter(NONE)
    @Transient
    private boolean administrador;

    @Getter(NONE)
    @Transient
    private boolean curador;

    public Usuario() { }

    public Usuario(List<Cargo> cargos) {
        this.cargos = cargos;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public boolean isCurador() {
        return curador;
    }

    public void setCurador(boolean curador) {
        this.curador = curador;
    }

    @Override
    public String toString() {
        return getNome();
    }
}
