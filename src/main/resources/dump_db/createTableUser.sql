CREATE TABLE public.user
(
    id         bigserial   not null unique PRIMARY KEY,
    username   varchar(50) NOT NULL,
    first_name varchar(50) NOT NULL,
    last_name  varchar(50) NOT NULL,
    role_id    int         NOT NULL,
    CONSTRAINT fk_user_role_id
        FOREIGN KEY (role_id)
        REFERENCES role (role_id)
);
