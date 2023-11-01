package com.olisaude.challenge.olisaudeapi.repository;

import com.olisaude.challenge.olisaudeapi.model.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CostumerRepository extends JpaRepository<Costumer, Long> {
    Optional<Costumer> findById(Long id);
    Optional<Costumer> findByName(String name);
}
