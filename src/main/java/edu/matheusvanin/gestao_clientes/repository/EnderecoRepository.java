package edu.matheusvanin.gestao_clientes.repository;

import edu.matheusvanin.gestao_clientes.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findByClienteId(Integer clienteId);

    Optional<Endereco> findByUuid(UUID uuid);
}
