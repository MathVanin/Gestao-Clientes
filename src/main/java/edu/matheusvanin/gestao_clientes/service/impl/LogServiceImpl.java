package edu.matheusvanin.gestao_clientes.service.impl;

import edu.matheusvanin.gestao_clientes.auditoria.Log;
import edu.matheusvanin.gestao_clientes.auditoria.LogRepository;
import edu.matheusvanin.gestao_clientes.exception.ArgumentoInvalidoException;
import edu.matheusvanin.gestao_clientes.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

import static edu.matheusvanin.gestao_clientes.utils.Constantes.LOG_NAO_ENCONTRADO;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Override
    public Page<Log> getLogsByFilters(Pageable pageable, String metodo, String endpoint, Integer status,
                                      LocalDateTime dataInicial, LocalDateTime dataFinal) {
        Page<Log> logs = logRepository.findLogsByFilters(pageable, metodo, endpoint, status, dataInicial, dataFinal);
        if (Objects.isNull(logs)) {
            throw new ArgumentoInvalidoException(LOG_NAO_ENCONTRADO);
        }
        return logs;
    }

    @Override
    public Page<Log> getLogs(Pageable pageable) {
        return logRepository.findAll(pageable);
    }
}