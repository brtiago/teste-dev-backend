package com.olisaude.challenge.olisaudeapi.service;

import com.olisaude.challenge.olisaudeapi.dto.CostumerRequest;
import com.olisaude.challenge.olisaudeapi.model.Costumer;
import com.olisaude.challenge.olisaudeapi.repository.CostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CostumerService {
    @Autowired
    private CostumerRepository cr;
    public Costumer createCostumer(CostumerRequest request) {
        Costumer costumer = new Costumer(request);
        System.out.println(costumer);
        return cr.save(costumer);
    }
}
