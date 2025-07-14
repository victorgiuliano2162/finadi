package org.com.finadi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
  @JsonBackReference
  @JoinColumn(name = "usuario_id", nullable = false)
  private Usuario usuario;

  @OneToMany(mappedBy = "categoria")

  @JsonManagedReference
  private List<Movimentacao> movimentacoes = new ArrayList<>();

  public Categoria(String nome,  Usuario usuario) {
    this.nome = capitalizarPrimeiraLetra(nome);
    this.usuario = usuario;
  }

  public void adicionarMovimentacao(Movimentacao movimentacao) {
    this.movimentacoes.add(movimentacao);
  }

  public static String capitalizarPrimeiraLetra(String texto) {
    return texto.substring(0, 1).toUpperCase() + texto.substring(1);
  }
}
