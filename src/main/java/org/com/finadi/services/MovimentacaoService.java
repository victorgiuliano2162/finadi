package org.com.finadi.services;

import org.com.finadi.entities.Categoria;
import org.com.finadi.entities.Conta;
import org.com.finadi.entities.Movimentacao;
import org.com.finadi.entities.enums.TipoMovimentacao;
import org.com.finadi.repositories.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentacaoService {

  @Autowired
  MovimentacaoRepository movimentacaoRepository;

  public void deleteByTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
    movimentacaoRepository.deleteByTipoMovimentacao(tipoMovimentacao);
  }

  public List<Movimentacao> findByTipoMovimentacaoAndConta(TipoMovimentacao tipoMovimentacao, Conta conta) {
    return movimentacaoRepository.findByTipoMovimentacaoAndConta(tipoMovimentacao, conta);
  }

  public List<Movimentacao> findByDataAndConta(LocalDateTime data, Conta conta) {
    return movimentacaoRepository.findByDataAndConta(data, conta);
  }

  public List<Movimentacao> findByCategoriaAndConta(Categoria categoria, Conta conta) {
    return movimentacaoRepository.findByCategoriaAndConta(categoria, conta);
  }


  public List<Movimentacao> buscarPorTipoEMovimentacao(TipoMovimentacao tipo, Conta conta) {
    return movimentacaoRepository.findByTipoMovimentacaoAndConta(tipo, conta);
  }

  public List<Movimentacao> buscarPorDataEConta(LocalDateTime data, Conta conta) {
    return movimentacaoRepository.findByDataAndConta(data, conta);
  }

  public List<Movimentacao> buscarPorCategoriaEConta(Categoria categoria, Conta conta) {
    return movimentacaoRepository.findByCategoriaAndConta(categoria, conta);
  }

  public List<Movimentacao> buscarPorDataCategoriaEConta(LocalDateTime data, Categoria categoria, Conta conta) {
    return movimentacaoRepository.findByDataAndCategoriaAndConta(data, categoria, conta);
  }

  public List<Movimentacao> buscarPorDataTipoEConta(LocalDateTime data, TipoMovimentacao tipo, Conta conta) {
    return movimentacaoRepository.findbyDataAndTipoMovimentacaoAndConta(data, tipo, conta);
  }

  public List<Movimentacao> buscarPorTudo(LocalDateTime data, TipoMovimentacao tipo, Categoria categoria, Conta conta) {
    return movimentacaoRepository.findByDataAndTipoMovimentacaoAndCategoriaAndConta(data, tipo, categoria, conta);
  }

  public void deletarPorTipo(TipoMovimentacao tipo) {
    movimentacaoRepository.deleteByTipoMovimentacao(tipo);
  }
}
