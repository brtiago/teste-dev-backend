package com.olisaude.challenge.olisaudeapi.controller;

import com.olisaude.challenge.olisaudeapi.dto.ClienteRequest;
import com.olisaude.challenge.olisaudeapi.dto.ClienteResponse;
import com.olisaude.challenge.olisaudeapi.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "API para gerenciamento de clientes e cálculo de risco de saúde")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(
            summary = "Criar novo cliente",
            description = "Cria um novo cliente no sistema com seus dados pessoais e problemas de saúde. O sistema calcula automaticamente o SD (soma dos graus) e o score de risco."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "409", description = "Cliente já existe com este CPF")
    })
    @PostMapping
    public ResponseEntity<ClienteResponse> criar(@RequestBody @Valid ClienteRequest request) {
        ClienteResponse clienteResponse = clienteService.criar(request);
        return ResponseEntity.ok(clienteResponse);
    }

    @Operation(
            summary = "Buscar cliente por ID",
            description = "Retorna os dados completos de um cliente específico através do seu identificador único"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarId(
            @Parameter(description = "ID do cliente", required = true) @PathVariable Long id) {
        ClienteResponse clienteEncontrado = clienteService.buscarId(id);
        return ResponseEntity.ok(clienteEncontrado);
    }

    @Operation(
            summary = "Buscar cliente por CPF",
            description = "Retorna os dados completos de um cliente através do seu CPF"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/buscar/{cpf}")
    public ResponseEntity<ClienteResponse> buscarCpf(
            @Parameter(description = "CPF do cliente (11 dígitos)", required = true) @PathVariable String cpf) {
        ClienteResponse clienteEncontrado = clienteService.buscarCpf(cpf);
        return ResponseEntity.ok(clienteEncontrado);
    }

    @Operation(
            summary = "Listar todos os clientes",
            description = "Retorna uma lista com todos os clientes cadastrados no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<ClienteResponse>> buscarTodos() {
        List<ClienteResponse> listaClientes = clienteService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(listaClientes);
    }

    @Operation(
            summary = "Editar cliente",
            description = "Atualiza os dados de um cliente existente. Recalcula automaticamente o SD e score de risco."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> editar(
            @Parameter(description = "ID do cliente", required = true) @PathVariable Long id,
            @RequestBody @Valid ClienteRequest request) {
        ClienteResponse response = clienteService.editar(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Desativar cliente",
            description = "Desativa um cliente no sistema (soft delete). O cliente não será excluído permanentemente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente desativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(
            @Parameter(description = "ID do cliente", required = true) @PathVariable Long id) {
        clienteService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Listar clientes de maior risco",
            description = "Retorna os 10 clientes com maior score de risco de saúde. " +
                    "O score é calculado através da fórmula: score = (1 / (1 + e^(-2.8 + sd))) * 100, " +
                    "onde SD é a soma dos graus dos problemas de saúde do cliente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes de maior risco retornada com sucesso")
    })
    @GetMapping("/maior_risco")
    public ResponseEntity<List<ClienteResponse>> maiorRisco() {
        List<ClienteResponse> listaClientes = clienteService.buscarMaiorRisco();
        return ResponseEntity.status(HttpStatus.OK).body(listaClientes);
    }

}
