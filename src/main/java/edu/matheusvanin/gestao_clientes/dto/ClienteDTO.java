package edu.matheusvanin.gestao_clientes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO implements Serializable {
    private DadosPessoaisDTO dadosPessoais;
    private List<EnderecoDTO> endereco;
    private List<TelefoneDTO> telefone;
}
