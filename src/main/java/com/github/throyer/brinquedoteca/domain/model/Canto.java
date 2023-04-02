package com.github.throyer.brinquedoteca.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
public class Canto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Size(min = 5, max = 100, message = "{canto.nome.tamanho}")
    @Column(nullable = false, unique = true)
    private String nome;

    @Size(max = 5000, message = "{canto.descricao.tamanho}")
    private String descricao;

    @JoinColumn(name = "created_by")
    @ManyToOne(fetch = EAGER)
    private Usuario createdBy;

    @JoinColumn(name = "updated_by")
    @ManyToOne(fetch = EAGER)
    private Usuario updatedBy;

    @JoinColumn(name = "deleted_by")
    @ManyToOne(fetch = EAGER)
    private Usuario deletedBy;

    public Canto() { }

    public Canto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return getNome();
    }
}
