package edu.matheusvanin.gestao_clientes.service;

import edu.matheusvanin.gestao_clientes.auditoria.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface LogService {
    /**
     * Busca logs com filtros, caso não haja resultados lança exceção
     *
     * @param pageable    Paginação
     * @param metodo      Método HTTP
     * @param endpoint    Endpoint
     * @param status      Status HTTP
     * @param dataInicial Data inicial
     * @param dataFinal   Data final
     * @return Página de logs
     */
    Page<Log> getLogsByFilters(Pageable pageable, String metodo, String endpoint, Integer status,
                               LocalDateTime dataInicial, LocalDateTime dataFinal);

    /**
     * Busca logs de forma paginada
     *
     * @param pageable Paginação
     * @return Página de logs
     */
    Page<Log> getLogs(Pageable pageable);
}
