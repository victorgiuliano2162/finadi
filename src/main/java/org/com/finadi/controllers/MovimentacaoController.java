package org.com.finadi.controllers;


import org.com.finadi.entities.Categoria;
import org.com.finadi.entities.Conta;
import org.com.finadi.entities.Movimentacao;
import org.com.finadi.exception.AccessDeniedException;
import org.com.finadi.services.CategoriaService;
import org.com.finadi.services.ContaService;
import org.com.finadi.services.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class MovimentacaoController {

  @Autowired
  private MovimentacaoService movimentacaoService;
  @Autowired
  private CategoriaService categoriaService;
  @Autowired
  private ContaService contaService;


  @GetMapping("/{userID}/account/{contaID}/{categoryID}")
  public ResponseEntity<List<Movimentacao>> getCategoryOfAccount(@PathVariable String userID,
                                                                 @PathVariable String contaID,
                                                                @PathVariable String categoryID)  {
    Conta conta = contaService.getContaById(contaID);
    Categoria categoria = categoriaService.findById(categoryID);
    if (!conta.getUsuario().getId().equals(userID)) {
      throw new AccessDeniedException("Conta não pertence ao usuário");
    } else {
      List<Movimentacao> movimentacaos = movimentacaoService.findByCategoriaAndConta(categoria, conta);
      return ResponseEntity.ok(movimentacaos);
    }
  }

  @PostMapping("/{userID}/account/{contaID}/transaction")
  public ResponseEntity<Movimentacao> adicionarMovimentacao(@PathVariable String userID,
                                                            @PathVariable String contaID,
                                                            @RequestParam String category,
                                                            @RequestBody Movimentacao movimentacao) throws Exception {

    //Exemplo de url
    //http://localhost:8080/api/user/def80f8b-f252-4881-9fbf-d252673a3894/account/c33f9828-2d01-4ca9-8b93-a82e69c66e1d/transaction?category=passeio
    Conta contaConsulta = contaService.getContaById(contaID);
    Categoria categoriaConsulta = categoriaService.findOneByNome(category);
    if (!contaConsulta.getUsuario().getId().equals(userID) && !categoriaConsulta.getUsuario().getId().equals(userID)) {
      throw new AccessDeniedException("Conta não pertence ao usuário");
    } else {
      Movimentacao movimentacaoToSave = new Movimentacao(movimentacao.getDescricao(), movimentacao.getValor(),
        movimentacao.getData(), movimentacao.getTipoMovimentacao(), contaConsulta, categoriaConsulta);
      Movimentacao movimentacaoSalva = movimentacaoService.registrarMovimentacao(movimentacaoToSave);
      return ResponseEntity.ok(movimentacaoSalva);
    }
  }

  @GetMapping("/{userID}/account/{contaID}/transaction")
  public ResponseEntity<List<Movimentacao>> getMovimentacaoByCategoria(@PathVariable String userID,
                                                            @PathVariable String contaID,
                                                            @RequestParam String category,
                                                            @RequestBody Movimentacao movimentacao) throws Exception {


    Conta contaConsulta = contaService.getContaById(contaID);
    Categoria categoriaConsulta = categoriaService.findOneByNome(category);
    if (!contaConsulta.getUsuario().getId().equals(userID) && !categoriaConsulta.getUsuario().getId().equals(userID)) {
      throw new AccessDeniedException("Conta não pertence ao usuário");
    } else {

      return ResponseEntity.ok(categoriaConsulta.getMovimentacoes());
    }
  }


}
