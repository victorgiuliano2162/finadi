package org.com.finadi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.com.finadi.entities.enums.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity @Getter
@Setter
@NoArgsConstructor
public class Movimentacao {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String descricao;
  private BigDecimal valor;
  private LocalDate data;
  @Enumerated(EnumType.STRING)
  private TipoMovimentacao tipo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "conta_id", nullable = false)
  private Conta conta;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "categoria_id", nullable = false)
  private Categoria categoria;
}
