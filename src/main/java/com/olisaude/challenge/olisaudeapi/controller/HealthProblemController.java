package com.olisaude.challenge.olisaudeapi.controller;

import com.olisaude.challenge.olisaudeapi.dto.CostumerResponse;
import com.olisaude.challenge.olisaudeapi.dto.HealthProblemRequest;
import com.olisaude.challenge.olisaudeapi.dto.HealthProblemResponse;
import com.olisaude.challenge.olisaudeapi.model.HealthProblem;
import com.olisaude.challenge.olisaudeapi.repository.HealthProblemRepository;
import com.olisaude.challenge.olisaudeapi.service.HealthProblemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/healthproblem")
public class HealthProblemController {

    @Autowired
    private HealthProblemRepository hpr;

    @Transactional
    @PostMapping
    public ResponseEntity<HealthProblemResponse> create (@RequestBody HealthProblemRequest request) throws Exception {
        HealthProblem healthProblem = new HealthProblem(request);
        hpr.save(new HealthProblem(request));
        HealthProblemResponse response = new HealthProblemResponse(healthProblem.getId(), healthProblem.getName(), healthProblem.getDegree());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<HealthProblemResponse>> listAll(){
        List<HealthProblemResponse> healthProblemResponse = hpr.findAll()
                .stream()
                .map(healthProblem -> new HealthProblemResponse(healthProblem))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(healthProblemResponse);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<HealthProblem> update(@PathVariable Long id,@RequestBody HealthProblemRequest request) {
        HealthProblem healthProblem = hpr.getReferenceById(id);
        healthProblem.update(request);
        return ResponseEntity.ok(healthProblem);
    }

}
