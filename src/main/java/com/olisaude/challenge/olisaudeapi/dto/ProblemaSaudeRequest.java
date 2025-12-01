package com.olisaude.challenge.olisaudeapi.dto;

import com.olisaude.challenge.olisaudeapi.model.GrauProblemaSaude;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de um problema de saúde do cliente")
public record ProblemaSaudeRequest(
        @Schema(description = "Nome do problema de saúde", example = "Diabetes")
        String nome,

        @Schema(description = "Grau de severidade do problema (GRAU_1 ou GRAU_2)", example = "GRAU_2")
        GrauProblemaSaude grau
) {}
