-- changeset gxstar:init_schema

CREATE TABLE employee(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(300) NOT NULL,
    last_name VARCHAR(300) NOT NULL,
    email VARCHAR(200)
)