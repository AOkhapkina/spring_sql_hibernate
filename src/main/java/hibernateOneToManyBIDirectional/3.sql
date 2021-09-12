DROP hr_db.departments
DROP hr_db.employees2
CREATE TABLE hr_db.departments (
  id int NOT NULL AUTO_INCREMENT,
  department_name varchar(15),
  max_salary int,
  min_salary int,
  PRIMARY KEY (id)
);

CREATE TABLE hr_db.employees2 (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(15),
  surname varchar(25),
  salary int,
  department_id int,
  PRIMARY KEY (id),
  FOREIGN KEY (department_id) REFERENCES hr_db.departments(id));