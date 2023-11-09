package com.olisaude.challenge.olisaudeapi.dto;

import com.olisaude.challenge.olisaudeapi.model.HealthProblem;

public record HealthProblemListResponse (Long id, String name, String degree ){
    public HealthProblemListResponse(HealthProblem healthProblem){
        this(healthProblem.getId(), healthProblem.getName(), healthProblem.getDegree());
    }
}
