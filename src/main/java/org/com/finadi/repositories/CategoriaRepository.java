package org.com.finadi.repositories;


import org.com.finadi.entities.Categoria;
import org.com.finadi.entities.Movimentacao;
import org.com.finadi.entities.enums.TipoMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, String> {

  List<Categoria> findByNome(String nome);

  List<Categoria> findByUsuarioId(String usuarioId);

  void deleteByUsuarioId(String usuarioId);


  }
