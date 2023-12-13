package com.olisaude.challenge.olisaudeapi.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Olisaúde API",
                version = "0.1",
                description = "API para gerenciar clientes da Olisaúde",
                contact = @Contact(
                        name = "Tiago R.",
                        email = "tgribeiro@gmail.com"
                )
        )
)
public class OlisaudeApiConfig {
}
