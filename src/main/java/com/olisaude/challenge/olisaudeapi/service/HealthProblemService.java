package com.olisaude.challenge.olisaudeapi.service;

import com.olisaude.challenge.olisaudeapi.dto.HealthProblemRequest;
import com.olisaude.challenge.olisaudeapi.dto.HealthProblemResponse;
import com.olisaude.challenge.olisaudeapi.model.HealthProblem;
import com.olisaude.challenge.olisaudeapi.repository.HealthProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HealthProblemService {

    @Autowired
    private HealthProblemRepository repository;

    public void create(HealthProblemRequest request) {
        HealthProblem healthProblem = new HealthProblem(request);
        repository.save(healthProblem);
    }

    public List listAll(){
        return repository.findAll()
                .stream()
                .map(healthProblem -> new HealthProblemResponse(healthProblem))
                .collect(Collectors.toList());
    }


}
