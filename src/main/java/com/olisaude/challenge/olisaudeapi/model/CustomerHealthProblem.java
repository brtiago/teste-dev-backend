package com.olisaude.challenge.olisaudeapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "costumer_health_problems")
public class CustomerHealthProblem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "costumer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "health_problem_id")
    private HealthProblem healthProblem;
}