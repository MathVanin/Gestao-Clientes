package edu.matheusvanin.gestao_clientes.controller;

import edu.matheusvanin.gestao_clientes.dto.TelefoneDTO;
import edu.matheusvanin.gestao_clientes.facede.GestaoClienteFacede;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/telefone")
@RequiredArgsConstructor
public class TelefoneController {
    private final GestaoClienteFacede gestaoClienteFacede;

    @PostMapping("/{uuid}")
    void insereTelefone(@PathVariable(name = "uuid") UUID uuidCliente, @RequestBody TelefoneDTO telefoneDTO) {
        gestaoClienteFacede.insereTelefone(uuidCliente, telefoneDTO);
    }

    @PutMapping()
    void atualizaTelefone(@RequestBody TelefoneDTO telefoneDTO) {
        gestaoClienteFacede.atualizaTelefone(telefoneDTO);
    }

    @DeleteMapping("/{uuid}")
    void deletaTelefone(@PathVariable(name = "uuid") UUID uuidTelefone) {
        gestaoClienteFacede.deletaTelefone(uuidTelefone);
    }

    @GetMapping("/{uuid}")
    TelefoneDTO buscaTelefone(@PathVariable(name = "uuid") UUID uuidTelefone) {
        return gestaoClienteFacede.buscaTelefone(uuidTelefone);
    }
}
