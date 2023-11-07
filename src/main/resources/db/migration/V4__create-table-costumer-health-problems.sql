CREATE TABLE costumer_health_problems (
  costumer_id BIGINT NOT NULL,
  health_problem_id BIGINT NOT NULL,
  PRIMARY KEY (costumer_id, health_problem_id),
  FOREIGN KEY (costumer_id) REFERENCES costumers (id),
  FOREIGN KEY (health_problem_id) REFERENCES health_problems (id)
);