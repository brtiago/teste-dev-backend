package com.olisaude.challenge.olisaudeapi.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Olisaúde API",
                version = "0.1",
                description = """
                        API REST para gerenciamento de clientes e cálculo de risco de saúde.

                        ### Funcionalidades principais:
                        - Cadastro e gerenciamento de clientes
                        - Registro de problemas de saúde com níveis de gravidade
                        - Cálculo automático de score de risco (SD e Score)
                        - Ranking dos 10 clientes com maior risco

                        ### Cálculo do Score de Risco:
                        - **SD** = Soma dos graus dos problemas de saúde (GRAU_1=1, GRAU_2=2)
                        - **Score** = (1 / (1 + e^(-2.8 + SD))) × 100

                        O score varia de 0 a 100, onde valores mais altos indicam maior risco de saúde.
                        """,
                contact = @Contact(
                        name = "Tiago Ribeiro",
                        email = "tgribeiro@gmail.com"
                )
        ),
        servers = {
                @Server(
                        description = "Servidor Local",
                        url = "http://localhost:8080"
                )
        }
)
public class OlisaudeApiConfig {
}
