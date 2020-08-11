insert into usr (id, username, password)
    values (1, 'admin', 'admin');

insert into user_role (user_id, roles)
    values (1, 'USER'), (1, 'ADMIN');

insert into usr (id, username, password)
    values (2, 'user', 'user');

insert into user_role (user_id, roles)
    values (2, 'USER');