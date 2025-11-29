package com.olisaude.challenge.olisaudeapi.service;

import com.olisaude.challenge.olisaudeapi.dto.ClienteRequest;
import com.olisaude.challenge.olisaudeapi.dto.ClienteResponse;
import com.olisaude.challenge.olisaudeapi.dto.ProblemaSaudeRequest;
import com.olisaude.challenge.olisaudeapi.model.Cliente;
import com.olisaude.challenge.olisaudeapi.model.ProblemaSaude;
import com.olisaude.challenge.olisaudeapi.repository.ClienteRepository;
import com.olisaude.challenge.olisaudeapi.service.exception.ResourceAlreadyExists;
import com.olisaude.challenge.olisaudeapi.service.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
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

    private boolean clienteExiste(String cpf) {
        return repository.existsByCpf(cpf);
    }

    @Transactional
    public ClienteResponse criar(@Valid ClienteRequest request) {
        if(clienteExiste(request.cpf())) {
            throw new ResourceAlreadyExists("Já existe um cliente cadastrado com este CPF.");
        }
        Cliente entidade = new Cliente(request);
        try {
            Cliente salvo = repository.save(entidade);
            return ClienteResponse.fromEntity(salvo);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceAlreadyExists("Cliente já existe com este CPF." + request.cpf());
        }
    }

    public ClienteResponse buscarId(Long id) {
        return repository.findById(id)
                .map(ClienteResponse::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + id));
    }

    public ClienteResponse buscarCpf(String cpf) {
        return repository.findByCpf(cpf)
                .map(ClienteResponse::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + cpf));
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

        List<ProblemaSaude> problemasSaude = request.problemaSaude().stream()
                .map(ProblemaSaude::new)
                .collect(Collectors.toList());

        cliente.setProblemaSaude(problemasSaude);
        cliente.setDataAtualizacao(LocalDateTime.now());
        cliente.atualizarData();

        Cliente atualizado = repository.save(cliente);
        return ClienteResponse.fromEntity(atualizado);
    }

    @Transactional
    public void desativar(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + id));
        cliente.desativar();
    }

    public List<ClienteResponse> buscarMaiorRisco() {
        return repository.findTop10ByOrderByScoreDesc()
                .stream()
                .map(ClienteResponse::fromEntity)
                .collect(Collectors.toList());
    }

}
