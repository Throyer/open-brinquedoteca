package com.github.throyer.brinquedoteca.modules.corner.entities;

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
@Entity(name = "corner")
@Table(name = "canto")
public class Corner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Size(min = 5, max = 100, message = "{canto.nome.tamanho}")
    @Column(name = "nome")
    private String name;

    @Size(max = 5000, message = "{canto.descricao.tamanho}")
    @Column(name = "descricao")
    private String description;

    @JoinColumn(name = "created_by")
    @ManyToOne(fetch = EAGER)
    private User createdBy;

    @JoinColumn(name = "updated_by")
    @ManyToOne(fetch = EAGER)
    private User updatedBy;

    @JoinColumn(name = "deleted_by")
    @ManyToOne(fetch = EAGER)
    private User deletedBy;

    public Corner() { }

    public Corner(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return getName();
    }
}
