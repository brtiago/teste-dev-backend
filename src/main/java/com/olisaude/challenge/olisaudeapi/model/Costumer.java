package com.olisaude.challenge.olisaudeapi.model;

import com.olisaude.challenge.olisaudeapi.dto.CostumerRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table

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
    private String birthDate;
    @Enumerated(EnumType.STRING)
    private CostumerGender gender;
    @ManyToOne
    private HealthProblem healthProblem;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean active;

    public Costumer(CostumerRequest request) {
        this.name = request.name();
        this.birthDate = request.birthDate();
        this.healthProblem = new HealthProblem();
        this.gender = request.gender();
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
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", active=" + active +
                '}';
    }
}
