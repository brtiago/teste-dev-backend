package com.olisaude.challenge.olisaudeapi.model;

import com.olisaude.challenge.olisaudeapi.dto.ClienteRequest;
import com.olisaude.challenge.olisaudeapi.dto.ProblemaSaudeRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@Table(name = "clientes")
@Getter
@ToString
@EqualsAndHashCode(of = "id")

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotBlank
    private String nome;

    @Setter
    @NotBlank
    @Size(min = 11, max = 11)
    @Column(unique = true)
    private String cpf;

    @Setter
    @NotNull
    private LocalDate dataNascimento;

    @Setter
    @Enumerated(EnumType.STRING)
    private GeneroCliente genero;
    private LocalDateTime dataCriacao;
    @Setter
    private LocalDateTime dataAtualizacao;
    private int sd;
    private double score;
    @Setter
    private boolean ativo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "cliente_problema_saude",
            joinColumns = @JoinColumn(name = "id_cliente"),
            inverseJoinColumns = @JoinColumn(name = "id_problema_saude")
    )
    private List<ProblemaSaude> problemaSaude;

    protected Cliente(){}

    public Cliente(ClienteRequest request) {
        this.nome = request.nome();
        this.cpf = request.cpf();
        this.dataNascimento = request.dataNascimento();
        this.genero = request.genero();

        this.problemaSaude = request.problemaSaude() != null ?
            request.problemaSaude().stream()
                    .map(problema -> new ProblemaSaude(problema.nome(), problema.grau()))
                    .collect(Collectors.toList()) : new ArrayList<>();
        dataCriacao = LocalDateTime.now();
        this.ativo = true;
        atualizarCalculos();
    }

    private void atualizarCalculos() {
        this.sd = calcularSd();
        this.score = calcularScore();
    }

    private Integer calcularSd() {
        return Optional.ofNullable(problemaSaude)
                .orElse(Collections.emptyList())
                .stream()
                .mapToInt(problema -> problema.getGrau().getValue())
                .sum();
    }

    public void desativar() {
        this.ativo = false;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public double calcularScore() {
        int sd = calcularSd();
        // score = (1 / (1 + eˆ-(-2.8 + sd ))) * 100
        return (1.0 / (1.0 + Math.exp(-(-2.8 + sd)))) * 100;
    }

    public void setProblemaSaude(List<ProblemaSaude> problemaSaude) {
        this.problemaSaude = problemaSaude != null ? problemaSaude : new ArrayList<>();
        atualizarCalculos();
    }

    public void setProblemaSaudeFromRequest(List<ProblemaSaudeRequest> problemaSaudeRequest) {
        this.problemaSaude = problemaSaudeRequest != null ?
                problemaSaudeRequest.stream()
                        .map(problema -> new ProblemaSaude(problema.nome(), problema.grau()))
                        .collect(Collectors.toList()) : new ArrayList<>();
        atualizarCalculos();
    }

    public void atualizarData() {
        this.dataAtualizacao = LocalDateTime.now();
    }

}
