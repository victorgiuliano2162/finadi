package org.com.finadi.services;

import org.com.finadi.entities.Usuario;
import org.com.finadi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  public Usuario saveUser(Usuario usuario) {
    return usuarioRepository.save(usuario);
  }

  public Usuario findByCpf(String cpf) {
    return usuarioRepository.findByCpf(cpf);
  }

  public List<Usuario> getUsers() {
    List<Usuario> usuarios = new ArrayList<>();
    usuarioRepository.findAll().forEach(usuarios::add);
    return usuarios;
  }

  public Usuario getUserById(String id) {
    return usuarioRepository.findById(id).orElseThrow();
  }

  public void deleteUserById(String id) {
    usuarioRepository.deleteById(id);
  }

  public Usuario updateUser(Usuario usuario) {
    getUserById(usuario.getId());
    return usuarioRepository.save(usuario);
  }

  public Usuario findByEmail(String email) {
    return usuarioRepository.findByEmail(email).orElseThrow();
  }


}
