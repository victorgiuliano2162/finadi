package org.com.finadi.repositories;

import org.com.finadi.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

  /**
   * Busca um produto pelo seu nome exato.
   * O Spring gera: "SELECT p FROM Produto p WHERE p.nome = ?1"
   */
  Optional<Usuario> findByName(String nome);
  Optional<Usuario> findByEmail(String email);
  Optional<Usuario> findByEmailContainingIgnoreCase(String email);
  /**
   * Busca todos os produtos de uma determinada categoria.
   * O Spring gera: "SELECT p FROM Produto p WHERE p.categoria = ?1"
   */
  Usuario findByCpf(String cpf);
  /**
   * Busca produtos cujo nome contém o termo pesquisado, ignorando maiúsculas/minúsculas.
   * O Spring gera: "SELECT p FROM Produto p WHERE upper(p.nome) LIKE upper(?1)"
   * (O SQL exato pode variar com o dialeto do banco)
   */
  List<Usuario> findByNameContainingIgnoreCase(String name);
}
