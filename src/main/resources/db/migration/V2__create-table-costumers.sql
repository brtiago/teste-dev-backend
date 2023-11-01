create table costumers(
    id bigint not null generated always as identity,
    name varchar(255),
    birth_date DATE,
    gender varchar(100),
    health_problem_id bigint not null,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp,
    active boolean,


    primary key(id),
    CONSTRAINT fk_health_problem_id
        FOREIGN KEY (health_problem_id)
            REFERENCES health_problems(id)
);