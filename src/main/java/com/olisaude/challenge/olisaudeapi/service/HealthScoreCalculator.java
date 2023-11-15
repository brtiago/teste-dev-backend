package com.olisaude.challenge.olisaudeapi.service;

import com.olisaude.challenge.olisaudeapi.model.HealthProblem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthScoreCalculator {
    public double getScore (int sd) {
        return (1 / (1 + Math.exp(-(-2.8 + sd)))) * 100;
    }

    public int getSd(List<HealthProblem> healthProblems) {
        int sd = healthProblems.stream()
                .mapToInt(healthProblem -> {
                    try {
                        return Integer.parseInt(healthProblem.getDegree());
                    } catch (NumberFormatException e) {
                        return 1;
                    }
                })
                .sum();

        return sd;
    }
}
