create table condicao_saude(

    id bigint not null generated always as identity,
    nome VARCHAR(100) NOT NULL,
    grau VARCHAR(10) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,

    primary key(id)
);