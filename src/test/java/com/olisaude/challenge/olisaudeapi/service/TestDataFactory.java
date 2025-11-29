package com.olisaude.challenge.olisaudeapi.service;

import com.olisaude.challenge.olisaudeapi.dto.ClienteRequest;
import com.olisaude.challenge.olisaudeapi.dto.ProblemaSaudeRequest;
import com.olisaude.challenge.olisaudeapi.model.GeneroCliente;
import com.olisaude.challenge.olisaudeapi.model.GrauProblemaSaude;

import java.time.LocalDate;
import java.util.List;

public class TestDataFactory {

    public static ClienteRequest criarClienteRequestComCpf(String cpf) {
        return new ClienteRequest(
                "João da Silva",
                cpf,
                LocalDate.of(1995, 1, 1),
                GeneroCliente.MASCULINO,
                List.of(
                        new ProblemaSaudeRequest("Diabetes", GrauProblemaSaude.GRAU_2),
                        new ProblemaSaudeRequest("Problema Renal", GrauProblemaSaude.GRAU_1)
                ));
    }

    public static ClienteRequest criarClienteRequestValido() {
        return new ClienteRequest(
                "João da Silva",
                "12345678901",
                LocalDate.of(1995, 1, 1),
                GeneroCliente.MASCULINO,
                List.of(
                        new ProblemaSaudeRequest("Diabetes", GrauProblemaSaude.GRAU_2),
                        new ProblemaSaudeRequest("Cardiopatia", GrauProblemaSaude.GRAU_1)
                )
        );
    }

}
