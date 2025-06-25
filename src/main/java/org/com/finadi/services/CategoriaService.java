package org.com.finadi.services;


import org.com.finadi.entities.Categoria;
import org.com.finadi.entities.enums.TipoMovimentacao;
import org.com.finadi.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoriaService {

  @Autowired
  private CategoriaRepository categoriaRepository;

  public List<Categoria> findByNome(String nome) {
    return categoriaRepository.findByNome(nome);
  }

  public List<Categoria> findByUsuarioId(String usuarioId) {
    return categoriaRepository.findByUsuarioId(usuarioId);
  }

  public void deleteByNome(Categoria categoria) {
    categoriaRepository.deleteById(categoria.getId());
  }

  public void deleteByUsuarioId(String usuarioId) {
    categoriaRepository.deleteByUsuarioId(usuarioId);
  }

  public Categoria salvarCategoria(Categoria categoria) {
    return categoriaRepository.save(categoria);
  }

  public Categoria alteraraCategoria(Categoria categoria) {
    return categoriaRepository.save(categoria);
  }


}
