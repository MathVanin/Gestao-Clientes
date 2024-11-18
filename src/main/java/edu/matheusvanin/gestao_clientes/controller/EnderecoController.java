package edu.matheusvanin.gestao_clientes.controller;

import edu.matheusvanin.gestao_clientes.dto.EnderecoDTO;
import edu.matheusvanin.gestao_clientes.dto.GenericResponseDto;
import edu.matheusvanin.gestao_clientes.facade.GestaoClienteFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static edu.matheusvanin.gestao_clientes.utils.Constantes.ENDERECO_CADASTRADO;
import static edu.matheusvanin.gestao_clientes.utils.Constantes.ENDERECO_EXCLUIDO;

@RestController
@RequestMapping("/endereco")
@RequiredArgsConstructor
public class EnderecoController {

    private final GestaoClienteFacade gestaoClienteFacade;

    @PostMapping("/{uuid}")
    ResponseEntity<GenericResponseDto> insereEndereco(@PathVariable(name = "uuid") UUID uuidCliente,
                                                      @RequestBody EnderecoDTO enderecoDTO) {
        gestaoClienteFacade.insereEndereco(uuidCliente, enderecoDTO);
        return ResponseEntity.ok(GenericResponseDto.builder().message(ENDERECO_CADASTRADO)
                .timestamp(LocalDateTime.now()).build());
    }

    @GetMapping("/{uuid}")
    ResponseEntity<EnderecoDTO> buscaEndereco(@PathVariable(name = "uuid") UUID uuidEndereco) {
        return ResponseEntity.ok(gestaoClienteFacade.buscaEndereco(uuidEndereco));
    }

    @DeleteMapping("/{uuid}")
    ResponseEntity<GenericResponseDto> deletaEndereco(@PathVariable(name = "uuid") UUID uuidEndereco) {
        gestaoClienteFacade.deletaEndereco(uuidEndereco);
        return ResponseEntity.ok(GenericResponseDto.builder().message(ENDERECO_EXCLUIDO)
                .timestamp(LocalDateTime.now()).build());
    }

    @GetMapping("/busca-cep/{cep}")
    ResponseEntity<EnderecoDTO> buscaEnderecoPorCep(@PathVariable String cep) {
        return ResponseEntity.ok(gestaoClienteFacade.buscaEnderecoPorCep(cep));
    }
}
