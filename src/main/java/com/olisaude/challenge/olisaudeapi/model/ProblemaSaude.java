package com.olisaude.challenge.olisaudeapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

@Entity
@Getter
@Setter
@EqualsAndHashCode

public class ProblemaSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Enumerated(EnumType.STRING)
    private GrauProblemaSaude grau;

    private boolean ativo = true;

    public ProblemaSaude() {}

    public ProblemaSaude(String nome, GrauProblemaSaude grau) {
        this.nome = nome;
        this.grau = grau;
        this.ativo = true;
    }
}
