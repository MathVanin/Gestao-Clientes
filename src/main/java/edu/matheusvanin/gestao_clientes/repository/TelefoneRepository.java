package edu.matheusvanin.gestao_clientes.repository;

import edu.matheusvanin.gestao_clientes.domain.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
    List<Telefone> findByClienteId(Integer clienteId);

    Optional<Telefone> findByUuid(UUID uuid);
}
