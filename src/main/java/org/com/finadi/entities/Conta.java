package org.com.finadi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
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

  @ManyToOne(fetch = FetchType.LAZY) // Usar LAZY é uma boa prática para performance
  @JoinColumn(name = "usuario_id", nullable = false)
  @Getter @Setter
  @JsonBackReference
  private Usuario usuario;

  public Conta() {}

  public Conta(double saldo, Usuario usuario) {
    this.saldo = saldo;
    this.dataCriacao = LocalDateTime.now();
    this.usuario = usuario;
    usuario.adicionarConta(this);

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
