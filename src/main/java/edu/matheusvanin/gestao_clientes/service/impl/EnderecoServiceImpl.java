package edu.matheusvanin.gestao_clientes.service.impl;

import com.google.gson.Gson;
import edu.matheusvanin.gestao_clientes.domain.Endereco;
import edu.matheusvanin.gestao_clientes.dto.EnderecoDTO;
import edu.matheusvanin.gestao_clientes.dto.ViaCepDTO;
import edu.matheusvanin.gestao_clientes.exception.ArgumentoInvalidoException;
import edu.matheusvanin.gestao_clientes.mapper.GestaoClienteMapper;
import edu.matheusvanin.gestao_clientes.repository.EnderecoRepository;
import edu.matheusvanin.gestao_clientes.service.EnderecoService;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static edu.matheusvanin.gestao_clientes.utils.Constantes.CEP_INVALIDO;
import static edu.matheusvanin.gestao_clientes.utils.Constantes.ENDERECO_INVALIDO;

@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {

    private final GestaoClienteMapper mapper;
    private final EnderecoRepository enderecoRepository;

    @Override
    public void insereEndereco(EnderecoDTO enderecoDto, Integer clientId) {
        enderecoDto.setClienteId(clientId);
        Endereco endereco = mapper.enderecoDtotoEndereco(enderecoDto);
        enderecoRepository.save(endereco);
    }

    @Override
    public void insereListaEndereco(List<EnderecoDTO> enderecoDtos, Integer clienteId) {
        enderecoDtos.forEach(enderecoDto -> enderecoDto.setClienteId(clienteId));
        List<Endereco> enderecos = mapper.enderecoDtoListToEnderecoList(enderecoDtos);
        enderecoRepository.saveAll(enderecos);
    }

    @Override
    public ViaCepDTO buscaDadosCep(String cep) {
        HttpResponse<String> response = Unirest.get("http://viacep.com.br/ws/" + cep + "/json/").asString();
        if (response.isSuccess()) {
            Gson gson = new Gson();
            return gson.fromJson(response.getBody(), ViaCepDTO.class);
        }
        return null;
    }

    @Override
    public EnderecoDTO criaEnderecoDto(ViaCepDTO viaCepDto) {
        return mapper.viaCepDtoToEnderecoDto(viaCepDto);
    }

    @Override
    public boolean cepValido(String cep) {
        String cepRegex = "^((\\d{2}\\.\\d{3}-\\d{3})|(\\d{2}\\d{3}-\\d{3})|(\\d{8}))$";
        Pattern pattern = Pattern.compile(cepRegex);
        Matcher matcher = pattern.matcher(cep);
        return matcher.find();
    }

    @Override
    public void cepValidoOuException(String cep) {
        if (!cepValido(cep)) {
            throw new ArgumentoInvalidoException(CEP_INVALIDO);
        }
    }

    @Override
    public List<Endereco> buscaEnderecosCliente(Integer clienteId) {
        return enderecoRepository.findByClienteId(clienteId);
    }

    @Override
    public void validaEnderecoClienteOuException(EnderecoDTO enderecoDto, Integer clienteId) {
        List<Endereco> enderecos = buscaEnderecosCliente(clienteId);
        if (enderecos.stream().noneMatch(endereco -> endereco.getUuid().equals(enderecoDto.getUuid()))) {
            throw new IllegalArgumentException(ENDERECO_INVALIDO);
        }
    }

    @Override
    public void validaEnderecoClienteOuException(List<EnderecoDTO> enderecoDtolist, Integer clienteId) {
        List<Endereco> enderecos = buscaEnderecosCliente(clienteId);
        enderecos.forEach(endereco -> {
            if (enderecoDtolist.stream().noneMatch(enderecoDto -> enderecoDto.getUuid().equals(endereco.getUuid()))) {
                throw new IllegalArgumentException(ENDERECO_INVALIDO);
            }
        });
    }

    @Override
    public Endereco buscaEnderecoOuException(UUID uuid) {
        return enderecoRepository.findByUuid(uuid).orElseThrow(
                () -> new IllegalArgumentException(ENDERECO_INVALIDO));
    }

    @Override
    public Endereco atualizaEndereco(EnderecoDTO enderecoDto) {
        Endereco endereco = buscaEnderecoOuException(enderecoDto.getUuid());
        endereco.setCep(enderecoDto.getCep());
        endereco.setLogradouro(enderecoDto.getLogradouro());
        endereco.setBairro(enderecoDto.getBairro());
        endereco.setCidade(enderecoDto.getCidade());
        endereco.setEstado(enderecoDto.getEstado());
        endereco.setComplemento(enderecoDto.getComplemento());
        endereco.setNumero(enderecoDto.getNumero());
        return enderecoRepository.save(endereco);
    }

    @Override
    public List<Endereco> atualizaEndereco(List<EnderecoDTO> enderecoDtos) {
        List<Endereco> enderecos = new ArrayList<>();
        enderecoDtos.forEach(enderecoDTO -> enderecos.add(atualizaEndereco(enderecoDTO)));
        return enderecos;
    }

    @Override
    public EnderecoDTO converteEnderecoEmDTO(Endereco endereco) {
        return mapper.enderecoToDto(endereco);
    }

    @Override
    public void deletaEndereco(Endereco endereco) {
        enderecoRepository.delete(endereco);
    }
}
