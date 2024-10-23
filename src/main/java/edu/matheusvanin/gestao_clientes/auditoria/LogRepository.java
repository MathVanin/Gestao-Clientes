package edu.matheusvanin.gestao_clientes.auditoria;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log, String>, LogRepositoryCustom {
}
