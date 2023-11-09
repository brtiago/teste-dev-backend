package com.olisaude.challenge.olisaudeapi.controller;

import com.olisaude.challenge.olisaudeapi.dto.CostumerResponse;
import com.olisaude.challenge.olisaudeapi.dto.HealthProblemListResponse;
import com.olisaude.challenge.olisaudeapi.dto.HealthProblemRequest;
import com.olisaude.challenge.olisaudeapi.dto.HealthProblemResponse;
import com.olisaude.challenge.olisaudeapi.model.HealthProblem;
import com.olisaude.challenge.olisaudeapi.repository.HealthProblemRepository;
import com.olisaude.challenge.olisaudeapi.service.HealthProblemService;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/healthproblem")
public class HealthProblemController {

    @Autowired
    private HealthProblemService service;

    @Autowired
    private HealthProblemRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity<String> create (@RequestBody HealthProblemRequest request) {
        try {
            this.service.getOrCreateHealthProblem(request);
            return ResponseEntity.ok("A new health problem has been registered");
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<List<HealthProblemResponse>> listAll() {
        List<HealthProblemResponse> response = this.service.listAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<HealthProblem> update(@PathVariable Long id,@RequestBody HealthProblemRequest request) {
        HealthProblem healthProblem = repository.getReferenceById(id);
        healthProblem.update(request);
        return ResponseEntity.ok(healthProblem);
    }

}
