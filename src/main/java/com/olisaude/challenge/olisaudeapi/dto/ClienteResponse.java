package com.olisaude.challenge.olisaudeapi.dto;

import com.olisaude.challenge.olisaudeapi.model.Cliente;
import com.olisaude.challenge.olisaudeapi.model.CondicaoSaude;
import com.olisaude.challenge.olisaudeapi.model.GeneroCliente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record ClienteResponse(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        GeneroCliente genero,
        List<CondicaoSaudeResponse> problemaSaude,
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
                entidade.getCondicaoSaude().stream()
                        .map(condicao -> new CondicaoSaudeResponse(
                                condicao.getNome(),
                                condicao.getGrau()
                        )).collect(Collectors.toList()),
                entidade.getDataCriacao(),
                entidade.isAtivo()
        );
    }
}