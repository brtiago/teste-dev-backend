package com.olisaude.challenge.olisaudeapi.service;

import com.olisaude.challenge.olisaudeapi.dto.ClienteRequest;
import com.olisaude.challenge.olisaudeapi.dto.ClienteResponse;
import com.olisaude.challenge.olisaudeapi.dto.ProblemaSaudeRequest;
import com.olisaude.challenge.olisaudeapi.model.Cliente;
import com.olisaude.challenge.olisaudeapi.model.GeneroCliente;
import com.olisaude.challenge.olisaudeapi.model.GrauProblemaSaude;
import com.olisaude.challenge.olisaudeapi.repository.ClienteRepository;
import com.olisaude.challenge.olisaudeapi.service.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    @Test
    void buscarCpf_QuandoClienteExiste_DeveRetornarClienteResponse() {
        // Arrange
        String cpf = "12345678900";
        ClienteRequest requestReal = new ClienteRequest(
                "João da Silva",
                cpf,
                LocalDate.of(1995, 1, 1),
                GeneroCliente.MASCULINO,
                List.of(
                        new ProblemaSaudeRequest("Diabetes", GrauProblemaSaude.GRAU_2),
                        new ProblemaSaudeRequest("Problema Renal", GrauProblemaSaude.GRAU_1)
                )
        );

        Cliente cliente = new Cliente(requestReal);
        when(repository.findByCpf(cpf)).thenReturn(Optional.of(cliente));

        // Act
        ClienteResponse response = service.buscarCpf(cpf);

        // Assert
        assertNotNull(response);;
        assertEquals(cpf, response.cpf());
        verify(repository).findByCpf(cpf);
    }

    @Test
    void buscarCpf_QuandoClienteNaoExiste_DeveLancarExcecao() {
        // Given
        String cpf = "99999999999";

        // When
        when(repository.findByCpf(cpf)).thenReturn(Optional.empty());

        // Then
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> service.buscarCpf(cpf)
        );

        assertEquals("Cliente não encontrado: " + cpf, exception.getMessage());
        verify(repository).findByCpf(cpf);
    }

}