package edu.matheusvanin.gestao_clientes.facade;

import edu.matheusvanin.gestao_clientes.auditoria.Log;
import edu.matheusvanin.gestao_clientes.dto.LogDTO;
import edu.matheusvanin.gestao_clientes.mapper.GestaoClienteMapper;
import edu.matheusvanin.gestao_clientes.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuditoriaFacade {
    private final LogService logService;
    private final GestaoClienteMapper mapper;

    /**
     * Consulta os logs com filtro opcionais
     *
     * @param pageable    Paginação
     * @param metodo      Método HTTP
     * @param endpoint    Endpoint
     * @param status      Status HTTP
     * @param dataInicial Data inicial
     * @param dataFinal   Data final
     * @return Página de logs
     */
    public Page<LogDTO> consultaLogComFiltro(Pageable pageable, String metodo, String endpoint, Integer status,
                                             LocalDateTime dataInicial, LocalDateTime dataFinal) {
        Page<Log> log = logService.getLogsByFilters(pageable, metodo, endpoint, status, dataInicial, dataFinal);
        List<LogDTO> listLogDto = mapper.listLogToListDto(log.toList());
        return new PageImpl<>(listLogDto, pageable, log.getTotalElements());
    }

    /**
     * Consulta os logs de forma paginada
     *
     * @param pageable Paginação
     * @return Página de logs
     */
    public Page<LogDTO> consultaLog(Pageable pageable) {
        Page<Log> log = logService.getLogs(pageable);
        List<LogDTO> listLogDto = mapper.listLogToListDto(log.toList());
        return new PageImpl<>(listLogDto, pageable, log.getTotalElements());
    }
}
