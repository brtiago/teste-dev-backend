package com.olisaude.challenge.olisaudeapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.olisaude.challenge.olisaudeapi.dto.HealthProblemRequest;
import com.olisaude.challenge.olisaudeapi.dto.HealthProblemUpdate;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "health_problems")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")

public class HealthProblem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String degree;
    private boolean active;

    @JsonIgnore
    @ManyToMany(mappedBy = "healthProblems")
    private List<Customer> customerList;

    public HealthProblem(HealthProblemRequest request) {
        this.name = request.name();
        this.degree = request.degree();
        this.active = true;
    }

    public HealthProblem(String name, String degree) {
        this.name = name;
        this.degree = degree;
        this.active = true;
    }
    public void delete() {
        this.active = false;
    }

    public void update(HealthProblemUpdate request) {
        if (request.name() != null){
            this.name = request.name();
        }
        if (request.degree() != null){
            this.degree = request.degree();
        }
    }
}
