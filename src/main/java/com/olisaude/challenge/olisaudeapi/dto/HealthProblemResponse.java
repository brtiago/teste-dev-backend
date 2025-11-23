package com.olisaude.challenge.olisaudeapi.dto;

import com.olisaude.challenge.olisaudeapi.model.HealthProblem;

public record HealthProblemResponse(
        String name,
        String degree
) {

    public HealthProblemResponse (HealthProblem healthProblem){
        this(
                healthProblem.getName(),
                healthProblem.getDegree()
        );
    }
}
