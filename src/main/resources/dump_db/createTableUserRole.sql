create table IF NOT EXISTS public.role
(
    role_id          bigserial   not null unique PRIMARY KEY,
    role_name        varchar(50) not null unique,
    role_description varchar(50) not null
);
