insert into receba.users (id, email, password, username, user_creation_date) values ('d4a296ca-c095-4585-9bad-6d88e0074f19', 'nikolas@nikolas', '$2a$12$wfew1NxgJx6dGzJ6qG38leNdK2xX1tHJB4.L4sJX6woHG/9J5/AqS', 'nikolas', current_timestamp);

insert into receba.roles (role_id, authority) values (1, 'ADMIN');
insert into receba.roles (role_id, authority) values (2, 'USER');

insert into receba.user_role_junction (role_id, user_id) values (1, 'd4a296ca-c095-4585-9bad-6d88e0074f19');
insert into receba.user_role_junction (role_id, user_id) values (2, 'd4a296ca-c095-4585-9bad-6d88e0074f19');