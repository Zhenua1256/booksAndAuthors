CREATE SEQUENCE IF NOT EXISTS books_seq;

CREATE SEQUENCE IF NOT EXISTS authors_seq;

CREATE SEQUENCE IF NOT EXISTS users_seq;

CREATE TABLE IF NOT EXISTS AUTHORS
(
    id      INT             NOT NULL    PRIMARY KEY,
    name    VARCHAR(100)    NOT NULL
);

CREATE TABLE IF NOT EXISTS BOOKS
(
    id          INT             NOT NULL    PRIMARY KEY,
    name        VARCHAR(100)    NOT NULL,
    author_id   INT             NOT NULL,

    FOREIGN KEY (author_id) REFERENCES AUTHORS(id)
);

CREATE TABLE IF NOT EXISTS USERS
(
    id          INT             NOT NULL    PRIMARY KEY,
    username    VARCHAR(100)    NOT NULL,
    password    VARCHAR(100)    NOT NULL,
    role        VARCHAR(20)     NOT NULL
);

INSERT INTO USERS (id, username, password, role)
VALUES(nextval('users_seq'), 'admin', '$2a$10$cOpIH0jw6uJtb7f0G7jO4e6HBNoQPsxfUbokWV7i11Co2uLIHOwg.', 'ADMIN')