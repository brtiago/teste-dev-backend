package com.olisaude.challenge.olisaudeapi.service;

import com.olisaude.challenge.olisaudeapi.dto.CostumerRequest;
import com.olisaude.challenge.olisaudeapi.dto.CostumerResponse;
import com.olisaude.challenge.olisaudeapi.model.Costumer;
import com.olisaude.challenge.olisaudeapi.repository.CostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CostumerService {
    @Autowired
    private CostumerRepository repository;
    public Costumer createCostumer(@RequestBody CostumerRequest request) {
        Costumer costumer = new Costumer(request);
        System.out.println(costumer);
        return repository.save(costumer);
    }

    public List<Costumer> listAll(){
        return repository.findAll();
    }

    public void delete(Long id) {
        var costumer = repository.getReferenceById(id);
        costumer.delete();
    }

    public Costumer updateCostumer(Long id, CostumerRequest request){
        var costumer = repository.getReferenceById(id);
        costumer.update(request);

        return costumer;
    }

    public List<CostumerResponse> listRiskier() {
        return repository.findAllByActiveTrue()
                .stream()
                .map(CostumerResponse::new)
                .collect(Collectors.toList());
    }


}
