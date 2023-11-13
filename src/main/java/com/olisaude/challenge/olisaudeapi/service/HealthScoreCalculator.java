package com.olisaude.challenge.olisaudeapi.service;

import org.springframework.stereotype.Service;

@Service
public class HealthScoreCalculator {
    private double getScore (int sd) {
        return (1 / (1 + Math.exp(-(-2.8 + sd)))) * 100;
    }
}
