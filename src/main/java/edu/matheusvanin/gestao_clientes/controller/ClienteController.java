package edu.matheusvanin.gestao_clientes.controller;

import edu.matheusvanin.gestao_clientes.dto.ClienteDTO;
import edu.matheusvanin.gestao_clientes.dto.DadosPessoaisDTO;
import edu.matheusvanin.gestao_clientes.facede.GestaoClienteFacede;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static edu.matheusvanin.gestao_clientes.utils.Constantes.*;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final GestaoClienteFacede gestaoClienteFacede;

    @PostMapping()
    public ResponseEntity<String> insereClienteCompleto(@RequestBody ClienteDTO clienteDto) {
        gestaoClienteFacede.insereClienteCompleto(clienteDto);
        return ResponseEntity.ok(CLIENTE_CADASTRADO);
    }

    @GetMapping()
    public ResponseEntity<Page<ClienteDTO>> buscaClientes(@PageableDefault Pageable pageable, @RequestParam(required = false) String cpf, @RequestParam(required = false) String nome) {
        return ResponseEntity.ok(gestaoClienteFacede.buscaClientes(pageable, cpf, nome));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<String> atualizaCliente(@PathVariable(name = "uuid") UUID uuid, @RequestBody ClienteDTO clienteDto) {
        gestaoClienteFacede.atualizaCliente(uuid, clienteDto);
        return ResponseEntity.ok(CLIENTE_ATUALIZADO);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> deletaCliente(@PathVariable(name = "uuid") UUID uuid) {
        gestaoClienteFacede.deletaCliente(uuid);
        return ResponseEntity.ok(CLIENTE_DELETADO);
    }

    @PostMapping("/dados-pessoais")
    public ResponseEntity<String> insereDadosPessoais(@RequestBody DadosPessoaisDTO dadosPessoaisDto) {
        gestaoClienteFacede.insereDadosPessoais(dadosPessoaisDto);
        return ResponseEntity.ok(DADOS_PESSOAIS_INSERIDOS);
    }

    @GetMapping("/dados-pessoais")
    public ResponseEntity<Page<DadosPessoaisDTO>> buscaDadosPessoais(@PageableDefault Pageable pageable, @RequestParam(required = false) String cpf, @RequestParam(required = false) String nome) {
        return ResponseEntity.ok(gestaoClienteFacede.buscaDadosPessoais(pageable, cpf, nome));
    }

    @PutMapping("/dados-pessoais/{uuid}")
    public ResponseEntity<String> atualizarDadosPessoais(@PathVariable(name = "uuid") UUID uuid, @RequestBody DadosPessoaisDTO dadosPessoaisDto) {
        gestaoClienteFacede.atualizarDadosPessoais(uuid, dadosPessoaisDto);
        return ResponseEntity.ok(DADOS_PESSOAIS_ATUALIZADO);
    }
}
