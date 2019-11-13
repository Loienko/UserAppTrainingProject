CREATE TABLE public.user
(
    id         bigserial   not null unique PRIMARY KEY,
    username   varchar(50) NOT NULL,
    first_name varchar(50) NOT NULL,
    last_name  varchar(50) NOT NULL,
    role_id    int REFERENCES role (id)
);
