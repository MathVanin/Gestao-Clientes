package edu.matheusvanin.gestao_clientes.facade;

import edu.matheusvanin.gestao_clientes.domain.Cliente;
import edu.matheusvanin.gestao_clientes.domain.Endereco;
import edu.matheusvanin.gestao_clientes.domain.Telefone;
import edu.matheusvanin.gestao_clientes.dto.*;
import edu.matheusvanin.gestao_clientes.service.DadosPessoaisService;
import edu.matheusvanin.gestao_clientes.service.EnderecoService;
import edu.matheusvanin.gestao_clientes.service.TelefoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GestaoClienteFacade {

    private final DadosPessoaisService dadosPessoaisService;
    private final EnderecoService enderecoService;
    private final TelefoneService telefoneService;

    /**
     * Insere um cliente completo no banco de dados
     *
     * @param clienteDto - Dados do cliente a ser inserido
     */
    public void insereClienteCompleto(ClienteDTO clienteDto) {
        dadosPessoaisService.cpfValidoOuException(clienteDto.getDadosPessoais().getCpf());
        Cliente cliente = dadosPessoaisService.insereDadosPessoais(clienteDto.getDadosPessoais());
        enderecoService.insereListaEndereco(clienteDto.getEndereco(), cliente.getId());
        telefoneService.insereListaTelefone(clienteDto.getTelefone(), cliente.getId());
    }

    /**
     * Insere os dados pessoais de um cliente no banco de dados
     *
     * @param dadosPessoaisDto - Dados pessoais do cliente
     */
    public void insereDadosPessoais(DadosPessoaisDTO dadosPessoaisDto) {
        dadosPessoaisService.cpfValidoOuException(dadosPessoaisDto.getCpf());
        dadosPessoaisService.insereDadosPessoais(dadosPessoaisDto);
    }

    /**
     * Consulta os dados pessoais dos clientes de forma paginada
     *
     * @param pageable - Configuração da paginação
     * @param cpf      - Filtro por cpf
     * @param nome     - Filtro por nome
     * @return Página de dados pessoais de clientes
     */
    public Page<DadosPessoaisDTO> buscaDadosPessoais(Pageable pageable, String cpf, String nome) {
        return dadosPessoaisService.buscaDadosPessoais(pageable, cpf, nome);
    }

    /**
     * Atualiza os dados pessoais de um cliente
     *
     * @param uuid             - UUID do cliente a ser atualizado
     * @param dadosPessoaisDto - Novos dados pessoais do cliente
     */
    public void atualizarDadosPessoais(UUID uuid, DadosPessoaisDTO dadosPessoaisDto) {
        Cliente cliente = dadosPessoaisService.buscaClienteOuException(uuid);
        dadosPessoaisService.cpfValidoOuException(dadosPessoaisDto.getCpf());
        dadosPessoaisService.atualizarDadosPessoais(cliente, dadosPessoaisDto);
    }

    /**
     * Deleta um cliente do banco de dados
     *
     * @param uuid - UUID do cliente a ser deletado
     */
    public void deletaCliente(UUID uuid) {
        Cliente cliente = dadosPessoaisService.buscaClienteOuException(uuid);
        dadosPessoaisService.deletaCliente(cliente);
    }

    /**
     * Insere um endereco no banco de dados
     *
     * @param uuidCliente - UUID do cliente a ser inserido
     * @param enderecoDTO - Endereco a ser inserido
     */
    public void insereEndereco(UUID uuidCliente, EnderecoDTO enderecoDTO) {
        Cliente cliente = dadosPessoaisService.buscaClienteOuException(uuidCliente);
        enderecoService.insereEndereco(enderecoDTO, cliente.getId());
    }

    /**
     * Busca um endereco por cep
     *
     * @param cep - CEP a ser buscado
     * @return Endereco encontrado
     */
    public EnderecoDTO buscaEnderecoPorCep(String cep) {
        enderecoService.cepValidoOuException(cep);
        ViaCepDTO viaCepDto = enderecoService.buscaDadosCep(cep);
        return enderecoService.criaEnderecoDto(viaCepDto);
    }

    /**
     * Busca cliente lista de clientes de forma paginada
     *
     * @param pageable - Configuração da paginação
     * @param cpf      - Filtro por cpf
     * @param nome     - Filtro por nome
     * @return Página de clientes
     */
    public Page<ClienteDTO> buscaClientes(Pageable pageable, String cpf, String nome) {
        return dadosPessoaisService.buscaClientes(pageable, cpf, nome);
    }

    /**
     * Atualiza um cliente
     *
     * @param uuid       - UUID do cliente a ser atualizado
     * @param clienteDto - Novos dados do cliente
     */
    public void atualizaCliente(UUID uuid, ClienteDTO clienteDto) {
        Cliente cliente = dadosPessoaisService.buscaClienteOuException(uuid);
        enderecoService.validaEnderecoClienteOuException(clienteDto.getEndereco(), cliente.getId());
        telefoneService.validaTelefoneClienteOuException(clienteDto.getTelefone(), cliente.getId());
        dadosPessoaisService.cpfValidoOuException(clienteDto.getDadosPessoais().getCpf());
        dadosPessoaisService.atualizarDadosPessoais(cliente, clienteDto.getDadosPessoais());
        enderecoService.atualizaEndereco(clienteDto.getEndereco());
        telefoneService.atualizaTelefone(clienteDto.getTelefone());
    }

    /**
     * Busca um endereco por uuid
     *
     * @param uuid - UUID do endereco a ser buscado
     * @return Endereco encontrado
     */
    public EnderecoDTO buscaEndereco(UUID uuid) {
        Endereco endereco = enderecoService.buscaEnderecoOuException(uuid);
        return enderecoService.converteEnderecoEmDTO(endereco);
    }

    /**
     * Deleta um endereco do banco de dados
     *
     * @param uuid - UUID do endereco a ser deletado
     */
    public void deletaEndereco(UUID uuid) {
        Endereco endereco = enderecoService.buscaEnderecoOuException(uuid);
        enderecoService.deletaEndereco(endereco);
    }

    /**
     * Insere um telefone no banco de dados
     *
     * @param uuidCliente - UUID do cliente a ser inserido
     * @param telefoneDTO - Telefone a ser inserido
     */
    public void insereTelefone(UUID uuidCliente, TelefoneDTO telefoneDTO) {
        Cliente cliente = dadosPessoaisService.buscaClienteOuException(uuidCliente);
        telefoneService.insereTelefone(telefoneDTO, cliente.getId());
    }

    /**
     * Atualiza um telefone no banco de dados
     *
     * @param telefoneDTO - Telefone a ser atualizado
     */
    public void atualizaTelefone(TelefoneDTO telefoneDTO) {
        telefoneService.atualizaTelefone(telefoneDTO);
    }

    /**
     * Deleta um telefone do banco de dados
     *
     * @param uuid - UUID do telefone a ser deletado
     */
    public void deletaTelefone(UUID uuid) {
        telefoneService.deletaTelefone(uuid);
    }

    /**
     * Busca um telefone por uuid
     *
     * @param uuid - UUID do telefone a ser buscado
     * @return Telefone encontrado
     */
    public TelefoneDTO buscaTelefone(UUID uuid) {
        Telefone telefone = telefoneService.buscaTelefoneOuException(uuid);
        return telefoneService.converteTelefoneEmDTO(telefone);
    }
}