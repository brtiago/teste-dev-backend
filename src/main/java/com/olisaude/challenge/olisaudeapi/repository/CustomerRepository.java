package com.olisaude.challenge.olisaudeapi.repository;

import com.olisaude.challenge.olisaudeapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findById(Long id);
    @Query(value = "select c from Customer c where upper(trim(c.name)) like %?1%")
    List<Customer> findByNameIgnoreCase(String name);
    List<Customer> findAllByActiveTrue();

    List<Customer> findTop10ByOrderByHealthScoreDesc();

}
