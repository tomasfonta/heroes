--liquibase formatted sql

--changeset tomas:1
--Database: h2
INSERT INTO heroes (id, name, slug) VALUES (1, 'A-Bomb', 'Bomba');
INSERT INTO heroes (id, name, slug) VALUES (2, 'Super Man', 'Super Man');
INSERT INTO heroes (id, name, slug) VALUES (3, 'Batman', 'Batman');
INSERT INTO heroes (id, name, slug) VALUES (4, 'Hombre Maravilla', 'Maravilla');
INSERT INTO heroes (id, name, slug) VALUES (5, 'Mujer Maravilla', 'Mujer Maravilla');
INSERT INTO heroes (id, name, slug) VALUES (6, 'Robin', 'Robin');
INSERT INTO heroes (id, name, slug) VALUES (7, 'Spider Man', 'Spider Man');
INSERT INTO heroes (id, name, slug) VALUES (8, 'Hulk', 'Increible Hulk');

