DELETE FROM users;
DELETE FROM queues;
DELETE FROM slots;
DELETE FROM user_roles;

INSERT INTO users(id, name, email, password, enabled)
VALUES (1, 'Admin', 'l@og.in', 'pass', TRUE),  -- pass
       (2, 'SuperUser', 'l1@og.in', 'pass1', TRUE), -- pass1
       (3, 'User', 'l2@og.in', 'pass2', true); -- pass2


INSERT INTO user_roles(user_id, role)
VALUES (1, 'USER'),
       (2, 'USER'),
       (3, 'USER'),
       (1, 'ADMIN'),
       (2, 'SUPERUSER');

INSERT INTO queues(id, name, user_id)
VALUES (1, 'FIRST QUEUE', 2);

INSERT INTO breaks(name, queue_id, start_time, end_time)
VALUES ('lunch', 1, '13:00', '14:00');

INSERT INTO slots (queue_id, user_id, date_time)
VALUES (1, 1, '2024-12-16 08:30:00.000000'),
       (1, 1, '2024-12-17 09:30:00.000000'),
       (1, 1, '2024-12-17 12:30:00.000000'),
       (1, 3, '2024-12-16 10:30:00.000000'),
       (1, 3, '2024-12-16 15:30:00.000000'),
       (1, 3, '2024-12-18 10:30:00.000000');