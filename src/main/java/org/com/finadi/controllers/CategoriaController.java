package org.com.finadi.controllers;


import org.com.finadi.entities.Categoria;
import org.com.finadi.entities.Usuario;
import org.com.finadi.services.CategoriaService;
import org.com.finadi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class CategoriaController {

  //user/category
  @Autowired
  private CategoriaService categoriaService;
  @Autowired
  private UsuarioService usuarioService;

  @GetMapping("/category")
  public ResponseEntity<List<Categoria>> getCategoriaByName(@RequestBody Categoria categoria) {
    return ResponseEntity.ok(categoriaService.findByNome(categoria.getNome()));
  }

  @GetMapping("/all")
  public ResponseEntity<List<Categoria>> all() {
    return ResponseEntity.ok(categoriaService.getAll());
  }

  @GetMapping("/{userID}/category")
  public ResponseEntity<List<Categoria>> getByUserId(@PathVariable String userID) {
    return ResponseEntity.ok(categoriaService.findByUsuarioId(userID));
  }

  @DeleteMapping("/{userID}/category/{categoryID}")
  public ResponseEntity<Void> deleteByNome(@PathVariable("userID") String userID,
                                           @PathVariable("categoryID") String categoryID) {

    Categoria categoriaToDelete = categoriaService.findById(categoryID);
    if (!categoriaToDelete.getUsuario().getId().equals(userID)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    } else {
      categoriaService.deleteById(categoriaToDelete.getId());
      return ResponseEntity.noContent().build();
    }

  }

  @PostMapping("/{userID}/")
  public ResponseEntity<Categoria> salvarCategoria(@PathVariable String userID, @RequestParam String category) {
    //http://localhost:8080/api/user/def80f8b-f252-4881-9fbf-d252673a3894/?category=pensão
    Usuario usuario = usuarioService.findUserById(userID);
    Categoria categoriaNew = new Categoria(category, usuario);
    categoriaNew = categoriaService.salvarCategoria(categoriaNew);
    return ResponseEntity.ok(categoriaNew);
  }
}

















