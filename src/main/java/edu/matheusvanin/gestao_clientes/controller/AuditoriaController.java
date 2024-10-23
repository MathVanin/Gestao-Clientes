package edu.matheusvanin.gestao_clientes.controller;

import edu.matheusvanin.gestao_clientes.dto.LogDTO;
import edu.matheusvanin.gestao_clientes.facade.AuditoriaFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auditoria")
@RequiredArgsConstructor
public class AuditoriaController {

    private final AuditoriaFacade auditoriaFacade;

    @GetMapping()
    public ResponseEntity<Page<LogDTO>> getLogs(Pageable pageable) {
        return ResponseEntity.ok(auditoriaFacade.consultaLog(pageable));
    }

    @GetMapping("/filtros")
    public ResponseEntity<Page<LogDTO>> getLogs(@PageableDefault Pageable pageable,
                                                @RequestParam(name = "metodo", required = false) String metodo,
                                                @RequestParam(name = "endpoint", required = false) String endpoint,
                                                @RequestParam(name = "status", required = false) Integer status,
                                                @RequestParam(name = "dataInicial", required = false) LocalDateTime dataInicial,
                                                @RequestParam(name = "dataFinal", required = false) LocalDateTime dataFinal) {
        return ResponseEntity.ok(auditoriaFacade.consultaLogComFiltro(pageable, metodo, endpoint, status, dataInicial, dataFinal));
    }
}