package edu.matheusvanin.gestao_clientes.service;

import edu.matheusvanin.gestao_clientes.domain.Endereco;
import edu.matheusvanin.gestao_clientes.dto.EnderecoDTO;
import edu.matheusvanin.gestao_clientes.dto.ViaCepDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface EnderecoService {
    /**
     * Insere um novo endereço.
     *
     * @param enderecoDto Endereço a ser inserido.
     * @param clienteId   Id do cliente ao qual o endereço pertence.
     */
    void insereEndereco(EnderecoDTO enderecoDto, Integer clienteId);

    /**
     * Insere uma lista de endereços.
     *
     * @param enderecoDtos Lista de endereços a serem inseridos.
     * @param clienteId    Id do cliente ao qual os endereços pertencem.
     */
    void insereListaEndereco(List<EnderecoDTO> enderecoDtos, Integer clienteId);

    /**
     * Busca os dados de um cep na api viacep.
     *
     * @param cep Cep a ser consultado
     * @return Dados do cep consultado
     */
    ViaCepDTO buscaDadosCep(String cep);

    /**
     * Cria um EnderecoDTO a partir de um ViaCepDTO
     *
     * @param viaCepDto Dados do cep consultado
     * @return EnderecoDTO criado
     */
    EnderecoDTO criaEnderecoDto(ViaCepDTO viaCepDto);

    /**
     * Valida um cep, caso seja invalido retorna false
     *
     * @param cep Cep a ser validado
     * @return true se o cep for valido, false caso contrário
     */
    boolean cepValido(String cep);

    /**
     * Verifica se um cep é válido, caso não seja lança uma exceção.
     *
     * @param cep Cep a ser validado
     */
    void cepValidoOuException(String cep);

    /**
     * Busca uma lista de enderecos de um cliente
     *
     * @param clienteId Id do cliente
     * @return Lista de enderecos do cliente
     */
    List<Endereco> buscaEnderecosCliente(Integer clienteId);

    /**
     * Valida se um endereço é de um cliente, caso não seja lança uma exceção.
     *
     * @param enderecoDto Endereço a ser validado
     * @param clienteId   Id do cliente
     */
    void validaEnderecoClienteOuException(EnderecoDTO enderecoDto, Integer clienteId);

    /**
     * Valida se uma lista de endereços é de um cliente, caso não seja lança uma exceção.
     *
     * @param enderecoDtolist Lista de endereços a ser validada
     * @param clienteId       Id do cliente
     */
    void validaEnderecoClienteOuException(List<EnderecoDTO> enderecoDtolist, Integer clienteId);

    /**
     * Busca um endereço por uuid, cajo não encontre lança uma exceção.
     *
     * @param uuid UUID do endereço a ser buscado
     * @return Endereço encontrado
     */
    Endereco buscaEnderecoOuException(UUID uuid);

    /**
     * Atualiza um endereço
     *
     * @param enderecoDto Endereço a ser atualizado
     * @return Endereço atualizado
     */
    Endereco atualizaEndereco(EnderecoDTO enderecoDto);

    /**
     * Atualiza uma lista de endereços
     *
     * @param enderecoDto Lista de endereços a serem atualizados
     */
    List<Endereco> atualizaEndereco(List<EnderecoDTO> enderecoDto);

    /**
     * Converte um Endereco em um EnderecoDTO
     *
     * @param endereco Endereco a ser convertido
     * @return EnderecoDTO convertido
     */
    EnderecoDTO converteEnderecoEmDTO(Endereco endereco);

    /**
     * Deleta um endereço
     *
     * @param endereco Endereço a ser deletado
     */
    void deletaEndereco(Endereco endereco);
}
