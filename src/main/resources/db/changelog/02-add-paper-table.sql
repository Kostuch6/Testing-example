CREATE TABLE paper (
  id BIGSERIAL PRIMARY KEY,
  type VARCHAR(50) NOT NULL,
  isbn VARCHAR(20),
  topic VARCHAR(255) NOT NULL,
  additional_authors VARCHAR(500),
  tutor_id BIGINT REFERENCES person(id) NOT NULL
);
