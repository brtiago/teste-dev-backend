package com.olisaude.challenge.olisaudeapi.model;

import com.olisaude.challenge.olisaudeapi.dto.ClienteRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
            name = "cliente_condicao_saude",
            joinColumns = @JoinColumn(name = "id_cliente"),
            inverseJoinColumns = @JoinColumn(name = "id_condicao_saude")
    )
    private List<CondicaoSaude> condicaoSaude;

    protected Cliente(){}

    public Cliente(ClienteRequest request) {
        this.nome = request.nome();
        this.cpf = request.cpf();
        this.dataNascimento = request.dataNascimento();
        this.genero = request.genero();
        this.condicaoSaude = request.condicaoSaude();
        dataCriacao = LocalDateTime.now();
        this.ativo = true;
        atualizarCalculos();
    }

    private void atualizarCalculos() {
        this.sd = calcularSd();
        this.score = calcularScore();
    }

    private Integer calcularSd() {
        if(condicaoSaude == null) {
            return 0;
        }

        return condicaoSaude.stream()
                .mapToInt(problema -> problema.getGrau().getValue())
                .sum();
    }

    public double calcularScore() {
        int sd = calcularSd();
        // score = (1 / (1 + eˆ-(-2.8 + sd ))) * 100
        return (1.0 / (1.0 + Math.exp(2.8 - sd) )) * 100;
    }

    public void setCondicaoSaude(List<CondicaoSaude> condicaoSaude) {
        this.condicaoSaude = condicaoSaude;
        atualizarCalculos();
    }

    public void atualizarData() {
        this.dataAtualizacao = LocalDateTime.now();
    }

}
