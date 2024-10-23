package edu.matheusvanin.gestao_clientes.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiErrorMessage extends RuntimeException {
    private final HttpStatus status;
    private final String message;
}