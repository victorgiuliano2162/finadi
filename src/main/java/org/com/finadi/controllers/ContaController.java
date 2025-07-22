package org.com.finadi.controllers;


import org.com.finadi.entities.Conta;
import org.com.finadi.services.ContaService;
import org.com.finadi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class ContaController {

  @Autowired
  private ContaService contaService;
  @Autowired
  private UsuarioService usuarioService;


  @PostMapping("/{userID}/account")
  public ResponseEntity<Conta> salvarConta(@PathVariable String userID,
                                           @RequestBody Conta conta) {

    Conta contaCriada = contaService.criarNovaConta(userID, conta);
    return ResponseEntity.ok(contaCriada);
  }

  @PostMapping("account")
  public ResponseEntity<Conta> salvarConta(@RequestBody Conta conta) {
    Conta criada = contaService.criarNovaConta(conta.getUsuario().getId(), conta);
    return ResponseEntity.ok(criada);
  }

  @DeleteMapping("/{userID}/account/{contaID}")
  public ResponseEntity<Void> deletarConta(@PathVariable("userID") String userID,
                                           @PathVariable("contaID") String contaID) throws AccessDeniedException {

    usuarioService.removerContaDoUsuario(userID, contaID);
    return ResponseEntity.noContent().build();
  }


  @GetMapping("/account/{contaID}")
  public ResponseEntity<Conta> getContaUsuario(@PathVariable("contaID") String contaID){
    Conta c = contaService.findById(contaID);
    return ResponseEntity.ok(c);
  }

  @GetMapping("/{userID}/accounts")
  public ResponseEntity<List<Conta>> getContasUsuario(@PathVariable("userID") String userID){
    List<Conta> contas = contaService.getContasByUserId(userID);
    return ResponseEntity.ok(contas);

  }

}
