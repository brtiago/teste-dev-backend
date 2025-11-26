package com.olisaude.challenge.olisaudeapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.olisaude.challenge.olisaudeapi.model.CondicaoSaude;
import com.olisaude.challenge.olisaudeapi.model.GeneroCliente;

import java.time.LocalDate;
import java.util.List;

public record ClienteRequest(
        String nome,
        String cpf,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate dataNascimento,
        GeneroCliente genero,
        List<CondicaoSaude> condicaoSaude
) {}
