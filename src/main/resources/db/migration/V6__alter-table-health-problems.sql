ALTER TABLE health_problems
    ADD CONSTRAINT name_degree UNIQUE (name, degree);