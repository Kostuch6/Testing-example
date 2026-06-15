CREATE SEQUENCE contact_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE contact (
  id BIGINT PRIMARY KEY DEFAULT nextval('contact_id_seq'),
  type VARCHAR(50) NOT NULL,
  value VARCHAR(500) NOT NULL,
  label VARCHAR(255),
  person_id BIGINT REFERENCES person(id) NOT NULL
);
