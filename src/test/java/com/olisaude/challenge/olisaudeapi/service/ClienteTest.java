package com.olisaude.challenge.olisaudeapi.service;

import com.olisaude.challenge.olisaudeapi.dto.ClienteRequest;
import com.olisaude.challenge.olisaudeapi.dto.ProblemaSaudeRequest;
import com.olisaude.challenge.olisaudeapi.model.Cliente;
import com.olisaude.challenge.olisaudeapi.model.GeneroCliente;
import com.olisaude.challenge.olisaudeapi.model.GrauProblemaSaude;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void calcularSd_QuandoClienteSemProblemasDeSaude_DeveRetornarZero() {
        // Given
        ClienteRequest request = new ClienteRequest(
                "João da Silva",
                "12345678901",
                LocalDate.of(1995, 1, 1),
                GeneroCliente.MASCULINO,
                new ArrayList<>()
        );
        Cliente cliente = new Cliente(request);

        // When
        int sd = (int) ReflectionTestUtils.invokeMethod(cliente, "calcularSd");

        // Then
        assertEquals(0, sd);
    }

    @Test
    void calcularSd_QuandoClienteComListaNull_DeveRetornarZero() {
        // Given
        ClienteRequest request = new ClienteRequest(
                "João da Silva",
                "12345678901",
                LocalDate.of(1995, 1, 1),
                GeneroCliente.MASCULINO,
                null
        );
        Cliente cliente = new Cliente(request);

        // When
        int sd = (int) ReflectionTestUtils.invokeMethod(cliente, "calcularSd");

        // Then
        assertEquals(0, sd);
    }

    @Test
    void calcularSd_QuandoClienteComUmProblemaGrau1_DeveRetornar1() {
        // Given
        ClienteRequest request = new ClienteRequest(
                "João da Silva",
                "12345678901",
                LocalDate.of(1995, 1, 1),
                GeneroCliente.MASCULINO,
                List.of(new ProblemaSaudeRequest("Diabetes", GrauProblemaSaude.GRAU_1))
        );
        Cliente cliente = new Cliente(request);

        // When
        int sd = (int) ReflectionTestUtils.invokeMethod(cliente, "calcularSd");

        // Then
        assertEquals(1, sd);
    }

    @Test
    void calcularSd_QuandoClienteComUmProblemaGrau2_DeveRetornar2() {
        // Given
        ClienteRequest request = new ClienteRequest(
                "João da Silva",
                "12345678901",
                LocalDate.of(1995, 1, 1),
                GeneroCliente.MASCULINO,
                List.of(new ProblemaSaudeRequest("Cardiopatia", GrauProblemaSaude.GRAU_2))
        );
        Cliente cliente = new Cliente(request);

        // When
        int sd = (int) ReflectionTestUtils.invokeMethod(cliente, "calcularSd");

        // Then
        assertEquals(2, sd);
    }

    @Test
    void calcularSd_QuandoClienteComMultiplosProblemas_DeveSomarTodosOsGraus() {
        // Given
        ClienteRequest request = new ClienteRequest(
                "João da Silva",
                "12345678901",
                LocalDate.of(1995, 1, 1),
                GeneroCliente.MASCULINO,
                List.of(
                        new ProblemaSaudeRequest("Diabetes", GrauProblemaSaude.GRAU_2),
                        new ProblemaSaudeRequest("Cardiopatia", GrauProblemaSaude.GRAU_1),
                        new ProblemaSaudeRequest("Hipertensão", GrauProblemaSaude.GRAU_2)
                )
        );
        Cliente cliente = new Cliente(request);

        // When
        int sd = (int) ReflectionTestUtils.invokeMethod(cliente, "calcularSd");

        // Then
        assertEquals(5, sd); // 2 + 1 + 2 = 5
    }

    @Test
    void calcularScore_QuandoSdZero_DeveRetornarScoreBaixo() {
        // Given - Cliente sem problemas de saúde
        ClienteRequest request = new ClienteRequest(
                "João da Silva",
                "12345678901",
                LocalDate.of(1995, 1, 1),
                GeneroCliente.MASCULINO,
                new ArrayList<>()
        );
        Cliente cliente = new Cliente(request);

        // When
        double score = cliente.calcularScore();

        // Then
        // score = (1 / (1 + e^(2.8))) * 100 ≈ 5.73%
        assertEquals(5.73, score, 0.01);
    }

    @Test
    void calcularScore_QuandoSd1_DeveRetornarScoreEsperado() {
        // Given
        ClienteRequest request = new ClienteRequest(
                "João da Silva",
                "12345678901",
                LocalDate.of(1995, 1, 1),
                GeneroCliente.MASCULINO,
                List.of(new ProblemaSaudeRequest("Diabetes", GrauProblemaSaude.GRAU_1))
        );
        Cliente cliente = new Cliente(request);

        // When
        double score = cliente.calcularScore();

        // Then
        // score = (1 / (1 + e^(2.8-1))) * 100 = (1 / (1 + e^1.8)) * 100 ≈ 14.18%
        assertEquals(14.18, score, 0.01);
    }

    @Test
    void calcularScore_QuandoSd2_DeveRetornarScoreEsperado() {
        // Given
        ClienteRequest request = new ClienteRequest(
                "João da Silva",
                "12345678901",
                LocalDate.of(1995, 1, 1),
                GeneroCliente.MASCULINO,
                List.of(new ProblemaSaudeRequest("Cardiopatia", GrauProblemaSaude.GRAU_2))
        );
        Cliente cliente = new Cliente(request);

        // When
        double score = cliente.calcularScore();

        // Then
        // score = (1 / (1 + e^(2.8-2))) * 100 = (1 / (1 + e^0.8)) * 100 ≈ 31.00%
        assertEquals(31.00, score, 0.01);
    }

    @Test
    void calcularScore_QuandoSd3_DeveRetornarScoreModerado() {
        // Given
        ClienteRequest request = new ClienteRequest(
                "João da Silva",
                "12345678901",
                LocalDate.of(1995, 1, 1),
                GeneroCliente.MASCULINO,
                List.of(
                        new ProblemaSaudeRequest("Diabetes", GrauProblemaSaude.GRAU_2),
                        new ProblemaSaudeRequest("Cardiopatia", GrauProblemaSaude.GRAU_1)
                )
        );
        Cliente cliente = new Cliente(request);

        // When
        double score = cliente.calcularScore();

        // Then
        // score = (1 / (1 + e^(2.8-3))) * 100 = (1 / (1 + e^-0.2)) * 100 ≈ 54.98%
        assertEquals(54.98, score, 0.01);
    }

    @Test
    void calcularScore_QuandoSdAlto_DeveRetornarScoreAlto() {
        // Given
        ClienteRequest request = new ClienteRequest(
                "João da Silva",
                "12345678901",
                LocalDate.of(1995, 1, 1),
                GeneroCliente.MASCULINO,
                List.of(
                        new ProblemaSaudeRequest("Diabetes", GrauProblemaSaude.GRAU_2),
                        new ProblemaSaudeRequest("Cardiopatia", GrauProblemaSaude.GRAU_2),
                        new ProblemaSaudeRequest("Hipertensão", GrauProblemaSaude.GRAU_2)
                )
        );
        Cliente cliente = new Cliente(request);

        // When
        double score = cliente.calcularScore();

        // Then
        // SD = 6, score = (1 / (1 + e^(2.8-6))) * 100 = (1 / (1 + e^-3.2)) * 100 ≈ 96.08%
        assertEquals(96.08, score, 0.01);
    }

    @Test
    void getSd_DeveRetornarSdCalculado() {
        // Given
        ClienteRequest request = new ClienteRequest(
                "João da Silva",
                "12345678901",
                LocalDate.of(1995, 1, 1),
                GeneroCliente.MASCULINO,
                List.of(
                        new ProblemaSaudeRequest("Diabetes", GrauProblemaSaude.GRAU_2),
                        new ProblemaSaudeRequest("Cardiopatia", GrauProblemaSaude.GRAU_1)
                )
        );
        Cliente cliente = new Cliente(request);

        // When
        int sd = cliente.getSd();

        // Then
        assertEquals(3, sd); // 2 + 1 = 3
    }

    @Test
    void getScore_DeveRetornarScoreCalculado() {
        // Given
        ClienteRequest request = new ClienteRequest(
                "João da Silva",
                "12345678901",
                LocalDate.of(1995, 1, 1),
                GeneroCliente.MASCULINO,
                List.of(
                        new ProblemaSaudeRequest("Diabetes", GrauProblemaSaude.GRAU_2),
                        new ProblemaSaudeRequest("Cardiopatia", GrauProblemaSaude.GRAU_1)
                )
        );
        Cliente cliente = new Cliente(request);

        // When
        double score = cliente.getScore();

        // Then
        // SD = 3, score ≈ 54.98%
        assertEquals(54.98, score, 0.01);
    }

    @Test
    void setProblemaSaude_DeveRecalcularSdEScore() {
        // Given
        ClienteRequest request = new ClienteRequest(
                "João da Silva",
                "12345678901",
                LocalDate.of(1995, 1, 1),
                GeneroCliente.MASCULINO,
                new ArrayList<>()
        );
        Cliente cliente = new Cliente(request);
        assertEquals(0, cliente.getSd());

        // When
        List<ProblemaSaudeRequest> novosProblemas = List.of(
                new ProblemaSaudeRequest("Diabetes", GrauProblemaSaude.GRAU_2)
        );
        cliente.setProblemaSaudeFromRequest(novosProblemas);

        // Then
        assertEquals(2, cliente.getSd());
        assertEquals(31.00, cliente.getScore(), 0.01);
    }
}
