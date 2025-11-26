package com.olisaude.challenge.olisaudeapi.service;

import com.olisaude.challenge.olisaudeapi.dto.ClienteRequest;
import com.olisaude.challenge.olisaudeapi.dto.ClienteResponse;
import com.olisaude.challenge.olisaudeapi.model.Cliente;
import com.olisaude.challenge.olisaudeapi.repository.ClienteRepository;
import com.olisaude.challenge.olisaudeapi.service.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository repository;
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ClienteResponse criar(@Valid ClienteRequest request) {
        Cliente entidade = new Cliente(request);
        Cliente salvo = repository.save(entidade);
        return ClienteResponse.fromEntity(salvo);
    }

    public ClienteResponse buscarId(Long id) {
        Cliente clienteExistente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + id));
        return ClienteResponse.fromEntity(clienteExistente);
    }

    public ClienteResponse buscarCpf(String cpf) {
        Cliente clienteExistente = repository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + cpf));
        return ClienteResponse.fromEntity(clienteExistente);
    }

    public List<ClienteResponse> buscarTodos() {
        return repository.findAll()
                .stream()
                .map(ClienteResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public ClienteResponse editar(Long id, @Valid ClienteRequest request) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + id));

        cliente.setNome(request.nome());
        cliente.setCpf(request.cpf());
        cliente.setDataNascimento(request.dataNascimento());
        cliente.setGenero(request.genero());
        cliente.setCondicaoSaude(request.condicaoSaude());
        cliente.setDataAtualizacao(LocalDateTime.now());
        cliente.atualizarData();

        Cliente atualizado = repository.save(cliente);
        return ClienteResponse.fromEntity(atualizado);
    }

    @Transactional
        public void remover (Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + id));

        cliente.setAtivo(false);
        Cliente atualizado = repository.save(cliente);
    }

    public List<ClienteResponse> buscarMaiorRisco() {
        return repository.findTop10ByOrderByScoreDesc()
                .stream()
                .map(ClienteResponse::fromEntity)
                .collect(Collectors.toList());
    }

}
