CREATE TABLE IF NOT EXISTS genres
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS actors
(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE,
    nationality VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS movies
(
    id SERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    release_date DATE,
    duration INT,
    score INT,
    genre_id INT REFERENCES genres(id)
);

CREATE TABLE IF NOT EXISTS movie_actors
(
    movie_id INT REFERENCES movies(id),
    actor_id INT REFERENCES actors(id),
    PRIMARY KEY(movie_id,actor_id)
);