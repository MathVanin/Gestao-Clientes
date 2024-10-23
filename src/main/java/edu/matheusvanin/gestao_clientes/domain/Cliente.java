package edu.matheusvanin.gestao_clientes.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cliente")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private UUID uuid;
    private String nome;
    private String cpf;
    private String email;
    @OneToMany(mappedBy = "clienteId")
    private List<Endereco> endereco;
    @OneToMany(mappedBy = "clienteId")
    private List<Telefone> telefone;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    @PrePersist
    public void prePersist() {
        this.uuid = UUID.randomUUID();
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }

    @PreRemove
    public void preRemove() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}
