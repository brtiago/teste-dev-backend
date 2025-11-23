package com.olisaude.challenge.olisaudeapi.model;

public enum GrauProblemaSaude {
        GRAU_1(1),
        GRAU_2(2);

        private final int value;

    GrauProblemaSaude(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
