CREATE TABLE IF NOT EXISTS cliente_problema_saude (
    id_cliente BIGINT NOT NULL,
    id_problema_saude BIGINT NOT NULL,
    PRIMARY KEY (id_cliente, id_problema_saude),
    FOREIGN KEY (id_cliente) REFERENCES clientes(id),
    FOREIGN KEY (id_problema_saude) REFERENCES problema_saude(id)
);