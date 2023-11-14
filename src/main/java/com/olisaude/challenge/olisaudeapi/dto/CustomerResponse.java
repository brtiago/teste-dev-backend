package com.olisaude.challenge.olisaudeapi.dto;

import com.olisaude.challenge.olisaudeapi.model.Customer;
import com.olisaude.challenge.olisaudeapi.model.CustomerGender;

public record CustomerResponse(
        Long id,
        String name,
        String birthDate,
        CustomerGender gender,
        String healthProblems,
        String degree
) {
    public CustomerResponse(Long id, String name, String birthDate, CustomerGender gender, String healthProblems, String degree) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.healthProblems = healthProblems;
        this.degree = degree;
    }

    public CustomerResponse(Customer customer) {
        this(customer.getId(), customer.getName(), String.valueOf(customer.getBirthDate()), customer.getGender(), null, null);
    }
}
