package com.olisaude.challenge.olisaudeapi.model;

import com.olisaude.challenge.olisaudeapi.dto.ProblemaSaudeRequest;
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

    public ProblemaSaude() {
        this.grau = GrauProblemaSaude.GRAU_1;
    }

    public ProblemaSaude(String nome, GrauProblemaSaude grau) {
        this.nome = nome;
        this.grau = grau != null ? grau : GrauProblemaSaude.GRAU_1;
        this.ativo = true;
    }

    public ProblemaSaude(ProblemaSaudeRequest request) {
        this.nome = request.nome();
        this.grau = request.grau() != null ? request.grau() : GrauProblemaSaude.GRAU_1;
        this.ativo = true;
    }
}
