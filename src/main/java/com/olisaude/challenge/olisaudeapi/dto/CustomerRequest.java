package com.olisaude.challenge.olisaudeapi.dto;

import com.olisaude.challenge.olisaudeapi.model.CustomerGender;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(

        @NotBlank
        String name,
        @NotBlank
        String birthDate,
        @NotBlank
        CustomerGender gender,
        Long healthProblem,
        String degree
) {
}
