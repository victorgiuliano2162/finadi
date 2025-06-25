package org.com.finadi.services;


import org.com.finadi.dto.ContaCriacaoDTO;
import org.com.finadi.entities.Conta;
import org.com.finadi.entities.Usuario;
import org.com.finadi.repositories.ContaRepository;
import org.com.finadi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

  @Autowired
  private ContaRepository contaRepository;
  @Autowired
  private UsuarioRepository usuarioRepository;

  public List<Conta> findAll() {
    return contaRepository.findAll();
  }

  public Optional<Conta> findById(String id) {
    return contaRepository.findById(id);
  }

  public Optional<List<Conta>> getContas(String id) {
    return contaRepository.findContasByUsuarioId(id);
  }

  public Conta salvarConta(Conta conta){
    return contaRepository.save(conta);
  }

  @Transactional
  public Conta criarNovaConta(ContaCriacaoDTO contaDTO) {
    String cpfUsuario = contaDTO.getUsuario().getCpf();
    if (cpfUsuario == null || cpfUsuario.isBlank()) {
      throw new IllegalArgumentException("O CPF do usuário é obrigatório.");
    }

    Usuario usuario = usuarioRepository.findByCpf(cpfUsuario);

    Double saldoInicial = contaDTO.getSaldo();

    Conta novaConta = new Conta(saldoInicial, usuario);


    return contaRepository.save(novaConta);
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
