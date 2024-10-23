package edu.matheusvanin.gestao_clientes.controller;

import edu.matheusvanin.gestao_clientes.dto.EnderecoDTO;
import edu.matheusvanin.gestao_clientes.facede.GestaoClienteFacede;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/endereco")
@RequiredArgsConstructor
public class EnderecoController {

    private final GestaoClienteFacede gestaoClienteFacede;

    @PostMapping("/{uuid}")
    void insereEndereco(@PathVariable(name = "uuid") UUID uuidCliente, @RequestBody EnderecoDTO enderecoDTO) {
        gestaoClienteFacede.insereEndereco(uuidCliente, enderecoDTO);
    }

    @GetMapping("/{uuid}")
    ResponseEntity<EnderecoDTO> buscaEndereco(@PathVariable(name = "uuid") UUID uuidEndereco) {
        return ResponseEntity.ok(gestaoClienteFacede.buscaEndereco(uuidEndereco));
    }

    @DeleteMapping("/{uuid}")
    void deletaEndereco(@PathVariable(name = "uuid") UUID uuidEndereco) {
        gestaoClienteFacede.deletaEndereco(uuidEndereco);
    }

    @GetMapping("/busca-cep/{cep}")
    ResponseEntity<EnderecoDTO> buscaEnderecoPorCep(@PathVariable String cep) {
        return ResponseEntity.ok(gestaoClienteFacede.buscaEnderecoPorCep(cep));
    }
}
