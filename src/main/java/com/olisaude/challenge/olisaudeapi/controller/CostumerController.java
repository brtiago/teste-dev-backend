package com.olisaude.challenge.olisaudeapi.controller;

import com.olisaude.challenge.olisaudeapi.dto.CostumerRequest;
import com.olisaude.challenge.olisaudeapi.dto.CostumerResponse;
import com.olisaude.challenge.olisaudeapi.model.Costumer;
import com.olisaude.challenge.olisaudeapi.service.CostumerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/costumer")
public class CostumerController {

    @Autowired
    private CostumerService cs;

    @Transactional
    @PostMapping
    public ResponseEntity<CostumerResponse> create (@RequestBody CostumerRequest request) throws Exception {
        Costumer costumer = cs.createCostumer(request);
        CostumerResponse response = new CostumerResponse(costumer);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CostumerResponse>> listAll() {
        List<CostumerResponse> costumerResponses = cs.listAll()
                .stream()
                .map(costumer -> new CostumerResponse(costumer))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(costumerResponses);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCostumer(@PathVariable Long id){
        cs.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping
    public ResponseEntity updateCostumer(@RequestBody CostumerRequest request){
        return cs.updateCostumer(request);
    }

}

