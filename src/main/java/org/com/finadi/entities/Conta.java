package org.com.finadi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.com.finadi.services.UsuarioService;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
public class Conta {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Getter
  private String id;

  @Getter
  private BigDecimal saldo =  BigDecimal.ZERO;

  @Getter
  private LocalDateTime dataCriacao;

  @ManyToOne(fetch = FetchType.LAZY) // Usar LAZY é uma boa prática para performance
  @JoinColumn(name = "usuario_id", nullable = false)
  @Getter @Setter
  @JsonBackReference
  private Usuario usuario;

  public Conta() {}

  public Conta(BigDecimal saldo, Usuario usuario, LocalDateTime dataCriacao) {
    this.saldo = saldo;
    this.dataCriacao = dataCriacao;
    this.usuario = usuario;
    usuario.adicionarConta(this);
  }

  public void setSaldo(BigDecimal saldo) {
    this.saldo = this.saldo.add(saldo);
  }

  public void depositar(BigDecimal valor) {
    saldo.add(valor);
  }

  public void sacar(BigDecimal valor) throws Exception {
    if (saldo.compareTo(valor) < 0) {
      throw new Exception("O saldo não pode ser maior que o valor");
    } else {
    this.saldo = this.saldo.subtract(valor);
    }
  }

}
