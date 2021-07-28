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

insert into post(name, description, user_id)
values ('Правила форума', 'Обязательно к прочтению', 1);

insert into authorities (authority)
values ('ROLE_USER');
insert into authorities (authority)
values ('ROLE_ADMIN');

insert into users (username, password, enabled, authority_id)
values ('admin', '$2a$10$oXa/cKL3yS3.51BoN5HKAO048ExAma/QPGTEqWGlQFGfjkGcjzZvW', true,
        (select id from authorities where authority = 'ROLE_ADMIN'));

--truncate table post restart identity cascade;