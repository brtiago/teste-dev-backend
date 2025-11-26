package com.olisaude.challenge.olisaudeapi.repository;

import com.olisaude.challenge.olisaudeapi.model.Cliente;
import com.olisaude.challenge.olisaudeapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByIdAndAtivoTrue(Long id);
    List<Cliente> findTop10ByOrderByScoreDesc();

    Optional<Cliente> findByCpf(String cpf);
}
