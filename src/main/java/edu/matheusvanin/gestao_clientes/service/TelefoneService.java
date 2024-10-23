package edu.matheusvanin.gestao_clientes.service;

import edu.matheusvanin.gestao_clientes.domain.Telefone;
import edu.matheusvanin.gestao_clientes.dto.TelefoneDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface TelefoneService {
    /**
     * Insere um novo telefone.
     *
     * @param telefone  Telefone a ser inserido.
     * @param clienteId Id do cliente ao qual o telefone pertence.
     */
    void insereTelefone(TelefoneDTO telefone, Integer clienteId);

    /**
     * Insere uma lista de telefones.
     *
     * @param telefones Lista de telefones a serem inseridos.
     * @param clienteId Id do cliente ao qual os telefones pertencem.
     */
    void insereListaTelefone(List<TelefoneDTO> telefones, Integer clienteId);

    /**
     * Valida se um telefone é de um cliente, caso não seja lança uma exceção.
     *
     * @param telefoneDto telefone a ser validado
     * @param clienteId   Id do cliente ao qual o telefone pertence.
     */
    void validaTelefoneClienteOuException(TelefoneDTO telefoneDto, Integer clienteId);

    /**
     * Valida se uma lista de telefones é de um cliente, caso não seja lança uma exceção.
     *
     * @param telefoneListDto Lista de telefones a ser validada
     * @param clienteId       Id do cliente ao qual os telefones pertencem.
     */
    void validaTelefoneClienteOuException(List<TelefoneDTO> telefoneListDto, Integer clienteId);

    /**
     * Busca um telefone ou lança uma exceção caso não encontre.
     *
     * @param uuid UUID do telefone a ser buscado.
     * @return Telefone encontrado.
     */
    Telefone buscaTelefoneOuException(UUID uuid);

    /**
     * Atualiza um telefone.
     *
     * @param telefone Telefone a ser atualizado.
     * @return Telefone atualizado.
     */
    Telefone atualizaTelefone(TelefoneDTO telefone);

    /**
     * Atualiza uma lista de telefones.
     *
     * @param telefones Lista de telefones a ser atualizada.
     * @return Lista de telefones atualizada.
     */
    List<Telefone> atualizaTelefone(List<TelefoneDTO> telefones);

    /**
     * Deleta um telefone.
     *
     * @param uuid UUID do telefone a ser deletado.
     */
    void deletaTelefone(UUID uuid);

    /**
     * Converte um telefone em um DTO.
     *
     * @param telefone Telefone a ser convertido.
     */
    TelefoneDTO converteTelefoneEmDTO(Telefone telefone);
}
