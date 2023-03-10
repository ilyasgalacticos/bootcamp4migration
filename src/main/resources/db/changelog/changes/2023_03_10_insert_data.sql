INSERT INTO t_permissions (role, name)
VALUES
       ('ROLE_USER', 'User'),
       ('ROLE_ADMIN', 'Administrator'),
       ('ROLE_TEACHER', 'Teacher Mentor');

INSERT INTO t_users (email, full_name, password)
VALUES
       ('ilyas@gmail.com', 'Ilyas Zhuanyshev', '$2a$10$4n448yf3mxelcMVNSUknv.9Mka2HnZHjrzoPBab9XgkxcMOwxmtUC'),
       ('admin@gmail.com', 'Admin Adminov', '$2a$10$4n448yf3mxelcMVNSUknv.9Mka2HnZHjrzoPBab9XgkxcMOwxmtUC'),
       ('erzhan@gmail.com', 'Erzhan Serikov', '$2a$10$4n448yf3mxelcMVNSUknv.9Mka2HnZHjrzoPBab9XgkxcMOwxmtUC'),
       ('nurzhan@gmail.com', 'Nurzhan Berikov', '$2a$10$4n448yf3mxelcMVNSUknv.9Mka2HnZHjrzoPBab9XgkxcMOwxmtUC');

INSERT INTO t_users_permissions (user_id, permissions_id)
VALUES
       (1, 1),
       (2, 1),
       (2, 2),
       (2, 3),
       (3, 1),
       (3, 3),
       (4, 1);