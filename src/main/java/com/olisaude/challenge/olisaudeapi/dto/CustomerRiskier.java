package com.olisaude.challenge.olisaudeapi.dto;

import com.olisaude.challenge.olisaudeapi.model.Customer;

import java.util.List;
import java.util.stream.Collectors;

public record CustomerRiskier(
        Long id,
        String name,
        Double healthScore,
        List<HealthProblemListResponse> healthProblems
) {
    public CustomerRiskier(Long id, String name, Double healthScore, List<HealthProblemListResponse> healthProblems) {
        this.id = id;
        this.name = name;
        this.healthScore = healthScore;
        this.healthProblems = healthProblems;
    }

    public CustomerRiskier(Customer customer) {
        this(customer.getId(),
                customer.getName(),
                customer.getHealthScore(),
                customer.getHealthProblems().stream()
                        .map(HealthProblemListResponse::new)
                        .collect(Collectors.toList()));
    }

}
