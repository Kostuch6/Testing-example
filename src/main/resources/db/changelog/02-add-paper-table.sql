CREATE TABLE paper (
  id BIGSERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  type VARCHAR(50) NOT NULL,
  topic VARCHAR(255),
  isbn VARCHAR(20),
  additional_authors VARCHAR(500),
  publication_year INTEGER,
  tutor_id BIGINT REFERENCES person(id) NOT NULL
);
