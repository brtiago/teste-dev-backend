ALTER TABLE customers
    DROP CONSTRAINT fk_health_problem_id;


ALTER TABLE customers
    DROP COLUMN health_problem_id;