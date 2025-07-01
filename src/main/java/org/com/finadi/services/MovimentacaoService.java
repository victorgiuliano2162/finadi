package org.com.finadi.services;

import org.com.finadi.entities.Categoria;
import org.com.finadi.entities.Conta;
import org.com.finadi.entities.Movimentacao;
import org.com.finadi.entities.enums.TipoMovimentacao;
import org.com.finadi.repositories.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentacaoService {

  @Autowired
  MovimentacaoRepository movimentacaoRepository;
  @Autowired
  ContaService contaService;
  /*
  //PERIGOSÍSSIMO deleta TODAS as movimentações marcadas com um tipo específico
  public void deleteByTipoMovimentacao(Movimentacao movimentacao) {
    movimentacaoRepository.deleteByTipoMovimentacao(movimentacao.getTipoMovimentacao());
  }
   */

  public List<Movimentacao> findByTipoMovimentacaoAndConta(Movimentacao movimentacao, Conta conta) {
    Conta contaConsulta = contaService.getContaById(conta.getId());
    return movimentacaoRepository.findByTipoMovimentacaoAndConta(movimentacao.getTipoMovimentacao(), contaConsulta);
  }

  public List<Movimentacao> findByDataAndConta(LocalDateTime data, Conta conta) {
    Conta contaConsulta = contaService.getContaById(conta.getId());
    return movimentacaoRepository.findByDataAndConta(data, contaConsulta);
  }

  public List<Movimentacao> findByCategoriaAndConta(Categoria categoria, Conta conta) {
    Conta contaConsulta = contaService.getContaById(conta.getId());
    return movimentacaoRepository.findByCategoriaAndConta(categoria, contaConsulta);
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
    return movimentacaoRepository.findByDataAndTipoMovimentacaoAndConta(data, tipo, conta);
  }

  public List<Movimentacao> buscarPorTudo(LocalDateTime data, TipoMovimentacao tipo, Categoria categoria, Conta conta) {
    return movimentacaoRepository.findByDataAndTipoMovimentacaoAndCategoriaAndConta(data, tipo, categoria, conta);
  }

  public void deletarPorTipo(TipoMovimentacao tipo) {
    movimentacaoRepository.deleteByTipoMovimentacao(tipo);
  }


  public Movimentacao registrarMovimentacao(Movimentacao movimentacao) throws Exception {
    Conta conta = movimentacao.getConta();
    BigDecimal valor = movimentacao.getValor();

    if (movimentacao.getTipoMovimentacao() == TipoMovimentacao.CREDITO) {
      conta.depositar(valor);
    } else if (movimentacao.getTipoMovimentacao() == TipoMovimentacao.DEBITO) {
      conta.sacar(valor);
    }

    contaService.salvarConta(conta); // Atualiza o saldo
    return movimentacaoRepository.save(movimentacao); // Salva a movimentação
  }


}
