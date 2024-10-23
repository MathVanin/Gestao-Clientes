package edu.matheusvanin.gestao_clientes.mapper;

import edu.matheusvanin.gestao_clientes.auditoria.Log;
import edu.matheusvanin.gestao_clientes.domain.Cliente;
import edu.matheusvanin.gestao_clientes.domain.Endereco;
import edu.matheusvanin.gestao_clientes.domain.Telefone;
import edu.matheusvanin.gestao_clientes.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GestaoClienteMapper {

    // Mapeamentos de dados pessoais e cliente
    DadosPessoaisDTO clienteToDadosPessoaisDto(Cliente cliente);

    List<DadosPessoaisDTO> clienteListToDadosPessoaisDtoList(List<Cliente> clienteList);

    Cliente dadosPessoaisDtoToCliente(DadosPessoaisDTO dto);

    @Mapping(target = "dadosPessoais", source = "cliente")
    ClienteDTO clienteToClienteDto(Cliente cliente);

    List<ClienteDTO> clienteListToClienteDtoList(List<Cliente> clienteList);

    EnderecoDTO viaCepDtoToEnderecoDto(ViaCepDTO dto);

    // Mapeamentos de endereço
    EnderecoDTO enderecoToDto(Endereco endereco);

    Endereco enderecoDtotoEndereco(EnderecoDTO enderecoDto);

    List<Endereco> enderecoDtoListToEnderecoList(List<EnderecoDTO> enderecoDTOList);

    // Mapeamentos de telefone
    TelefoneDTO telefoneToDto(Telefone telefone);

    Telefone telefoneDtoToTelefone(TelefoneDTO dto);

    List<Telefone> telefoneDtoListToTelefoneList(List<TelefoneDTO> dto);

    // Mapeamento de log
    @Mapping(target = "corpoEnvio", source = "body")
    @Mapping(target = "metodo", source = "method")
    @Mapping(target = "conteudoResposta", source = "responseContent")
    @Mapping(target = "statusResposta", source = "responseStatus")
    @Mapping(target = "timestamp", source = "timestamp", dateFormat = "dd-MM-yyyy HH:mm:ss")
    LogDTO pageLogToPageDto(Log log);

    List<LogDTO> listLogToListDto(List<Log> log);

}
