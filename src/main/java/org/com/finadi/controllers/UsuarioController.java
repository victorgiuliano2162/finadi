package org.com.finadi.controllers;


import jakarta.validation.Valid;
import org.com.finadi.entities.Usuario;
import org.com.finadi.services.ContaService;
import org.com.finadi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/user")
public class UsuarioController {

  @Autowired
  private UsuarioService usuarioService;
  @Autowired
  private ContaService contaService;



  @PostMapping()
  public ResponseEntity<Usuario> createUser(@RequestBody @Valid Usuario usuario) {
    Usuario savedUser = usuarioService.saveUser(usuario);
    return ResponseEntity.ok(savedUser);
  }


  @GetMapping("/{id}")
  public ResponseEntity<Usuario> getUser(@PathVariable String id) {
    Usuario user = usuarioService.findUserById(id);
    return ResponseEntity.ok(user);
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable String id) {
    usuarioService.deleteUserById(id);
    return ResponseEntity.noContent().build();
  }


}
