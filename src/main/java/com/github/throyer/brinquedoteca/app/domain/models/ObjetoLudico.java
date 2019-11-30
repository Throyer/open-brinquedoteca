package com.github.throyer.brinquedoteca.app.domain.models;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Type;

/**
 * Classe entidade Objeto Lúdico.
 *
 * @author Renato.
 * @version (22/06/2018)
 */
@Entity
public class ObjetoLudico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(min = 3, max = 100, message = "{objeto.nome.tamanho}")
    @Column(nullable = false)
    private String nome;
    
    @Lob
    @Type(type="text")
    @Size(min = 5, message = "{objeto.descricao.tamanho}")
    @Column(nullable = false)
    private String conteudo;
    
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
    
    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ObjetoLudico other = (ObjetoLudico) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return this.getNome();
    }
    
}
