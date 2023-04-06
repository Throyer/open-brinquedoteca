package com.github.throyer.brinquedoteca.modules.user.entities;

import com.github.throyer.brinquedoteca.modules.role.entities.Role;
import com.github.throyer.brinquedoteca.modules.user.dtos.CreateOrUpdateUser;
import com.github.throyer.brinquedoteca.modules.user.dtos.CreateUserProps;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

import static com.github.throyer.brinquedoteca.modules.infra.constants.SecurityConstants.ENCODER;
import static java.util.Optional.ofNullable;
import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;


@Getter
@Setter
@Table(name = "usuario")
@Entity(name = "user")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Size(min = 5, max = 255, message = "Forneça um nome valido.")
  @Column(name = "nome")
  private String name;

  @Size(max = 255, message = "Forneça um email valido.")
  @Column(name = "email")
  private String email;

  @Size(min = 8, max = 255, message = "{usuario.senha.tamanho}")
  @Column(name = "senha")
  private String password;

  @ManyToMany(cascade = DETACH, fetch = EAGER)
  @JoinTable(name = "usuario_cargo",
    joinColumns = {@JoinColumn(name = "usuario_id")},
    inverseJoinColumns = {@JoinColumn(name = "cargo_id")}
  )
  private List<Role> roles;

  public User() {
  }

  public User(CreateUserProps user, List<Role> roles) {
    this.name = user.getName();
    this.email = user.getEmail();
    this.password = ENCODER.encode("mudar123");
    this.roles = roles;
  }
  
  public User(CreateOrUpdateUser user, List<Role> roles) {
    this.id = user.getId();
    this.name = user.getName();
    this.email = user.getEmail();
    this.password = ENCODER.encode("mudar123");
    this.roles = roles;
  }

  public void update(CreateOrUpdateUser user, List<Role> roles) {
    this.name = user.getName();
    this.email = user.getEmail();
    this.roles = roles;
  }

  @Override
  public String toString() {
    return ofNullable(getName())
      .orElse("");
  }
}
