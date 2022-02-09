INSERT INTO USER(name, email, password) VALUES('user', 'user@email.com', '$2a$10$TvpIywTmnZCE.5Ni6h.PSuknszA1YfA4.EeN9AOjGsV9qYHgrLgUm');

INSERT INTO COURSE(name, category) VALUES('Spring Boot', 'Programming');
INSERT INTO COURSE(name, category) VALUES('HTML 5', 'Front-end');

INSERT INTO TOPIC(title, message, date_of_criation, status, author_id, course_id) VALUES('query', 'Error when creating a project', '2021-05-05 18:00:00', 'NOT_ANSWERED', 1, 1);
INSERT INTO TOPIC(title, message, date_of_criation, status, author_id, course_id) VALUES('query 2', 'Project is not compiling', '2021-05-05 19:00:00', 'NOT_ANSWERED', 1, 1);
INSERT INTO TOPIC(title, message, date_of_criation, status, author_id, course_id) VALUES('query 3', 'Tag HTML', '2021-05-05 20:00:00', 'NOT_ANSWERED', 1, 2);