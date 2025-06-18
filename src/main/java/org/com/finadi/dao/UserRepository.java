package org.com.finadi.dao;

import org.com.finadi.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

  /**
   * Busca um produto pelo seu nome exato.
   * O Spring gera: "SELECT p FROM Produto p WHERE p.nome = ?1"
   */
  Optional<User> findByName(String nome);
  Optional<User> findByEmail(String email);

  /**
   * Busca todos os produtos de uma determinada categoria.
   * O Spring gera: "SELECT p FROM Produto p WHERE p.categoria = ?1"
   */

  /**
   * Busca produtos cujo nome contém o termo pesquisado, ignorando maiúsculas/minúsculas.
   * O Spring gera: "SELECT p FROM Produto p WHERE upper(p.nome) LIKE upper(?1)"
   * (O SQL exato pode variar com o dialeto do banco)
   */
  List<User> findByNameContainingIgnoreCase(String termo);
}
