package org.com.finadi.repositories;


import org.com.finadi.entities.Categoria;
import org.com.finadi.entities.Movimentacao;
import org.com.finadi.entities.enums.TipoMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, String> {

  List<Categoria> findByNomeContainingIgnoreCase(String nome);

  List<Categoria> findByUsuarioId(String usuarioId);

  void deleteByUsuarioId(String usuarioId);


// No seu CategoriaRepository.java

  @Query(
    // Dizemos explicitamente para usar a função unaccent do schema 'public'
    value = "SELECT * FROM categoria " +
      "WHERE public.unaccent(nome::text) ILIKE public.unaccent(concat('%', :nome, '%')) " +
      "LIMIT 1",
    nativeQuery = true
  )
  Optional<Categoria> findByNomeIgnoreCaseAndUnaccent(@Param("nome") String nome);



}
