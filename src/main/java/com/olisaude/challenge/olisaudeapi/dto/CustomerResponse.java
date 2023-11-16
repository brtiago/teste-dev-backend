package com.olisaude.challenge.olisaudeapi.dto;

import com.olisaude.challenge.olisaudeapi.model.Customer;
import com.olisaude.challenge.olisaudeapi.model.CustomerGender;

import java.util.List;
import java.util.stream.Collectors;

public record CustomerResponse(
        Long id,
        String name,
        String birthDate,
        CustomerGender gender,
        List<HealthProblemResponse> healthProblems
) {
    public CustomerResponse(Long id, String name, String birthDate, CustomerGender gender, List<HealthProblemResponse> healthProblems) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.healthProblems = healthProblems;
    }

    public CustomerResponse(Customer customer) {
        this(customer.getId(),
                customer.getName(),
                String.valueOf(customer.getBirthDate()),
                customer.getGender(),
                customer.getHealthProblems().stream()
                        .map(HealthProblemResponse::new)
                        .collect(Collectors.toList()));
    }
}
