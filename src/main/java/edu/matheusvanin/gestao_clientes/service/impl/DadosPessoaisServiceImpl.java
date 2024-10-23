package edu.matheusvanin.gestao_clientes.service.impl;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import edu.matheusvanin.gestao_clientes.domain.Cliente;
import edu.matheusvanin.gestao_clientes.dto.ClienteDTO;
import edu.matheusvanin.gestao_clientes.dto.DadosPessoaisDTO;
import edu.matheusvanin.gestao_clientes.exception.ArgumentoInvalidoException;
import edu.matheusvanin.gestao_clientes.mapper.GestaoClienteMapper;
import edu.matheusvanin.gestao_clientes.repository.ClienteRepository;
import edu.matheusvanin.gestao_clientes.service.DadosPessoaisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static edu.matheusvanin.gestao_clientes.utils.Constantes.CPF_INVALIDO;

@Service
@RequiredArgsConstructor
public class DadosPessoaisServiceImpl implements DadosPessoaisService {

    private final ClienteRepository clienteRepository;
    private final GestaoClienteMapper mapper;

    @Override
    public Cliente insereDadosPessoais(DadosPessoaisDTO dadosPessoaisDto) {
        Cliente cliente = mapper.dadosPessoaisDtoToCliente(dadosPessoaisDto);
        return clienteRepository.save(cliente);
    }

    @Override
    public Page<DadosPessoaisDTO> buscaDadosPessoais(Pageable pageable, String cpf, String nome) {
        Page<Cliente> dadosPessoaisPage = clienteRepository.paginadoComFiltros(pageable, cpf, nome);
        List<DadosPessoaisDTO> dadosPessoaisDtos = mapper.clienteListToDadosPessoaisDtoList(dadosPessoaisPage.getContent());
        return new PageImpl<>(dadosPessoaisDtos, pageable, dadosPessoaisPage.getTotalElements());
    }

    @Override
    public void atualizarDadosPessoais(Cliente cliente, DadosPessoaisDTO dadosPessoaisDto) {
        cliente.setNome(dadosPessoaisDto.getNome());
        cliente.setCpf(dadosPessoaisDto.getCpf());
        cliente.setEmail(dadosPessoaisDto.getEmail());
        clienteRepository.save(cliente);
    }

    @Override
    public void deletaCliente(Cliente cliente) {
        clienteRepository.delete(cliente);
    }

    @Override
    public Cliente buscaClienteOuException(UUID uuid) {
        return clienteRepository.findByUuid(uuid).orElseThrow(() ->
                new IllegalArgumentException("Cliente não encontrado"));
    }

    @Override
    public Page<ClienteDTO> buscaClientes(Pageable pageable, String cpf, String nome) {
        Page<Cliente> cliente = clienteRepository.paginadoComFiltros(pageable, cpf, nome);
        List<ClienteDTO> clienteListDto = mapper.clienteListToClienteDtoList(cliente.getContent());
        return new PageImpl<>(clienteListDto, pageable, cliente.getTotalElements());
    }

    @Override
    public boolean cpfValido(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        try {
            cpfValidator.assertValid(cpf);
            return true;
        } catch (InvalidStateException e) {
            return false;
        }
    }

    @Override
    public void cpfValidoOuException(String cpf) {
        if (!cpfValido(cpf)) {
            throw new ArgumentoInvalidoException(CPF_INVALIDO);
        }
    }

}
