package com.olisaude.challenge.olisaudeapi.dto;

import com.olisaude.challenge.olisaudeapi.model.Cliente;
import com.olisaude.challenge.olisaudeapi.model.GeneroCliente;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Schema(description = "Dados de resposta de um cliente")
public record ClienteResponse(
        @Schema(description = "ID único do cliente", example = "1")
        Long id,

        @Schema(description = "Nome completo do cliente", example = "João da Silva")
        String nome,

        @Schema(description = "CPF do cliente", example = "12345678900")
        String cpf,

        @Schema(description = "Data de nascimento do cliente", example = "1993-02-22")
        LocalDate dataNascimento,

        @Schema(description = "Gênero do cliente", example = "MASCULINO")
        GeneroCliente genero,

        @Schema(description = "Lista de problemas de saúde do cliente")
        List<ProblemaSaudeResponse> problemaSaude,

        @Schema(description = "Data de criação do registro", example = "2024-01-15T10:30:00")
        LocalDateTime dataCriacao,

        @Schema(description = "Indica se o cliente está ativo no sistema", example = "true")
        boolean ativo
) {
    public static ClienteResponse fromEntity(Cliente entidade){
        return new ClienteResponse(
                entidade.getId(),
                entidade.getNome(),
                entidade.getCpf(),
                entidade.getDataNascimento(),
                entidade.getGenero(),
                Optional.ofNullable(entidade.getProblemaSaude())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(problemaSaude -> new ProblemaSaudeResponse(
                                problemaSaude.getNome(),
                                problemaSaude.getGrau()
                        )).collect(Collectors.toList()),

                entidade.getDataCriacao(),
                entidade.isAtivo()
        );
    }
}