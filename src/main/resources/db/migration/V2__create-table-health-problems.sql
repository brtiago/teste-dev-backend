create table health_problems(

    id bigint not null generated always as identity,
    name varchar(100) not null,
    degree varchar(10) not null,
    active boolean,

    primary key(id)
);