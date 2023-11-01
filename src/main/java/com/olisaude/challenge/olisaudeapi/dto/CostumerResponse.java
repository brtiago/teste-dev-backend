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

    public CostumerResponse(Costumer costumer) {
        this(   costumer.getId(),
                costumer.getName(),
                String.valueOf(costumer.getBirthDate()),
                costumer.getGender(),
                costumer.getHealthProblem().getName(),
                costumer.getHealthProblem().getDegree()
        );
    }


}
