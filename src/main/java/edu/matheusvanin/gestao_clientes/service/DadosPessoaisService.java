package edu.matheusvanin.gestao_clientes.service;

import edu.matheusvanin.gestao_clientes.domain.Cliente;
import edu.matheusvanin.gestao_clientes.dto.ClienteDTO;
import edu.matheusvanin.gestao_clientes.dto.DadosPessoaisDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface DadosPessoaisService {

    /**
     * Insere os dados pessoais de um cliente no banco de dados
     *
     * @param dadosPessoaisDto Dados pessoais do cliente
     * @return
     */
    Cliente insereDadosPessoais(DadosPessoaisDTO dadosPessoaisDto);

    /**
     * Consulta os dados pessoais dos clientes de forma paginada
     *
     * @param pageable Configuração da paginação
     * @param cpf      Filtro por cpf
     * @param nome     Filtro por nome
     * @return Página de dados pessoais de clientes
     */
    Page<DadosPessoaisDTO> buscaDadosPessoais(Pageable pageable, String cpf, String nome);

    /**
     * Atualiza os dados pessoais de um cliente
     *
     * @param cliente          Cliente a ser editado
     * @param dadosPessoaisDto Dados pessoais do cliente
     */
    void atualizarDadosPessoais(Cliente cliente, DadosPessoaisDTO dadosPessoaisDto);

    /**
     * Deleta um cliente do banco de dados
     *
     * @param cliente Cliente a ser deletado
     */
    void deletaCliente(Cliente cliente);

    /**
     * Busca um cliente pelo UUID, caso nessa busca não seja encontrado, lança uma exceção
     *
     * @param uuid UUID do cliente
     * @return Cliente encontrado
     */
    Cliente buscaClienteOuException(UUID uuid);

    /**
     * Busca uma pagina de clientes com filtros por cpf e/ou nome
     *
     * @param pageable Configuração da paginação
     * @param cpf      CPF do cliente
     * @param nome     Nome do cliente
     * @return Página de clientes
     */
    Page<ClienteDTO> buscaClientes(Pageable pageable, String cpf, String nome);

    /**
     * Valida um cpf, caso seja invalido retorna false
     *
     * @param cpf Cpf a ser validado
     * @return true se o cpf for valido, false caso contrário
     */
    boolean cpfValido(String cpf);

    /**
     * Verifica se um cpf é válido, caso não seja lança uma exceção.
     *
     * @param cpf Cpf a ser validado
     */
    void cpfValidoOuException(String cpf);
}
