package com.olisaude.challenge.olisaudeapi.dto;

import com.olisaude.challenge.olisaudeapi.model.CostumerGender;
import com.olisaude.challenge.olisaudeapi.model.HealthProblemDegree;
import jakarta.validation.constraints.NotBlank;

public record CostumerResponse(
        Long id,
        String name,
        String birthDate,
        CostumerGender gender,
        String healthProblems,
        HealthProblemDegree degree
) {
}
