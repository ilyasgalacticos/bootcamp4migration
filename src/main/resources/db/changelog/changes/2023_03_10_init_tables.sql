CREATE TABLE t_permissions(
    id bigint auto_increment,
    role varchar(255),
    name varchar(255),
    primary key (id)
);

CREATE TABLE t_users(
    id bigint auto_increment,
    email varchar(255),
    full_name varchar(255),
    password varchar(255),
    primary key (id)
);

CREATE TABLE t_users_permissions(
    user_id bigint,
    permissions_id bigint(255)
);

CREATE TABLE t_posts(
    id bigint auto_increment,
    content_text TEXT,
    title varchar(255),
    author_id bigint,
    primary key (id)
);