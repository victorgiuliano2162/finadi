package org.com.finadi.services;

import org.com.finadi.entities.Conta;
import org.com.finadi.entities.Usuario;
import org.com.finadi.exception.ResourceNotFoundException;
import org.com.finadi.exception.UserNotFoundException;
import org.com.finadi.repositories.ContaRepository;
import org.com.finadi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;
  @Autowired
  private ContaRepository contaRepository;

  public Usuario saveUser(Usuario usuario) {
    return usuarioRepository.save(usuario);
  }

  public List<Usuario> saveAllUsers(List<Usuario> usuarios) {
    return usuarioRepository.saveAll(usuarios);
  }

  public Usuario findByCpf(String cpf) {
    return usuarioRepository.findByCpf(cpf);
  }

  public List<Usuario> getUsers() {
    List<Usuario> usuarios = new ArrayList<>();
    usuarioRepository.findAll().forEach(usuarios::add);
    return usuarios;
  }

  public Usuario findUserById(String id) {
    return usuarioRepository.findById(id)
      .orElseThrow(() -> new UserNotFoundException(id));
  }

  public void deleteUserById(String id) {
    usuarioRepository.deleteById(id);
  }

  public Usuario updateUser(Usuario usuario) {
    findUserById(usuario.getId());
    return usuarioRepository.save(usuario);
  }

  public Usuario findByEmail(String email) {
    return usuarioRepository.findByEmail(email).orElseThrow();
  }

  public void removerContaDoUsuario(String userId, String contaId) throws AccessDeniedException {
    Usuario usuario = usuarioRepository.findById(userId)
    .orElseThrow(() -> new ResourceNotFoundException("Usuário "+ userId+ " não encontrado"));

    Conta conta = contaRepository.findById(contaId)
      .orElseThrow(() -> new ResourceNotFoundException("Conta " +contaId+ " não encontrada"));

    if (!conta.getUsuario().getId().equals(usuario.getId())) {
      throw new AccessDeniedException("Conta não pertence ao usuário");
    }

    usuario.removerConta(conta);
    usuarioRepository.save(usuario);
  }

}
