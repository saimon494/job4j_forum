create table users
(
    id       serial primary key,
    name     varchar(200) not null,
    password varchar(200) not null
);

create table post
(
    id          serial primary key,
    name        varchar(2000)               not null,
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

insert into users(name, password)
values ('admin', 'admin');

insert into post(name, description, user_id)
values ('Правила форума', 'Обязательно к прочтению', 1);

--truncate table users restart identity cascade;