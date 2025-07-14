package org.com.finadi.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
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

  public Usuario(String cpf, String name, String email, String password) {
    this.cpf = retornaCPFValido(cpf);
    this.name = name;
    this.email = email;
    this.password = password;
    this.contas = new ArrayList<>();
  }


  public void adicionarConta(Conta conta) {
    contas.add(conta);
  }

  public void removerConta(Conta conta) {
    contas.remove(conta);
    conta.setUsuario(null);
  }

  public String retornaCPFValido(String cpf) {
    boolean valido = validarCPF(cpf);
    //TODO: adicionar lógica de retorno do CPF caso seja inválido
    //talvez mais mais interessante fazê-lo no DTO e não deixar a classe ter informações diretas de solicitações
    // inválidas
    return cpf;
  }

  public static boolean validarCPF(String cpf) {
    cpf = cpf.replaceAll("\\D", "");

    if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;

    int soma = 0;
    for (int i = 0; i < 9; i++) {
      soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
    }
    int primeiroDigito = 11 - (soma % 11);
    if (primeiroDigito >= 10) primeiroDigito = 0;

    soma = 0;
    for (int i = 0; i < 10; i++) {
      soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
    }
    int segundoDigito = 11 - (soma % 11);
    if (segundoDigito >= 10) segundoDigito = 0;

    return primeiroDigito == Character.getNumericValue(cpf.charAt(9)) && segundoDigito == Character.getNumericValue(cpf.charAt(10));
  }
}
