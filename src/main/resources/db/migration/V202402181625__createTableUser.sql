CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL,
    race_id INTEGER NOT NULL,
    FOREIGN KEY (race_id) REFERENCES race(race_id),
    profession_id INTEGER NOT NULL,
    FOREIGN KEY (profession_id) REFERENCES profession(profession_id),
    user_level INT NOT NULL,
    date_of_birth DATE NOT NULL,
    date_of_registration DATE NOT NULL,
    status VARCHAR(10) NOT NULL
);