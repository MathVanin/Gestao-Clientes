package edu.matheusvanin.gestao_clientes.controller;

import edu.matheusvanin.gestao_clientes.dto.EnderecoDTO;
import edu.matheusvanin.gestao_clientes.facade.GestaoClienteFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/endereco")
@RequiredArgsConstructor
public class EnderecoController {

    private final GestaoClienteFacade gestaoClienteFacade;

    @PostMapping("/{uuid}")
    void insereEndereco(@PathVariable(name = "uuid") UUID uuidCliente, @RequestBody EnderecoDTO enderecoDTO) {
        gestaoClienteFacade.insereEndereco(uuidCliente, enderecoDTO);
    }

    @GetMapping("/{uuid}")
    ResponseEntity<EnderecoDTO> buscaEndereco(@PathVariable(name = "uuid") UUID uuidEndereco) {
        return ResponseEntity.ok(gestaoClienteFacade.buscaEndereco(uuidEndereco));
    }

    @DeleteMapping("/{uuid}")
    void deletaEndereco(@PathVariable(name = "uuid") UUID uuidEndereco) {
        gestaoClienteFacade.deletaEndereco(uuidEndereco);
    }

    @GetMapping("/busca-cep/{cep}")
    ResponseEntity<EnderecoDTO> buscaEnderecoPorCep(@PathVariable String cep) {
        return ResponseEntity.ok(gestaoClienteFacade.buscaEnderecoPorCep(cep));
    }
}
