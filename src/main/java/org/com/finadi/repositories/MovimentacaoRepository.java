package org.com.finadi.repositories;


import org.com.finadi.entities.Categoria;
import org.com.finadi.entities.Conta;
import org.com.finadi.entities.Movimentacao;
import org.com.finadi.entities.enums.TipoMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, String> {


  List<Movimentacao> findByTipoMovimentacaoAndConta(TipoMovimentacao tipoMovimentacao, Conta conta);

  List<Movimentacao> findByDataAndConta(LocalDateTime data, Conta conta);
  List<Movimentacao> findByCategoriaAndConta(Categoria categoria, Conta conta);

  List<Movimentacao> findByDataAndCategoriaAndConta(LocalDateTime data, Categoria categoria, Conta conta);
  List<Movimentacao> findByDataAndTipoMovimentacaoAndConta(LocalDateTime data, TipoMovimentacao tipoMovimentacao,
                                                           Conta conta);

  List<Movimentacao> findByDataAndTipoMovimentacaoAndCategoriaAndConta(LocalDateTime data, TipoMovimentacao tipoMovimentacao, Categoria categoria, Conta conta);

  void deleteByTipoMovimentacao(TipoMovimentacao tipo);
}
