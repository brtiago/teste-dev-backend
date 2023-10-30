package com.olisaude.challenge.olisaudeapi.model;

import com.olisaude.challenge.olisaudeapi.dto.CostumerRequest;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Costumer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String birthDate;
    @Enumerated(EnumType.STRING)
    private CostumerGender gender;
    private String healthProblems;
    private HealthProblemDegree degree;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean active;

    public Costumer(CostumerRequest request) {
        this.name = request.name();
        this.birthDate = request.birthDate();
        this.gender = request.gender();
        this.healthProblems = request.healthProblems();
        this.degree = request.degree();
        this.createdAt = LocalDateTime.now();
        this.active = true;
    }

    public void delete() {
        this.active = false;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Costumer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", gender='" + gender + '\'' +
                ", healthProblems='" + healthProblems + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", active=" + active +
                '}';
    }
}
