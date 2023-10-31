package com.olisaude.challenge.olisaudeapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table

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

    public HealthProblem(String name, String degree) {
        this.name = name;
        this.degree = degree;
        this.active = true;
    }

    public void delete() {
        this.active = false;
    }

    @Override
    public String toString() {
        return "HealthProblem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", degree='" + degree + '\'' +
                '}';
    }
}
