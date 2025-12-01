package com.olisaude.challenge.olisaudeapi.dto;

import com.olisaude.challenge.olisaudeapi.model.GrauProblemaSaude;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de resposta de um problema de saúde")
public record ProblemaSaudeResponse(
        @Schema(description = "Nome do problema de saúde", example = "Diabetes")
        String nome,

        @Schema(description = "Grau de severidade do problema", example = "GRAU_2")
        GrauProblemaSaude grau
) {}
