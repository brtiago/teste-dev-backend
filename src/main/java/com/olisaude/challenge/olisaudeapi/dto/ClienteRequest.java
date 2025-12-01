package com.olisaude.challenge.olisaudeapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.olisaude.challenge.olisaudeapi.model.GeneroCliente;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "Dados para criação ou atualização de um cliente")
public record ClienteRequest(
        @Schema(description = "Nome completo do cliente", example = "João da Silva")
        String nome,

        @Schema(description = "CPF do cliente (11 dígitos)", example = "12345678900")
        String cpf,

        @Schema(description = "Data de nascimento do cliente", example = "22-02-1993")
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate dataNascimento,

        @Schema(description = "Gênero do cliente", example = "MASCULINO")
        GeneroCliente genero,

        @Schema(description = "Lista de problemas de saúde do cliente")
        List<ProblemaSaudeRequest> problemaSaude
) {}
