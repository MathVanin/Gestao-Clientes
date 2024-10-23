package edu.matheusvanin.gestao_clientes.auditoria;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "logs")
@Builder
@Data
public class Log {
    @Id
    private String id;
    private String body;
    private String method;
    private String endpoint;
    private String responseContent;
    private int responseStatus;
    private LocalDateTime timestamp;
}
