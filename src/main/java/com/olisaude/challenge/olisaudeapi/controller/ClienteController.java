package com.olisaude.challenge.olisaudeapi.controller;

import com.olisaude.challenge.olisaudeapi.dto.ClienteRequest;
import com.olisaude.challenge.olisaudeapi.dto.ClienteResponse;
import com.olisaude.challenge.olisaudeapi.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> criar(@RequestBody @Valid ClienteRequest request) {
        ClienteResponse clienteResponse = clienteService.criar(request);
        return ResponseEntity.ok(clienteResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarId(@PathVariable Long id) {
        ClienteResponse clienteEncontrado = clienteService.buscarId(id);
        return ResponseEntity.ok(clienteEncontrado);
    }

    @GetMapping("/buscar/{cpf}")
    public ResponseEntity<ClienteResponse> buscarCpf(@PathVariable String cpf) {
        ClienteResponse clienteEncontrado = clienteService.buscarCpf(cpf);
        return ResponseEntity.ok(clienteEncontrado);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> buscarTodos() {
        List<ClienteResponse> listaClientes = clienteService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(listaClientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> editar(@PathVariable Long id,
                                                  @RequestBody @Valid ClienteRequest request) {
        ClienteResponse response = clienteService.editar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity desativar(@PathVariable Long id) {
        clienteService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/maior_risco")
    public ResponseEntity<List<ClienteResponse>> maiorRisco() {
        List<ClienteResponse> listaClientes = clienteService.buscarMaiorRisco();
        return ResponseEntity.status(HttpStatus.OK).body(listaClientes);
    }

}
