
<h1 align="center">
  Backend Developer Challenge Olisaúde
</h1>

<p align="center">
 <img src="https://img.shields.io/static/v1?label=Linkedin&message=@tgribeiro&color=8257E5&labelColor=000000" alt="@tgribeiro" />
 <img src="https://img.shields.io/static/v1?label=Tipo&message=Desafio&color=8257E5&labelColor=000000" alt="Desafio" />
</p>

## 💻 Sobre o projeto
Esse é um projeto desenvolvido como base para a avaliação de habilidades técnicas para o papel de Desenvolvedor Backend Java da empresa Olisaúde. 
Para mais detalhes a respeito do desafio é recomendada a leitura do seu arquivo [README](INSTRUCTIONS.md).

## 🎯 Objetivos
Criar uma API simples para gerenciar Clientes.

### Funcionalidades desejadas:
- Criar um cliente
- Editar um cliente
- Obter um cliente específico
- Listar clientes

#### Um Cliente deve ter os seguintes campos:
- nome
- data de nascimento
- sexo
- [ problemas de saude ]
- data de criação
- data de atualização

##### Problemas de Saúde
- nome
- grau do problema (de 1 a 2)
    ```
    ex: diabetes, grau 2
    ```

#### Criar um endpoint para trazer os 10 clientes com maior risco de saúde, no qual o cálculo é:
  ```
    sd = soma do grau dos problemas
    score = (1 / (1 + eˆ-(-2.8 + sd ))) * 100
  ```

#### ⛔ O que NÃO será implementado

- Frontend (só implementaremos a [API Restful](https://www.devmedia.com.br/rest-tutorial/28912))
- Autenticação

## Práticas adotadas

- SOLID, Clean code
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro
- Geração automática do Swagger com a OpenAPI 3
- Testes automatizados
- Uso de DTOs para a API

## 🛠 Tecnologias utilizadas

As seguintes tecnologias foram utilizadas no desenvolvimento da API Rest do projeto:

- *[Java 17](https://www.oracle.com/java)*
- *[Spring Boot 3](https://spring.io/projects/spring-boot)*
- *[Maven](https://maven.apache.org)*
- *[PostgreSQL](https://www.postgresql.org/)*
- *[Hibernate](https://hibernate.org)*
- *[Flyway](https://flywaydb.org)*
- *[Lombok](https://projectlombok.org)*
- *[Insomnia](https://insomnia.rest/)*
- *[Docker](https://www.docker.com/)*
- *[SpringDoc OpenAPI 3](https://springdoc.org/v2/#spring-webflux-support)*
- *[Swagger UI](https://swagger.io/tools/swagger-ui/)*

## Como Executar

- Clonar repositório git
- Construir o projeto:
```
$ ./mvnw clean package
```
- Executar a aplicação:
```
$ java -jar target/todolist-0.0.1-SNAPSHOT.jar
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).
O Swagger poderá ser visualizado em [localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [curl](https://curl.se/):

- Editar um cliente
- Obter um cliente específico
- Listar clientes

- Criar um cliente
```
$ curl -X POST -H "Content-Type: application/json" -d '{
  "name": "João Anonimo",
  "birthDate": "1993-02-22",
  "gender": "MALE",
  "healthProblem": [
    {
      "name": "DEPRESSION"
    },
    {
      "name": "ASHMA"
    }
  ]
}' http://localhost:8080/customer

```
- Detalhar planeta pelo ID
```
$ curl http://localhost:8080/planets/1
[
  {
    "id":1,
    "name":"Tatooine",
    "climate":"arid",
    "terrain":"desert",
    "filmCount":"5"
  }
]
```

## 📝 Materiais úteis
- https://hub.packtpub.com/why-we-need-design-patterns/
- https://refactoring.guru/
- http://br.phptherightway.com/
- https://www.php-fig.org/psr/psr-12/
- https://www.atlassian.com/continuous-delivery/software-testing/types-of-software-testing
- https://github.com/exakat/php-static-analysis-tools
- https://martinfowler.com/articles/microservices.htm
- https://docs.guzzlephp.org/en/stable/request-options.html
- https://www.devmedia.com.br/rest-tutorial/28912