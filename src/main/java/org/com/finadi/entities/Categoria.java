package org.com.finadi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@Setter
@NoArgsConstructor
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String nome;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usuario_id", nullable = false)
  private Usuario usuario;

  @OneToMany(mappedBy = "categoria")
  private List<Movimentacao> movimentacoes = new ArrayList<>();

  public Categoria(String nome,  Usuario usuario) {
    this.nome = nome;
    this.usuario = usuario;
  }

  public void adicionarMovimentacao(Movimentacao movimentacao) {
    this.movimentacoes.add(movimentacao);
  }
}
