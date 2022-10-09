DROP TABLE IF EXISTS developer;
CREATE TABLE IF NOT EXISTS developer
(
    id varchar(36) not null primary key,
    name varchar(255) not null,
    created timestamp,
    modified timestamp,
    active boolean
    );

INSERT INTO developer (id, name, created, modified, active) VALUES
('102883e67df8e12a017df8e4c4440001', 'Anton', '2021-12-10 00:00:000', '2021-12-10 00:01:000', false),
('202883e67df8e12a017df8e4c4440002', 'Maksim', '2021-12-10 01:00:000', '2021-12-10 01:02:000', false),
('302883e67df8e12a017df8e4c4440003', 'Vladimir', '2021-12-10 02:00:000', '2021-12-10 02:03:000', false);

DROP TABLE IF EXISTS to_do;
CREATE TABLE IF NOT EXISTS to_do
(
    id varchar(36) not null primary key,
    description varchar(255) not null,
    created timestamp,
    modified timestamp,
    completed boolean
);

INSERT INTO to_do (id, description, created, modified, completed) VALUES
('102883e67df8e12a017df8e4c4440004', 'unit-test', '2021-12-10 00:00:000', '2021-12-10 00:01:000', false),
('202883e67df8e12a017df8e4c4440005', 'improve logs', '2021-12-10 01:00:000', '2021-12-10 01:02:000', false),
('302883e67df8e12a017df8e4c4440006', 'tech debt', '2021-12-10 02:00:000', '2021-12-10 02:03:000', false);

DROP TABLE IF EXISTS "user";
CREATE TABLE IF NOT EXISTS "user"
(
    id varchar(36) not null primary key,
    first_name varchar(50) not null,
    last_name varchar(50),
    login varchar(50) not null,
    password varchar(100) not null,
    is_active boolean default true,
    role varchar(50),
    date_login timestamp
);

INSERT INTO "user" (id, first_name, last_name, login, password, is_active, role, date_login) VALUES
('602883e67df8e12a017df8e4c4440004', 'admin', null, 'admin', '$2a$12$dOSNBUa41yYyUy1KZSpR2OoverxsULtlSfltZTSZFy4rVbZ04IeMa', true, 'ADMIN', '2022-01-29 00:01:000');