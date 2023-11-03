package com.olisaude.challenge.olisaudeapi.dto;

import com.olisaude.challenge.olisaudeapi.model.HealthProblem;

public record HealthProblemResponse(
        Long id,
        String name,
        String degree
) {

    public HealthProblemResponse (HealthProblem healthProblem){
        this(
                healthProblem.getId(),
                healthProblem.getName(),
                healthProblem.getDegree()
        );
    }
}
