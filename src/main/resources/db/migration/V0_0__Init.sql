CREATE TABLE employee (
    emp_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    age INT,
    gender VARCHAR(10),
    department VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into employee (emp_id, first_name, last_name, age, gender, department)
VALUES (1, 'Ritesh', 'Singh', 26, 'MALE', 'TECH'),
(2, 'Pratibha', 'Singh', 26, 'FEMALE', 'HR');