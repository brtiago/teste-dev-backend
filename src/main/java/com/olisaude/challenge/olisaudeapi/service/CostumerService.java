package com.olisaude.challenge.olisaudeapi.service;

import com.olisaude.challenge.olisaudeapi.dto.CostumerRequest;
import com.olisaude.challenge.olisaudeapi.dto.CostumerResponse;
import com.olisaude.challenge.olisaudeapi.model.Costumer;
import com.olisaude.challenge.olisaudeapi.repository.CostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CostumerService {
    @Autowired
    private CostumerRepository cr;
    public Costumer createCostumer(@RequestBody CostumerRequest request) {
        Costumer costumer = new Costumer(request);
        System.out.println(costumer);
        return cr.save(costumer);
    }

    public List<Costumer> listAll(){
        return cr.findAll();
    }


    public void delete(Long id) {
        var costumer = cr.getReferenceById(id);
        costumer.delete();
    }

    public Costumer updateCostumer(Long id, CostumerRequest request){
        var costumer = cr.getReferenceById(id);
        costumer.update(request);

        return costumer;
    }


}
