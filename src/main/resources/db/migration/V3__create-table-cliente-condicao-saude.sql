CREATE TABLE IF NOT EXISTS cliente_condicao_saude (
    id_cliente BIGINT NOT NULL,
    id_condicao_saude BIGINT NOT NULL,
    PRIMARY KEY (id_cliente, id_condicao_saude),
    FOREIGN KEY (id_cliente) REFERENCES clientes(id),
    FOREIGN KEY (id_condicao_saude) REFERENCES condicao_saude(id)
);