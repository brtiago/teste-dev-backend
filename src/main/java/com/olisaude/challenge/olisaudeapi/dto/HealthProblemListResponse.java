package com.olisaude.challenge.olisaudeapi.dto;

import com.olisaude.challenge.olisaudeapi.model.HealthProblem;

public record HealthProblemListResponse (String name){
    public HealthProblemListResponse(HealthProblem healthProblem){
        this(healthProblem.getName());
    }
}
