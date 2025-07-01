package org.com.finadi.services;


import org.com.finadi.entities.Categoria;
import org.com.finadi.exception.ResourceNotFoundException;
import org.com.finadi.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

  @Autowired
  private CategoriaRepository categoriaRepository;

  public List<Categoria> findByNome(String nome) {
    return categoriaRepository.findByNomeContainingIgnoreCase(nome);
  }

  public Categoria findOneByNome(String nome) {
    return categoriaRepository.findByNomeIgnoreCaseAndUnaccent(nome)
      .orElseThrow(() -> new ResourceNotFoundException("Categoria " + nome+ " não encontrada"));
  }

  public List<Categoria> getAll() {
    return categoriaRepository.findAll();
  }

  public List<Categoria> findByUsuarioId(String usuarioId) {
    return categoriaRepository.findByUsuarioId(usuarioId);
  }

  public Categoria findById(String id) {
    return categoriaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoria com o " + id + " não encontrada"));
  }

  public void deleteById(String id) {
    Categoria categoria = categoriaRepository
      .findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Categoria com o " + id + " não encontrada"));
    categoriaRepository.deleteById(id);
  }

  //TODO: revisar método, como está apagaria todas as categorias de um user
//  public void deleteByUsuarioId(String usuarioId) {
//    categoriaRepository.deleteByUsuarioId(usuarioId);
//  }

  public Categoria salvarCategoria(Categoria categoria) {
    return categoriaRepository.save(categoria);
  }

  public Categoria alteraraCategoria(Categoria categoria) {
    return categoriaRepository.save(categoria);
  }


}
