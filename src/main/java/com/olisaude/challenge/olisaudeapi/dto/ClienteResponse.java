package com.olisaude.challenge.olisaudeapi.dto;

import com.olisaude.challenge.olisaudeapi.model.Cliente;
import com.olisaude.challenge.olisaudeapi.model.CondicaoSaude;
import com.olisaude.challenge.olisaudeapi.model.GeneroCliente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ClienteResponse(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        GeneroCliente genero,
        List<CondicaoSaude> problemaSaude,
        LocalDateTime dataCriacao,
        boolean ativo
) {
    public static ClienteResponse fromEntity(Cliente entidade){
        return new ClienteResponse(
                entidade.getId(),
                entidade.getNome(),
                entidade.getCpf(),
                entidade.getDataNascimento(),
                entidade.getGenero(),
                entidade.getCondicaoSaude(),
                entidade.getDataCriacao(),
                entidade.isAtivo()
        );
    }
}