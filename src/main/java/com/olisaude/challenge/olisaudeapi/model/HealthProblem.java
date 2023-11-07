package com.olisaude.challenge.olisaudeapi.model;

import com.olisaude.challenge.olisaudeapi.dto.HealthProblemRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "health_problems")

@ToString
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
    @ManyToMany(mappedBy = "healthProblem")
    private List<Costumer> costumerList;

    public HealthProblem(HealthProblemRequest request) {
        this.name = request.name();
        this.degree = request.degree();
        this.active = true;
    }

    public void delete() {
        this.active = false;
    }

    public void update(HealthProblemRequest request) {
        if (request.name() != null){
            this.name = request.name();
        }
        if (request.degree() != null){
            this.degree = request.degree();
        }
    }
}
