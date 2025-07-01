package org.com.finadi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.com.finadi.entities.enums.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity @Getter
@Setter
@NoArgsConstructor
public class Movimentacao {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String descricao;
  private BigDecimal valor;
  private LocalDateTime data;

  @Enumerated(EnumType.STRING)
  private TipoMovimentacao tipoMovimentacao;

  @ManyToOne
  @JoinColumn(name = "conta_id", nullable = false)
  private Conta conta;

  @ManyToOne
  @JoinColumn(name = "categoria_id", nullable = false)
  @JsonBackReference
  private Categoria categoria;

  public Movimentacao(String descricao, BigDecimal valor, LocalDateTime data, TipoMovimentacao tipoMovimentacao,
                      Conta conta,
                      Categoria categoria){
    this.descricao = descricao;
    this.valor = valor;
    this.data = data;
    this.tipoMovimentacao = tipoMovimentacao;
    this.conta = conta;
    this.categoria = categoria;
    categoria.adicionarMovimentacao(this);
  }

}
