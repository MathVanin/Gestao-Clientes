package edu.matheusvanin.gestao_clientes.controller;

import edu.matheusvanin.gestao_clientes.dto.ClienteDTO;
import edu.matheusvanin.gestao_clientes.dto.DadosPessoaisDTO;
import edu.matheusvanin.gestao_clientes.dto.GenericResponseDto;
import edu.matheusvanin.gestao_clientes.facade.GestaoClienteFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static edu.matheusvanin.gestao_clientes.utils.Constantes.*;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final GestaoClienteFacade gestaoClienteFacade;

    @PostMapping()
    public ResponseEntity<GenericResponseDto> insereClienteCompleto(@RequestBody ClienteDTO clienteDto) {
        gestaoClienteFacade.insereClienteCompleto(clienteDto);
        return ResponseEntity.ok(GenericResponseDto.builder().message(CLIENTE_CADASTRADO)
                .timestamp(LocalDateTime.now()).build());
    }

    @GetMapping()
    public ResponseEntity<Page<ClienteDTO>> buscaClientes(@PageableDefault Pageable pageable,
                                                          @RequestParam(required = false) String cpf,
                                                          @RequestParam(required = false) String nome) {
        return ResponseEntity.ok(gestaoClienteFacade.buscaClientes(pageable, cpf, nome));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<GenericResponseDto> atualizaCliente(@PathVariable(name = "uuid") UUID uuid,
                                                              @RequestBody ClienteDTO clienteDto) {
        gestaoClienteFacade.atualizaCliente(uuid, clienteDto);
        return ResponseEntity.ok(GenericResponseDto.builder().message(CLIENTE_ATUALIZADO)
                .timestamp(LocalDateTime.now()).build());
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<GenericResponseDto> deletaCliente(@PathVariable(name = "uuid") UUID uuid) {
        gestaoClienteFacade.deletaCliente(uuid);
        return ResponseEntity.ok(GenericResponseDto.builder().message(CLIENTE_DELETADO)
                .timestamp(LocalDateTime.now()).build());
    }

    @PostMapping("/dados-pessoais")
    public ResponseEntity<GenericResponseDto> insereDadosPessoais(@RequestBody DadosPessoaisDTO dadosPessoaisDto) {
        gestaoClienteFacade.insereDadosPessoais(dadosPessoaisDto);
        return ResponseEntity.ok(GenericResponseDto.builder().message(DADOS_PESSOAIS_INSERIDOS)
                .timestamp(LocalDateTime.now()).build());
    }

    @GetMapping("/dados-pessoais")
    public ResponseEntity<Page<DadosPessoaisDTO>> buscaDadosPessoais(@PageableDefault Pageable pageable,
                                                                     @RequestParam(required = false) String cpf,
                                                                     @RequestParam(required = false) String nome) {
        return ResponseEntity.ok(gestaoClienteFacade.buscaDadosPessoais(pageable, cpf, nome));
    }

    @PutMapping("/dados-pessoais/{uuid}")
    public ResponseEntity<GenericResponseDto> atualizarDadosPessoais(@PathVariable(name = "uuid") UUID uuid,
                                                                     @RequestBody DadosPessoaisDTO dadosPessoaisDto) {
        gestaoClienteFacade.atualizarDadosPessoais(uuid, dadosPessoaisDto);
        return ResponseEntity.ok(GenericResponseDto.builder().message(DADOS_PESSOAIS_ATUALIZADO)
                .timestamp(LocalDateTime.now()).build());
    }
}
