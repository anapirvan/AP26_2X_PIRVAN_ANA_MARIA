CREATE TABLE genres (
id   SERIAL        PRIMARY KEY,
name VARCHAR(100)  NOT NULL UNIQUE
);

CREATE TABLE movies (
id SERIAL PRIMARY KEY,
title VARCHAR(255)  NOT NULL,
release_date DATE,
duration  INT,
score NUMERIC(3, 1),
genre_id INT REFERENCES genres(id) ON DELETE SET NULL
);

CREATE TABLE actors (
id  SERIAL PRIMARY KEY,
first_name  VARCHAR(100) NOT NULL,
last_name   VARCHAR(100) NOT NULL,
birth_date  DATE,
nationality VARCHAR(100)
);

CREATE TABLE movie_actors (
movie_id INT NOT NULL REFERENCES movies(id) ON DELETE CASCADE,
actor_id INT NOT NULL REFERENCES actors(id) ON DELETE CASCADE,
role VARCHAR(255),
PRIMARY KEY (movie_id, actor_id)
);

INSERT INTO genres (name) VALUES
('Action'), ('Drama'), ('Comedy'),
('Thriller'), ('Sci-Fi'), ('Horror'),
('Romance'), ('Animation');

INSERT INTO movies (title, release_date, duration, score, genre_id) VALUES
('The Dark Knight',          '2008-07-18', 152, 9.0, 1),
('Inception',                '2010-07-16', 148, 8.8, 5),
('The Shawshank Redemption', '1994-10-14', 142, 9.3, 2),
('Interstellar',             '2014-11-07', 169, 8.6, 5),
('Pulp Fiction',             '1994-10-14', 154, 8.9, 4),
('The Godfather',            '1972-03-24', 175, 9.2, 2);

INSERT INTO actors (first_name, last_name, birth_date, nationality) VALUES
('Christian', 'Bale',      '1974-01-30', 'British'),
('Heath',     'Ledger',    '1979-04-04', 'Australian'),
('Leonardo',  'DiCaprio',  '1974-11-11', 'American'),
('Tim',       'Robbins',   '1958-10-16', 'American'),
('Morgan',    'Freeman',   '1937-06-01', 'American'),
('Marlon',    'Brando',    '1924-04-03', 'American'),
('Al',        'Pacino',    '1940-04-25', 'American');

INSERT INTO movie_actors (movie_id, actor_id, role) VALUES
(1, 1, 'Bruce Wayne / Batman'),
(1, 2, 'The Joker'),
(2, 3, 'Dom Cobb'),
(3, 4, 'Andy Dufresne'),
(3, 5, 'Red'),
(5, 6, 'Don Vito Corleone'),
(5, 7, 'Michael Corleone');