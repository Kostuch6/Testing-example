CREATE TABLE paper (
  id BIGSERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  type VARCHAR(50) NOT NULL,
  topic VARCHAR(255) NOT NULL,
  isbn VARCHAR(20) NOT NULL,
  additional_authors VARCHAR(500) NOT NULL,
  publication_year INTEGER NOT NULL,
  tutor_id BIGINT REFERENCES person(id) NOT NULL
);
