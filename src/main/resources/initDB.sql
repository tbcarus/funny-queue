DROP TABLE IF EXISTS records;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;


CREATE TABLE users
(
    id          INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    email       VARCHAR(128) NOT NULL,
    password    VARCHAR(128) NOT NULL,
    name        VARCHAR(128) NOT NULL,
    enabled     boolean      NOT NULL,
    create_date TIMESTAMP,
    last_update TIMESTAMP
);


CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


create table public.records
(
    id             integer generated by default as identity primary key,
    user_id        integer not null,
    queue_owner_id integer not null,
    date_time      timestamp,
    create_date    timestamp,
    last_update    timestamp,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (queue_owner_id) REFERENCES users(id) ON DELETE CASCADE
);