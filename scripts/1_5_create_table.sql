CREATE TABLE students
(
    id      BIGINT NOT NULL AUTO_INCREMENT,
    name    VARCHAR(25),
    surname varchar(25),
    avg_grade DOUBLE,
    PRIMARY KEY (id)
);