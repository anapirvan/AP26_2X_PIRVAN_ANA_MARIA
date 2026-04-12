CREATE TABLE movie_lists (
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(200) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE movie_list_entries (
    list_id  INT REFERENCES movie_lists(id),
    movie_id INT REFERENCES movies(id),
    PRIMARY KEY (list_id, movie_id)
);