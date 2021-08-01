CREATE TABLE authorities
(
    id        serial primary key,
    authority varchar(50) not null unique
);

create table users
(
    id           serial primary key,
    username     varchar(50)  not null unique,
    password     varchar(100) not null,
    enabled      boolean default true,
    authority_id int          not null references authorities (id)
);

create table post
(
    id          serial primary key,
    name        varchar(100)                not null,
    description text                        not null,
    user_id     int references users (id)   not null,
    created     timestamp without time zone not null default now()
);

create table message
(
    id      serial primary key,
    text    text                        not null,
    user_id int references users (id)   not null,
    post_id int references post (id)    not null,
    created timestamp without time zone not null default now()
);

--truncate table post restart identity cascade;