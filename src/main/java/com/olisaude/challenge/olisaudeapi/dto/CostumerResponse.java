package com.olisaude.challenge.olisaudeapi.dto;

import com.olisaude.challenge.olisaudeapi.model.Costumer;
import com.olisaude.challenge.olisaudeapi.model.CostumerGender;

public record CostumerResponse(
        Long id,
        String name,
        String birthDate,
        CostumerGender gender,
        String healthProblems,
        String degree
) {
    public CostumerResponse(Long id, String name, String birthDate, CostumerGender gender, String healthProblems, String degree) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.healthProblems = healthProblems;
        this.degree = degree;
    }

    public CostumerResponse(Costumer costumer) {
        this(costumer.getId(), costumer.getName(), String.valueOf(costumer.getBirthDate()), costumer.getGender(), null, null);
    }
}
