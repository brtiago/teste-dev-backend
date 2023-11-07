package com.olisaude.challenge.olisaudeapi.model;

import com.olisaude.challenge.olisaudeapi.dto.CostumerRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "costumers")

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Costumer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private CostumerGender gender;

    @ManyToMany
    @JoinTable(
            name = "customer_health_problems",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "health_problem_id")
    )
    private List<HealthProblem> healthProblem;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean active;

    public Costumer(CostumerRequest request) {
        this.name = request.name();
        this.birthDate = LocalDate.parse(request.birthDate());
        this.healthProblem = new ArrayList<>();
        this.gender = request.gender();
        this.createdAt = LocalDateTime.now();
        this.active = true;
    }

    public void delete() {
        this.active = false;
        this.updatedAt = LocalDateTime.now();
    }

    public void update(CostumerRequest request) {
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
