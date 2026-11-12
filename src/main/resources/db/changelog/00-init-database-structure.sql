CREATE TABLE person (
  id BIGSERIAL PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  role VARCHAR(50) NOT NULL,
  identity_number VARCHAR(10) NOT NULL
);

CREATE TABLE course (
  id BIGSERIAL PRIMARY KEY,
  course_name VARCHAR(255) NOT NULL,
  course_length INTEGER NOT NULL,
  tutor_id BIGINT REFERENCES person(id) NOT NULL
);

CREATE TABLE person_course (
  id BIGSERIAL PRIMARY KEY,
  person_id BIGINT REFERENCES person(id) NOT NULL,
  course_id BIGINT REFERENCES course(id) NOT NULL,
  status VARCHAR(20) NOT NULL,
  degree VARCHAR(2),
  join_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  finish_date TIMESTAMP WITHOUT TIME ZONE
);