package com.olisaude.challenge.olisaudeapi.service;

import com.olisaude.challenge.olisaudeapi.dto.HealthProblemRequest;
import com.olisaude.challenge.olisaudeapi.dto.HealthProblemResponse;
import com.olisaude.challenge.olisaudeapi.dto.HealthProblemUpdate;
import com.olisaude.challenge.olisaudeapi.model.HealthProblem;
import com.olisaude.challenge.olisaudeapi.repository.HealthProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HealthProblemService {

    @Autowired
    private HealthProblemRepository repository;

    public HealthProblem getOrCreateHealthProblem(HealthProblemRequest request){
        return repository.findByName(request.name())
                .orElseGet(() -> {
                    HealthProblem healthProblem = new HealthProblem(request.name(), request.degree());
                    return repository.save(healthProblem);
                });
    }

    public List<HealthProblemResponse> listAll(){
        return repository.findAllByActiveTrue()
                .stream()
                .map(HealthProblemResponse::new)
                .collect(Collectors.toList());
    }

    public HealthProblem update(Long id, HealthProblemUpdate update){
        HealthProblem healthProblem = repository.getReferenceById(id);
        healthProblem.update(update);
        return healthProblem;
    }


    public void delete(Long id) {
        HealthProblem healthProblem = repository.getReferenceById(id);
        healthProblem.delete();
    }
}
