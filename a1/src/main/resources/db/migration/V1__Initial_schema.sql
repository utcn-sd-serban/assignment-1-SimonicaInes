CREATE TABLE IF NOT EXISTS users(
    id serial PRIMARY KEY,
    username VARCHAR(32) NOT NULL,
    password VARCHAR(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS question(
    id serial PRIMARY KEY,
    title VARCHAR(32) NOT NULL,
    author_Id INTEGER  NOT NULL,
    textq VARCHAR(64) NOT NULL,
    dateq DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS tag(
    id serial PRIMARY KEY,
    tag_Text VARCHAR(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS questiontags(
    id serial PRIMARY KEY,
    question_Id INTEGER NOT NULL,
    tag_Id INTEGER NOT NULL

);