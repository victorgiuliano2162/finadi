package org.com.finadi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import org.com.finadi.services.UsuarioService;

import java.time.LocalDateTime;


@Entity
public class Conta {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Getter
  private String id;

  @Getter
  private Double saldo;

  @Getter
  private LocalDateTime dataCriacao;

  @ManyToOne @JoinColumn(nullable = false) @Getter
  private Usuario usuario;

  public Conta() {}

  public Conta(double saldo, LocalDateTime dataCriacao, Usuario usuario) {
    this.saldo = saldo;
    this.dataCriacao = dataCriacao;
    this.usuario = usuario;
    UsuarioService usuarioService = new UsuarioService();
    usuario.adicionarConta(this);
    usuarioService.updateUser(usuario);

  }

  private void setSaldo(Double saldo) {
    this.saldo = saldo;
  }

  public void depositar(double valor) {
    this.saldo += valor;
  }

  public void sacar(double valor) throws Exception {
    if (valor > this.saldo) {
      throw new Exception("O saldo não pode ser maior que o valor");
    } else {
    this.saldo -= valor;
    }
  }

}
