INSERT INTO USER(id, name, email, password) VALUES(1, 'Student', 'user@email.com', '$2a$10$TvpIywTmnZCE.5Ni6h.PSuknszA1YfA4.EeN9AOjGsV9qYHgrLgUm');
INSERT INTO USER(id, name, email, password) VALUES(2, 'Admin', 'bruna@email.com', '$2a$10$TvpIywTmnZCE.5Ni6h.PSuknszA1YfA4.EeN9AOjGsV9qYHgrLgUm');

INSERT INTO COURSE(name, category) VALUES('Spring Boot', 'Programming');
INSERT INTO COURSE(name, category) VALUES('HTML 5', 'Front-end');

INSERT INTO ROLE(id, name) VALUES(1, 'ROLE_STUDENT');
INSERT INTO ROLE(id, name) VALUES(2, 'ROLE_ADMIN');

INSERT INTO TOPIC(title, message, date_of_criation, status, author_id, course_id) VALUES('query', 'Error when creating a project', '2021-05-05 18:00:00', 'NOT_ANSWERED', 1, 1);
INSERT INTO TOPIC(title, message, date_of_criation, status, author_id, course_id) VALUES('query 2', 'Project is not compiling', '2021-05-05 19:00:00', 'NOT_ANSWERED', 1, 1);
INSERT INTO TOPIC(title, message, date_of_criation, status, author_id, course_id) VALUES('query 3', 'Tag HTML', '2021-05-05 20:00:00', 'NOT_ANSWERED', 1, 2);

INSERT INTO USER_ROLE(user_id, role_id) VALUES(1, 1);
INSERT INTO USER_ROLE(user_id, role_id) VALUES(2, 2);