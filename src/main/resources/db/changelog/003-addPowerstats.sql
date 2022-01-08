--liquibase formatted sql

--changeset tomas:1
--Database: h2
CREATE TABLE power_stats
(
    id      INT UNSIGNED NOT NULL AUTO_INCREMENT,
    level   INT,
    name    VARCHAR(45) NOT NULL,
    hero_id INT         NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_powerstats_hero
    FOREIGN KEY (hero_id) REFERENCES heroes(id)
);

--changeset tomas:2
--Database: h2

INSERT INTO power_stats (id, level, name, hero_id)
VALUES (1, 100, 'Intelligence', 1),
       (2, 60, 'Intelligence', 2),
       (3, 02, 'Intelligence', 3),
       (4, 52, 'Intelligence', 4),
       (5, 63, 'Intelligence', 5),
       (6, 55, 'Intelligence', 6),
       (7, 66, 'Intelligence', 7),
       (8, 100, 'Intelligence', 8),
       (9, 23, 'Intelligence', 9),
       (10, 44, 'Intelligence', 10),
       (11, 100, 'Intelligence', 11),
       (12, 26, 'Intelligence', 12),
       (13, 50, 'Intelligence', 13),
       (14, 40, 'Intelligence', 14),
       (15, 59, 'Intelligence', 15),
       (16, 33, 'Intelligence', 16),
       (17, 98, 'Strength', 1),
       (18, 100, 'Strength', 2),
       (19, 02, 'Strength', 3),
       (20, 12, 'Strength', 4),
       (21, 63, 'Strength', 5),
       (22, 55, 'Strength', 6),
       (23, 66, 'Strength', 7),
       (24, 100, 'Strength', 8),
       (25, 23, 'Strength', 9),
       (26, 44, 'Strength', 10),
       (27, 100, 'Strength', 11),
       (28, 26, 'Strength', 12),
       (29, 70, 'Strength', 13),
       (30, 40, 'Strength', 14),
       (31, 49, 'Strength', 15),
       (32, 33, 'Strength', 16),
       (33, 100, 'Speed', 1),
       (34, 1, 'Speed', 2),
       (35, 02, 'Speed', 3),
       (36, 12, 'Speed', 4),
       (37, 88, 'Speed', 5),
       (38, 55, 'Speed', 6),
       (39, 66, 'Speed', 7),
       (40, 100, 'Speed', 8),
       (41, 23, 'Speed', 9),
       (42, 44, 'Speed', 10),
       (43, 66, 'Speed', 11),
       (44, 26, 'Speed', 12),
       (45, 50, 'Speed', 13),
       (46, 77, 'Speed', 14),
       (47, 26, 'Speed', 15),
       (48, 33, 'Speed', 16);
