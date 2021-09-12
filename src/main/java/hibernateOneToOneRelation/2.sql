CREATE TABLE hr_db.details (
  id int NOT NULL AUTO_INCREMENT,
  city varchar(15),
  phone_number varchar(25),
  email varchar(30), PRIMARY KEY (id)
);

CREATE TABLE hr_db.employees (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(25),
  surname varchar(50),
  department varchar(50), salary int, details_id int
,  PRIMARY KEY (id)
, FOREIGN KEY (details_id) REFERENCES hr_db.details(id));
