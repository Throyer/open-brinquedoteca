package com.github.throyer.brinquedoteca.modules.ludicobject.entities;

import com.github.throyer.brinquedoteca.modules.corner.entities.Corner;
import com.github.throyer.brinquedoteca.modules.user.entities.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity(name = "ludic_object")
@Table(name = "objeto_ludico")
public class LudicObject implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    @Size(min = 3, max = 100, message = "{objeto.nome.tamanho}")
    @Column(name = "nome")
    private String name;

    @Column(name = "url_da_imagem")
    private String imageUrl;

    @Size(min = 5, message = "{objeto.descricao.tamanho}")
    @Column(name = "descricao")
    private String description;

    @Size(min = 5, message = "{objeto.referencias.tamanho}")
    @Column(name = "referencias")
    private String references;

    @Size(min = 5, max = 150, message = "Forneça uma breve descrição para o objeto")
    @Column(name = "preview_text", nullable = false)
    private String previewText;

    @JoinColumn(name = "canto_id")
    @ManyToOne(fetch = EAGER)
    private Corner corner;

    @JoinColumn(name = "created_by")
    @ManyToOne(fetch = EAGER)
    private User createdBy;

    @JoinColumn(name = "updated_by")
    @ManyToOne(fetch = EAGER)
    private User updatedBy;

    @JoinColumn(name = "deleted_by")
    @ManyToOne(fetch = EAGER)
    private User deletedBy;

    public LudicObject() { }

    @Override
    public String toString() {
        return getName();
    }    
}
