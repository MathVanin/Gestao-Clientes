package edu.matheusvanin.gestao_clientes.auditoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface LogRepositoryCustom {
    Page<Log> findLogsByFilters(Pageable pageable, String method, String endpoint, Integer responseStatus,
                                LocalDateTime from, LocalDateTime to);
}
