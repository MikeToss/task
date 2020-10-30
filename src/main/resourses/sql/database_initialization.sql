CREATE DATABASE test_task_db;

USE test_task_db;
CREATE TABLE employee
(
    employee_id     BIGINT       NOT NULL AUTO_INCREMENT,
    first_name      VARCHAR(32)  NOT NULL,
    last_name       VARCHAR(32)  NOT NULL,
    email           VARCHAR(255) NOT NULL,
    salary_per_hour DECIMAL(16, 1),
    date_of_birth   DATE         NOT NULL,
    PRIMARY KEY (employee_id),
    UNIQUE INDEX email_UNIQUE (email ASC) VISIBLE
);

USE test_task_db;
CREATE TABLE department
(
    department_id BIGINT      NOT NULL AUTO_INCREMENT,
    title         VARCHAR(51) NOT NULL,
    PRIMARY KEY (department_id),
    UNIQUE INDEX title_UNIQUE (title ASC) VISIBLE
);

USE test_task_db;
CREATE TABLE department_employees
(
    department_id BIGINT(11) NULL,
    employee_id   BIGINT(11) NULL,
    INDEX employees_employees_fk_idx (employee_id ASC) VISIBLE,
    INDEX departments_departments_fk_idx (department_id ASC) VISIBLE,
    CONSTRAINT employees_employees_fk
        FOREIGN KEY (employee_id)
            REFERENCES employee (employee_id),
    CONSTRAINT `departments_departments_fk`
        FOREIGN KEY (department_id)
            REFERENCES department (department_id)
);

USE test_task_db;
CREATE TABLE department_validation_attempts
(
    id                          BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    department_title            VARCHAR(255),
    is_validation_failed        TINYINT,
    reason_of_failed_validation VARCHAR(255),
    controller_of_listener      VARCHAR(255) NOT NULL,
    date_of_input               DATE,
    time_of_input               TIME
);

CREATE TABLE employee_validation_attempts
(
    id                          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name                  VARCHAR(255),
    last_name                   VARCHAR(255),
    email                       VARCHAR(255),

#     We using VARCHAR instead DECIMAL for validation data.
#     For example if user enter 1244aFd2, it will throw Exception and DB won't be contain input value
    salary_per_hour             VARCHAR(255),
#     Same we using VARCHAR instead DATE for validation data.
#     For example, if the user changes any field in UPDATE employees, but has not touched the date, this date may be nothing and as a result an exception
    date_of_birth               VARCHAR(255),
    is_validation_failed        TINYINT,
    reason_of_failed_validation VARCHAR(255),
    controller_of_listener      VARCHAR(255) NOT NULL,
    date_of_input               DATE,
    time_of_input               TIME
);

INSERT INTO department(Title)
VALUES ('Production Department'),
       ('Research and Development Department'),
       ('Marketing Department'),
       ('Human Resource Management'),
       ('Accounting and Finance Department'),
       ('Purchasing Department');

INSERT INTO employee(first_name, last_name, email, salary_per_hour, date_of_birth)
VALUES ('Claude', 'Speed', 'laudespeed@gmail.com', 25.0, '1975-11-05'),
       ('Toni', 'Cipriani', 'tonicipriani@gmail.com', 100.5, '1968-12-11'),
       ('Donald ', 'Love', 'donaldlove1960@gmail.com', 145.0, '1960-10-25'),
       ('Salvatore', 'Leone', 'salvatoreleone@yahoo.com', 175.0, '1935-12-06'),
       ('Kasen', 'Kenji', 'kasenkenji@gmail.com', 125.0, '1965-06-11'),

       ('Tommy', 'Vercetti', 'tommyvercetti@gmail.com', 110.0, '1951-06-10'),
       ('Lance', 'Vance', 'lancevance@gmail.com', 20.5, '1958-03-11'),
       ('Ken', 'Rosenberg', 'kenrosenberg@mail.ru', 45.0, '1948-11-12'),
       ('Sonny', 'Forelli', 'sonnyforellig@gmail.com', 80.0, '1946-12-20'),
       ('Ricardo', 'Diaz', 'ricardodiaz@yahoo.com', 160.0, '1942-01-02'),

       ('Carl', 'Johnson', 'carljohnson1968@gmail.com', 110.0, '1968-05-03'),
       ('Sean', 'Johnson', 'seanjohnson@yandex.ru', 65.0, '1966-03-11'),
       ('Melvin', 'Harris', 'melvinharris@yahoo.com', 70.0, '1967-10-29'),
       ('Lance', 'Wilson', 'lancewilson@gmail.com', 40.5, '1963-06-24'),
       ('Frank', 'Tenpenny', 'franktenpenny@gmail.com', 80.0, '1958-11-12'),
       ('Jeffrey', 'Cross', 'jeffreycross1973@mail.ru', 10.0, '1973-06-12'),
       ('Cesar', 'Vialpando', 'cesarvialpando@gmail.com', 32.0, '1942-01-02'),

       ('Niko', 'Bellic', 'nikobellic@yandex.ru', 60.0, '1978-11-29'),
       ('Jonathan', 'Johnny', 'jonathanjohnny@yahoo.com', 22.5, '1974-10-22'),

       ('Michael', 'De Santa', 'michaeldesanta@gmail.com', 55.0, '1965-06-06'),
       ('Trevor', 'Philips', 'trevor@gmail.com', 30.5, '1968-07-04'),
       ('Franklin', 'Clinton', 'franklinclinton1988@yahoo.com', 27.0, '1988-11-24');

INSERT INTO department_employees(department_id, employee_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),

       (2, 6),
       (2, 7),
       (2, 8),
       (2, 9),
       (2, 10),

       (3, 11),
       (3, 12),
       (3, 13),
       (3, 14),
       (3, 15),
       (3, 16),
       (3, 17),

       (4, 18),
       (4, 19),

       (5, 20),
       (5, 21),
       (5, 22);