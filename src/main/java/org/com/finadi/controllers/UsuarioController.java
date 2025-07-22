package org.com.finadi.controllers;


import jakarta.validation.Valid;
import org.com.finadi.entities.Usuario;
import org.com.finadi.services.ContaService;
import org.com.finadi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/user")
public class UsuarioController {

  @Autowired
  private UsuarioService usuarioService;
  @Autowired
  private ContaService contaService;

  @GetMapping("/getAll")
  public ResponseEntity<List<Usuario>> getAllUsers() {
    List<Usuario> usuarios = usuarioService.findAll();
    return ResponseEntity.ok(usuarios);
  }

  @PostMapping("/login")
  public ResponseEntity<Usuario> login(@RequestBody Usuario usuario ) {
    Usuario user = usuarioService.findByEmail(usuario.getEmail());
    if (user.getPassword().equals(usuario.getPassword())) {
      return ResponseEntity.ok(user);
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping()
  public ResponseEntity<Usuario> createUser(@RequestBody Usuario usuario) {
    Usuario userToSave = usuarioService.saveUser(usuario);
    return ResponseEntity.status(HttpStatus.CREATED).body(userToSave);

  }


  @GetMapping("/{id}")
  public ResponseEntity<Usuario> getUser(@PathVariable String id) {
    Usuario user = usuarioService.findUserById(id);
    return ResponseEntity.ok(user);
  }

  @GetMapping
  public ResponseEntity<Usuario> getUserById(@RequestParam(name = "cpf") String cpf) {
    Usuario user = usuarioService.findByCpf(cpf);
    return ResponseEntity.ok(user);
  }



  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable String id) {
    usuarioService.deleteUserById(id);
    return ResponseEntity.noContent().build();
  }


}
