CREATE TABLE clientes(
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    nome varchar(255) NOT NULL,
    data_nascimento DATE NOT NULL,
    genero varchar(10)NOT NULL,
    data_criacao timestamp DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao timestamp,
    ativo boolean DEFAULT TRUE,
    sd INTEGER DEFAULT 0,
    score DECIMAL(5,2) DEFAULT 0.0,

    PRIMARY KEY(id)
);