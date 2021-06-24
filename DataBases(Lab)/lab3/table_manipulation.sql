use employees;

DROP TABLE addr_emp;
DROP TABLE address;

CREATE TABLE address (
adr_no INT NOT NULL,
city VARCHAR(30) NOT NULL,
street VARCHAR(40) NOT NULL,
house_no VARCHAR(10) NOT NULL,
post_code VARCHAR(20) NOT NULL,
country VARCHAR(50) NOT NULL,
PRIMARY KEY (adr_no)
);
CREATE TABLE adr_emp (
emp_no INT NOT NULL,
adr_no INT NOT NULL,
adr_type ENUM ('P','T') NOT NULL,
FOREIGN KEY (adr_no) REFERENCES address (adr_no) ON DELETE CASCADE,
FOREIGN KEY (emp_no) REFERENCES employees (emp_no) ON DELETE CASCADE,
PRIMARY KEY (emp_no, adr_no)
);

INSERT INTO address (adr_no,city,street,house_no,post_code,country) VALUES
(1,'Wroclaw',"Wittiga","8","51-628","Poland"),
(2,'Berlin',"Streetst","12","31-342","Germany");

INSERT INTO adr_emp (emp_no,adr_no,adr_type) values
(10001,1,'P'),
(10002,2,'P');

SELECT (adr_no,city,street) from address;

SELECT (country) from address WHERE adr_no % 2 = 0;

UPDATE address SET country = 'Poland';
DELETE FROM address WHERE adr_no = 2;

select * from address