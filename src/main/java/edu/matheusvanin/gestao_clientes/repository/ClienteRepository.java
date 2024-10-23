package edu.matheusvanin.gestao_clientes.repository;

import edu.matheusvanin.gestao_clientes.domain.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByUuid(UUID uuid);

    @Query("SELECT c FROM Cliente c WHERE (:cpf IS NULL OR c.cpf = :cpf) AND (:nome IS NULL OR c.nome = :nome)")
    Page<Cliente> paginadoComFiltros(Pageable pageable, String cpf, String nome);
}
