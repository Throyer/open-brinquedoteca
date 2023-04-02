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
public class ObjetoLudico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    @Size(min = 3, max = 100, message = "{objeto.nome.tamanho}")
    @Column(nullable = false)
    private String nome;

    @Column(name = "url_da_imagem")
    private String UrlImagem;

    @Size(min = 5, message = "{objeto.descricao.tamanho}")
    @Column(nullable = false)
    private String descricao;

    @Size(min = 5, message = "{objeto.referencias.tamanho}")
    @Column(nullable = false)
    private String referencias;

    @Size(min = 5, max = 150, message = "Forneça uma breve descrição para o objeto")
    @Column(name = "preview_text", nullable = false)
    private String preview;

    @JoinColumn(name = "canto_id")
    @ManyToOne
    private Canto canto;

    @JoinColumn(name = "created_by")
    @ManyToOne(optional = true, fetch = EAGER)
    private Usuario createdBy;

    @JoinColumn(name = "updated_by")
    @ManyToOne(optional = true, fetch = EAGER)
    private Usuario updatedBy;

    @JoinColumn(name = "deleted_by")
    @ManyToOne(optional = true, fetch = EAGER)
    private Usuario deletedBy;

    public ObjetoLudico() { }

    @Override
    public String toString() {
        return getNome();
    }    
}
