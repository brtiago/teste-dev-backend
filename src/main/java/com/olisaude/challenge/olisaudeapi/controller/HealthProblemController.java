package com.olisaude.challenge.olisaudeapi.controller;

import com.olisaude.challenge.olisaudeapi.dto.*;
import com.olisaude.challenge.olisaudeapi.model.HealthProblem;
import com.olisaude.challenge.olisaudeapi.service.HealthProblemService;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/healthproblem")
public class HealthProblemController {

    @Autowired
    private HealthProblemService service;


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
    public ResponseEntity<HealthProblem> update(@PathVariable Long id, @RequestBody HealthProblemUpdate dto) {
        service.update(id, dto);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
