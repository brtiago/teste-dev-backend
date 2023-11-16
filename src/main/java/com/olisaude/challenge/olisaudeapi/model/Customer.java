package com.olisaude.challenge.olisaudeapi.model;

import com.olisaude.challenge.olisaudeapi.dto.CustomerRequest;
import com.olisaude.challenge.olisaudeapi.service.HealthScoreCalculator;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private CustomerGender gender;
    private int sd;
    private double healthScore;

    @ManyToMany
    @JoinTable(
            name = "costumer_health_problems",
            joinColumns = @JoinColumn(name = "costumer_id"),
            inverseJoinColumns = @JoinColumn(name = "health_problem_id")
    )
    private List<HealthProblem> healthProblems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean active;

    public Customer(CustomerRequest request) {
        this.name = request.name();
        this.birthDate = LocalDate.parse(request.birthDate());
        this.healthProblems = new ArrayList<>(request.healthProblem());
        this.gender = request.gender();
        this.createdAt = LocalDateTime.now();
        this.active = true;
        HealthScoreCalculator calculator = new HealthScoreCalculator();
        this.sd = calculator.getSd(request.healthProblem());
        this.healthScore = calculator.getScore(sd);
    }

    public void delete() {
        this.active = false;
        this.updatedAt = LocalDateTime.now();
    }


    public void update(CustomerRequest request) {
        if (request.name() != null){
            this.name = request.name();
            this.updatedAt = LocalDateTime.now();
        }

        if (request.birthDate() != null){
            this.birthDate = LocalDate.parse(request.birthDate());
            this.updatedAt = LocalDateTime.now();
        }

        if (request.gender() != null){
            this.gender = request.gender();
            this.updatedAt = LocalDateTime.now();
        }
    }
}
