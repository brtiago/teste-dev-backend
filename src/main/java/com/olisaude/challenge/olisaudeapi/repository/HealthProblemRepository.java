package com.olisaude.challenge.olisaudeapi.repository;

import com.olisaude.challenge.olisaudeapi.model.HealthProblem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HealthProblemRepository extends JpaRepository<HealthProblem, Long> {
    Optional<HealthProblem> findByName(String name);
    List<HealthProblem> findAllByActiveTrue();

    List<HealthProblem> findByNameInAndActiveTrue(List<String> healthProblems);

}
