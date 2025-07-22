package org.com.finadi.services;


import org.com.finadi.entities.Conta;
import org.com.finadi.entities.Usuario;
import org.com.finadi.exception.ResourceNotFoundException;
import org.com.finadi.repositories.ContaRepository;
import org.com.finadi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContaService {

  @Autowired
  private ContaRepository contaRepository;
  @Autowired
  private UsuarioRepository usuarioRepository;
  @Autowired
  private UsuarioService usuarioService;

  public List<Conta> findAll() {
    return contaRepository.findAll();
  }

  public Conta findById(String id) {

    return contaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhuma conta encontrada para o usuário"));
  }

  public List<Conta> getContasByUserId(String id) {
    return contaRepository.findContasByUsuarioId(id)
      .orElseThrow(() -> new ResourceNotFoundException("Nenhuma conta encontrada para o usuário"));
  }

  public Conta salvarConta(Conta conta){
    return contaRepository.save(conta);
  }


  @Transactional
  public Conta criarNovaConta(String userID, Conta conta)  {

    Usuario userConta = usuarioService.findUserById(userID);
    Conta contaCriada = new Conta(conta.getName(), conta.getSaldo(), userConta, conta.getDataCriacao());
    return contaRepository.save(contaCriada);
  }

  public Conta getContaById(String id) {
    return contaRepository.findById(id).orElseThrow();
  }

  public void deleteContaById(String id) {
    contaRepository.deleteById(id);
  }

  public Conta updateConta(Conta conta){
    return contaRepository.save(conta);
  }

}
