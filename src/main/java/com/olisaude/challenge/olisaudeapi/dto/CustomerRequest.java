package com.olisaude.challenge.olisaudeapi.dto;

import com.olisaude.challenge.olisaudeapi.model.CustomerGender;
import com.olisaude.challenge.olisaudeapi.model.HealthProblem;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public record CustomerRequest(

        @NotBlank
        String name,
        @NotBlank
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate birthDate,
        @NotBlank
        CustomerGender gender,
        List<HealthProblemRequest> healthProblem,
        String degree
) {
}
