-- 1. Utworzenie tabeli paper
CREATE TABLE paper (
    id BIGSERIAL PRIMARY KEY,
    type VARCHAR(100)     NOT NULL,    -- np. "Conference Paper", "Journal Article"
    isbn VARCHAR(20)      NOT NULL,
    topic VARCHAR(255)    NOT NULL,
    tutor_id BIGINT       NOT NULL REFERENCES person(id)
);

-- 2. Utworzenie tabeli do dodatkowych autorów (ElementCollection)
CREATE TABLE paper_additional_authors (
    paper_id BIGINT       NOT NULL REFERENCES paper(id),
    author_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (paper_id, author_name)
);
-- 3. Przykładowe dane: najpierw dodajemy dwóch tutorów do tabeli person
INSERT INTO person (first_name, last_name, role, identity_number)
VALUES
    ('Anna',   'Kowalska', 'TUTOR', 'TUT-000001'),
    ('Marek',  'Nowak',    'TUTOR', 'TUT-000002');


INSERT INTO paper (type, isbn, topic, tutor_id)
VALUES
    ('Journal Article','978-83-123456-7','Machine Learning in Education',          1),
    ('Conference Paper','978-83-765432-1','Scalable Microservices with Spring Boot', 2);

-- 5. Dodajemy dodatkowych autorów dla tych papierów
/* INSERT INTO paper_additional_authors (paper_id, author_name)
VALUES
    -- dla paper.id = 1 (Machine Learning in Education)
    (1, 'Piotr Zalewski'),
    (1, 'Ewa Malinowska'),
    -- dla paper.id = 2 (Scalable Microservices with Spring Boot)
    (2, 'Joanna Wiśniewska'); */
