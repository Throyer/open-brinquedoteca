package com.github.throyer.brinquedoteca.domain.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
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

    private Integer atividade;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Canto> cantos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<ObjetoLudico> objetos;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "usuario_cargo",
            joinColumns = {
                @JoinColumn(name = "usuario_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "cargo_id")})
    private List<Cargo> cargos;

    @Transient
    private boolean administrador;

    @Transient
    private boolean curador;

    public Usuario() {
        //
    }

    public Usuario(List<Cargo> cargos) {
        this.cargos = cargos;
    }

    public Usuario(String nome, String sobrenome, String email, String senha, Integer atividade) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.atividade = atividade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getAtividade() {
        return atividade;
    }

    public void setAtividade(Integer atividade) {
        this.atividade = atividade;
    }

    public List<Canto> getCantos() {
        return cantos;
    }

    public void setCantos(List<Canto> cantos) {
        this.cantos = cantos;
    }

    public List<ObjetoLudico> getObjetos() {
        return objetos;
    }

    public void setObjetos(List<ObjetoLudico> objetos) {
        this.objetos = objetos;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
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
