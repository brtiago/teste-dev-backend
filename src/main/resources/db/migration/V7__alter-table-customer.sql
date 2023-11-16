ALTER TABLE costumers
    RENAME TO customers;

ALTER TABLE customers
    ADD COLUMN sd INT;

ALTER TABLE customers
    ADD COLUMN health_score DOUBLE PRECISION;