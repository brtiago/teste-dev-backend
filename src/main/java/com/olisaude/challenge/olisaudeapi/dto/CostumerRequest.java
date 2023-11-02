package com.olisaude.challenge.olisaudeapi.dto;

import com.olisaude.challenge.olisaudeapi.model.CostumerGender;
import jakarta.validation.constraints.NotBlank;

public record CostumerRequest(

        @NotBlank
        String name,
        @NotBlank
        String birthDate,
        @NotBlank
        CostumerGender gender,
        String healthProblem,
        String degree
) {
}
