drop table if exists authorities cascade;
drop table if exists users cascade;
drop table if exists post cascade;
drop table if exists message;

create table if not exists authorities
(
    id        serial primary key,
    authority varchar(50) not null unique
);

create table if not exists users
(
    id           serial primary key,
    username     varchar(50)  not null unique,
    password     varchar(100) not null,
    enabled      boolean default true,
    authority_id int          not null references authorities (id)
);

create table if not exists post
(
    id          serial primary key,
    name        varchar(100)                not null,
    description text                        not null,
    user_id     int references users (id)   not null,
    created     timestamp without time zone not null default now()
);

create table if not exists message
(
    id      serial primary key,
    text    text                        not null,
    user_id int references users (id)   not null,
    post_id int references post (id)    not null,
    created timestamp without time zone not null default now()
);
