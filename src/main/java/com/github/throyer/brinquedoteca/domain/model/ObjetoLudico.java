package com.github.throyer.brinquedoteca.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

@Entity
public class ObjetoLudico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(min = 3, max = 100, message = "{objeto.nome.tamanho}")
    @Column(nullable = false)
    private String nome;
    
    private String UrlImagem;
    
    @Lob
    @Type(type="text")
    @Size(min = 5, message = "{objeto.descricao.tamanho}")
    @Column(nullable = false)
    private String descricao;
    
    @Type(type="text")
    @Lob
    @Size(min = 5, message = "{objeto.referencias.tamanho}")
    @Column(nullable = false)
    private String referencias;
    
    @Type(type="text")
    @Size(min = 5, max = 150, message = "Forneça uma breve descrição para o objeto")
    @Column(nullable = false)
    private String preview;
    
    @ManyToOne
    private Canto canto;
    
    @ManyToOne
    private Usuario usuario;

    public ObjetoLudico() {
        //
    }

    public ObjetoLudico(String nome, String descricao, String referencias) {
        this.nome = nome;
        this.descricao = descricao;
        this.referencias = referencias;
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

    public String getUrlImagem() {
        return UrlImagem;
    }

    public void setUrlImagem(String UrlImagem) {
        this.UrlImagem = UrlImagem;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getReferencias() {
        return referencias;
    }

    public void setReferencias(String referencias) {
        this.referencias = referencias;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public Canto getCanto() {
        return canto;
    }

    public void setCanto(Canto canto) {
        this.canto = canto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return getNome();
    }    
}
