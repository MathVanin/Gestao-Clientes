package edu.matheusvanin.gestao_clientes.utils;

public class Constantes {

    private Constantes() {
        throw new IllegalStateException("Utility class");
    }

    // CLIENTE
    public static final String CLIENTE_CADASTRADO = "Cliente cadastrado com sucesso";
    public static final String CLIENTE_ATUALIZADO = "Cliente atualizado com sucesso";
    public static final String CLIENTE_DELETADO = "Cliente deletado com sucesso";
    public static final String DADOS_PESSOAIS_INSERIDOS = "Dados pessoais inseridos com sucesso";
    public static final String DADOS_PESSOAIS_ATUALIZADO = "Dados pessoais atualizados com sucesso";
    public static final String CPF_INVALIDO = "CPF inválido";

    // ENDERECO
    public static final String CEP_INVALIDO = "CEP inválido";
    public static final String ENDERECO_INVALIDO = "Endereço inválido";

    // TELEFONE
    public static final String TELEFONE_INVALIDO = "Telefone inválido";

    // LOG
    public static final String LOG_NAO_ENCONTRADO = "Log não encontrado";
    public static final String TIMESTAMP = "timestamp";


}
