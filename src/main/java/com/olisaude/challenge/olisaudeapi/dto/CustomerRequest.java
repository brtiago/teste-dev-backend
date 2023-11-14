package com.olisaude.challenge.olisaudeapi.dto;

import com.olisaude.challenge.olisaudeapi.model.CustomerGender;
import com.olisaude.challenge.olisaudeapi.model.HealthProblem;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CustomerRequest(

        @NotBlank
        String name,
        @NotBlank
        String birthDate,
        @NotBlank
        CustomerGender gender,
        List<HealthProblem> healthProblem,
        String degree
) {
}
