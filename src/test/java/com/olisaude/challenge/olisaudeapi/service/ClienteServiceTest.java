package com.olisaude.challenge.olisaudeapi.service;

import com.olisaude.challenge.olisaudeapi.dto.ClienteRequest;
import com.olisaude.challenge.olisaudeapi.dto.ClienteResponse;
import com.olisaude.challenge.olisaudeapi.model.Cliente;
import com.olisaude.challenge.olisaudeapi.repository.ClienteRepository;
import com.olisaude.challenge.olisaudeapi.service.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;
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
        ClienteRequest requestReal = TestDataFactory.criarClienteRequestComCpf(cpf);

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

    @Test
    void buscarId_QuandoClienteExiste_DeveRetornarClienteResponse() {
        // Given
        Long id = 1L;
        ClienteRequest requestReal = TestDataFactory.criarClienteRequestValido();
        Cliente cliente = new Cliente(requestReal);

        // Define o ID usando ReflectionTestUtils
        ReflectionTestUtils.setField(cliente, "id", id);

        // When
        when(repository.findById(id)).thenReturn(Optional.of(cliente));
        ClienteResponse response = service.buscarId(id);

        // Then
        assertNotNull(response);
        assertEquals(id, response.id());
        verify(repository).findById(id);
    }

    @Test
    void buscarId_QuandoClienteNaoExiste_DeveLancarExcecao() {
        // Given
        Long id = 99L;

        // When
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Then
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> service.buscarId(id)
        );

        assertEquals("Cliente não encontrado: " + id, exception.getMessage());
        verify(repository).findById(id);
    }



    @Test
    void buscarMaiorRisco_QuandoExistemClientes_DeveRetornarListaTop10OrdenadosPorScore() {
        // Given
        List<Cliente> clientes = Arrays.asList(
                criarClienteComScore(150),
                criarClienteComScore(102),
                criarClienteComScore(100)
        );

        when(repository.findTop10ByOrderByScoreDesc()).thenReturn(clientes);

        // When
        List<ClienteResponse> response = service.buscarMaiorRisco();

        // Then
        assertEquals(3, response.size());
        verify(repository).findTop10ByOrderByScoreDesc();
    }

    @Test
    void buscarMaiorRisco_QuandoNaoExistemClientes_DeveRetornarListaVazia() {
        // Given
        when(repository.findTop10ByOrderByScoreDesc()).thenReturn(List.of());

        // When
        List<ClienteResponse> response = service.buscarMaiorRisco();

        // Then
        assertTrue(response.isEmpty());
        verify(repository).findTop10ByOrderByScoreDesc();
    }

    private Cliente criarClienteComScore (int score) {
        Cliente cliente = new Cliente(TestDataFactory.criarClienteRequestValido());
        ReflectionTestUtils.setField(cliente, "score", score);
        return cliente;
    }

}