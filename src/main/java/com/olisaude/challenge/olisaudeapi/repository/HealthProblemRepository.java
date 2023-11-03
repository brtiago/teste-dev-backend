package com.olisaude.challenge.olisaudeapi.repository;

import com.olisaude.challenge.olisaudeapi.model.HealthProblem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthProblemRepository extends JpaRepository<HealthProblem, Long> {
}
