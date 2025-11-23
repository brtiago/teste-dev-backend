package com.olisaude.challenge.olisaudeapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "condicao_saude")
@Getter
@Setter
@EqualsAndHashCode

public class CondicaoSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Enumerated(EnumType.STRING)
    private GrauProblemaSaude grau;

    private boolean ativo = true;
}
