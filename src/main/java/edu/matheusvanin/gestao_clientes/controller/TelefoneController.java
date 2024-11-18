package edu.matheusvanin.gestao_clientes.controller;

import edu.matheusvanin.gestao_clientes.dto.GenericResponseDto;
import edu.matheusvanin.gestao_clientes.dto.TelefoneDTO;
import edu.matheusvanin.gestao_clientes.facade.GestaoClienteFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static edu.matheusvanin.gestao_clientes.utils.Constantes.*;

@RestController
@RequestMapping("/telefone")
@RequiredArgsConstructor
public class TelefoneController {
    private final GestaoClienteFacade gestaoClienteFacade;

    @PostMapping("/{uuid}")
    ResponseEntity<GenericResponseDto> insereTelefone(@PathVariable(name = "uuid") UUID uuidCliente,
                                                      @RequestBody TelefoneDTO telefoneDTO) {
        gestaoClienteFacade.insereTelefone(uuidCliente, telefoneDTO);
        return ResponseEntity.ok(GenericResponseDto.builder().message(TELEFONE_CADASTRADO)
                .timestamp(LocalDateTime.now()).build());
    }

    @PutMapping()
    ResponseEntity<GenericResponseDto> atualizaTelefone(@RequestBody TelefoneDTO telefoneDTO) {
        gestaoClienteFacade.atualizaTelefone(telefoneDTO);
        return ResponseEntity.ok(GenericResponseDto.builder().message(TELEFONE_ATUALIZADO)
                .timestamp(LocalDateTime.now()).build());
    }

    @DeleteMapping("/{uuid}")
    ResponseEntity<GenericResponseDto> deletaTelefone(@PathVariable(name = "uuid") UUID uuidTelefone) {
        gestaoClienteFacade.deletaTelefone(uuidTelefone);
        return ResponseEntity.ok(GenericResponseDto.builder().message(TELEFONE_EXCLUIDO)
                .timestamp(LocalDateTime.now()).build());
    }

    @GetMapping("/{uuid}")
    ResponseEntity<TelefoneDTO> buscaTelefone(@PathVariable(name = "uuid") UUID uuidTelefone) {
        return ResponseEntity.ok(gestaoClienteFacade.buscaTelefone(uuidTelefone));
    }
}
