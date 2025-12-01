
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

## 🚀 Como Executar

### Pré-requisitos
- Java 17 ou superior
- Maven 3.x

### Passos para execução

1. **Clonar o repositório**
```bash
git clone <url-do-repositorio>
cd teste-dev-backend
```

2. **Construir o projeto**
```bash
./mvnw clean package
```

3. **Executar a aplicação**
```bash
./mvnw spring-boot:run
```

Ou usando o JAR gerado:
```bash
java -jar target/olisaude-api-0.0.1-SNAPSHOT.jar
```

4. **Acessar a aplicação**
- API Base URL: [http://localhost:8080](http://localhost:8080)
- **Swagger UI (Documentação Interativa)**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- H2 Console (banco de dados em memória): [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

### 🧪 Executar os testes
```bash
./mvnw test
```

**Cobertura de testes:**
- 20 testes unitários
- Testes de serviço (ClienteService)
- Testes de cálculo de risco (calcularSd e calcularScore)

## 📚 Documentação da API (Swagger)

A API está completamente documentada usando **OpenAPI 3.0** e pode ser acessada através do Swagger UI.

### Acesso ao Swagger
Após iniciar a aplicação, acesse: **http://localhost:8080/swagger-ui/index.html**

O Swagger UI permite:
- 📖 Visualizar todos os endpoints disponíveis
- 🧪 Testar os endpoints diretamente pelo navegador
- 📋 Ver exemplos de request e response
- 📝 Consultar a estrutura dos DTOs
- ⚡ Entender os códigos de status HTTP retornados

## 📋 API Endpoints

A API oferece os seguintes endpoints para gerenciamento de clientes:

### 1. Criar Cliente
```bash
POST /clientes
Content-Type: application/json

{
  "nome": "João da Silva",
  "cpf": "12345678900",
  "dataNascimento": "22-02-1993",
  "genero": "MASCULINO",
  "problemaSaude": [
    {
      "nome": "Diabetes",
      "grau": "GRAU_2"
    },
    {
      "nome": "Hipertensão",
      "grau": "GRAU_1"
    }
  ]
}
```

### 2. Listar Todos os Clientes
```bash
GET /clientes
```

### 3. Buscar Cliente por ID
```bash
GET /clientes/{id}
```

### 4. Buscar Cliente por CPF
```bash
GET /clientes/buscar/{cpf}
```

### 5. Editar Cliente
```bash
PUT /clientes/{id}
Content-Type: application/json

{
  "nome": "João da Silva Atualizado",
  "cpf": "12345678900",
  "dataNascimento": "22-02-1993",
  "genero": "MASCULINO",
  "problemaSaude": [
    {
      "nome": "Diabetes",
      "grau": "GRAU_2"
    }
  ]
}
```

### 6. Desativar Cliente
```bash
DELETE /clientes/{id}
```

### 7. Listar 10 Clientes de Maior Risco ⚠️
```bash
GET /clientes/maior_risco
```

Este endpoint retorna os 10 clientes com maior score de risco de saúde, ordenados do maior para o menor.

## 📊 Cálculo de Risco de Saúde

A API implementa um sistema de cálculo de risco baseado nos problemas de saúde dos clientes.

### Fórmula de Cálculo

**SD (Score de Doença)** = Soma dos graus dos problemas de saúde
- GRAU_1 = 1 ponto
- GRAU_2 = 2 pontos

**Score de Risco** = (1 / (1 + e^(-2.8 + SD))) × 100

### Exemplos:

| Problemas de Saúde | SD | Score (%) |
|--------------------|----|-----------|
| Nenhum | 0 | ~5.73% |
| 1x GRAU_1 | 1 | ~14.18% |
| 1x GRAU_2 | 2 | ~31.00% |
| 1x GRAU_2 + 1x GRAU_1 | 3 | ~54.98% |
| 3x GRAU_2 | 6 | ~96.08% |

O score varia de 0 a 100, onde valores mais altos indicam maior risco de saúde.

## 📬 Exemplos de uso com cURL

### Criar um cliente
```bash
curl -X POST http://localhost:8080/clientes \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Maria Santos",
    "cpf": "98765432100",
    "dataNascimento": "15-05-1985",
    "genero": "FEMININO",
    "problemaSaude": [
      {
        "nome": "Diabetes",
        "grau": "GRAU_2"
      }
    ]
  }'
```

### Listar clientes de maior risco
```bash
curl http://localhost:8080/clientes/maior_risco
```

### Buscar cliente por ID
```bash
curl http://localhost:8080/clientes/1
```

## 🔧 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/olisaude/challenge/olisaudeapi/
│   │   ├── config/           # Configurações (OpenAPI, etc)
│   │   ├── controller/       # Controllers REST
│   │   ├── dto/              # Data Transfer Objects
│   │   ├── model/            # Entidades JPA
│   │   ├── repository/       # Repositories Spring Data
│   │   ├── service/          # Camada de serviço
│   │   └── OlisaudeApiApplication.java
│   └── resources/
│       ├── application.properties
│       └── db/migration/     # Scripts Flyway
└── test/
    └── java/                 # Testes unitários
```

## ⚠️ Tratamento de Erros

A API implementa tratamento de erros personalizado com respostas HTTP apropriadas:

| Código HTTP | Situação |
|-------------|----------|
| 200 OK | Operação realizada com sucesso |
| 201 Created | Cliente criado com sucesso |
| 204 No Content | Cliente desativado com sucesso |
| 400 Bad Request | Dados inválidos na requisição |
| 404 Not Found | Cliente não encontrado |
| 409 Conflict | CPF já cadastrado no sistema |
| 500 Internal Server Error | Erro interno do servidor |

### Exemplos de resposta de erro:

**Cliente não encontrado (404):**
```json
{
  "message": "Cliente não encontrado: 999"
}
```

**CPF já cadastrado (409):**
```json
{
  "message": "Já existe um cliente cadastrado com este CPF."
}
```

## 🗄️ Banco de Dados

### Desenvolvimento
- **H2 Database** (em memória)
- Console H2: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: *(deixe em branco)*

### Migrations
O projeto utiliza **Flyway** para versionamento do banco de dados. As migrations estão em:
```
src/main/resources/db/migration/
```

Scripts aplicados:
- V1: Criação da tabela `clientes`
- V2: Criação da tabela `problema_saude`
- V3: Criação da tabela de relacionamento `cliente_problema_saude`
- V4: Alterações na tabela `clientes` (campos SD e Score)

## 🎯 Próximas Melhorias

- [ ] Implementar paginação nos endpoints de listagem
- [ ] Adicionar filtros avançados de busca
- [ ] Implementar autenticação e autorização (JWT)
- [ ] Adicionar logs estruturados
- [ ] Implementar cache para consultas frequentes
- [ ] Adicionar métricas e monitoramento (Actuator)
- [ ] Configurar perfil de produção com PostgreSQL
- [ ] Aumentar cobertura de testes (testes de integração)

## 📝 Referências e Materiais Úteis

### Documentação Oficial
- [Spring Boot 3](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI](https://springdoc.org/)
- [Flyway Migrations](https://flywaydb.org/documentation/)

### Boas Práticas
- [REST API Tutorial](https://www.devmedia.com.br/rest-tutorial/28912)
- [SOLID Principles](https://refactoring.guru/design-patterns/java)
- [Clean Code](https://refactoring.guru/)
- [Testing Best Practices](https://www.atlassian.com/continuous-delivery/software-testing/types-of-software-testing)

## 👨‍💻 Autor

**Tiago Ribeiro**
- LinkedIn: [@tgribeiro](https://linkedin.com/in/tgribeiro)
- Email: tgribeiro@gmail.com

---

⭐ Desenvolvido como parte do desafio técnico para **Olisaúde**