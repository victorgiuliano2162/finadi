package org.com.finadi.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String cpf;

  private String name;

  private String email;

  private String password;

  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<Conta> contas;

  public Usuario(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public Usuario() {}

  public void adicionarConta(Conta conta) {
    contas.add(conta);
  }
}
