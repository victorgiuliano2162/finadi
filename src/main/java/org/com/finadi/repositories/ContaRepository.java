package org.com.finadi.repositories;

import org.com.finadi.entities.Conta;
import org.com.finadi.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, String> {

  Optional<Conta> findByUsuarioId(String usuarioId);
  Optional<List<Conta>> findContasByUsuarioId(String usuarioId);

  void deleteContaByUsuarioId(String usuarioId);
  void deleteContaById(String id);


}
