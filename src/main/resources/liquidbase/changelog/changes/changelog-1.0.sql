--liquibase formatted sql

--changeset tomas:1
--Database: h2
CREATE TABLE heroes
(
    id   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    slug VARCHAR(500) NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users
(
    id      INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name    VARCHAR(45) NOT NULL,
    password VARCHAR(500) NULL,
    role    VARCHAR(500) NULL,
    PRIMARY KEY (id)
);
