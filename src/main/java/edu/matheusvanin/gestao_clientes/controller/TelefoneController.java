package edu.matheusvanin.gestao_clientes.controller;

import edu.matheusvanin.gestao_clientes.dto.TelefoneDTO;
import edu.matheusvanin.gestao_clientes.facade.GestaoClienteFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/telefone")
@RequiredArgsConstructor
public class TelefoneController {
    private final GestaoClienteFacade gestaoClienteFacade;

    @PostMapping("/{uuid}")
    void insereTelefone(@PathVariable(name = "uuid") UUID uuidCliente, @RequestBody TelefoneDTO telefoneDTO) {
        gestaoClienteFacade.insereTelefone(uuidCliente, telefoneDTO);
    }

    @PutMapping()
    void atualizaTelefone(@RequestBody TelefoneDTO telefoneDTO) {
        gestaoClienteFacade.atualizaTelefone(telefoneDTO);
    }

    @DeleteMapping("/{uuid}")
    void deletaTelefone(@PathVariable(name = "uuid") UUID uuidTelefone) {
        gestaoClienteFacade.deletaTelefone(uuidTelefone);
    }

    @GetMapping("/{uuid}")
    TelefoneDTO buscaTelefone(@PathVariable(name = "uuid") UUID uuidTelefone) {
        return gestaoClienteFacade.buscaTelefone(uuidTelefone);
    }
}
