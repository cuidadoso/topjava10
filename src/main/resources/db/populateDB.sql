DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (datetime, description, calories, user_id) VALUES
  ('2017-04-10 09:00:00.0', 'Breakfast', 500, 100000),
  ('2017-04-10 14:00:00.0', 'Lunch', 2000, 100000),
  ('2017-04-10 21:00:00.0', 'Diner', 1500, 100000),
  ('2017-05-11 09:00:00.0', 'Breakfast', 510, 100001),
  ('2017-05-11 14:00:00.0', 'Lunch', 2000, 100001),
  ('2017-05-11 21:00:00.0', 'Diner', 1500, 100001);
