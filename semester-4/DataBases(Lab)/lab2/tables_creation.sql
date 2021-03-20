USE employees;

CREATE TABLE address (
	address_id int NOT NULL AUTO_INCREMENT,
    country nvarchar(255) NOT NULL,
    city nvarchar(255) NOT NULL,
    street nvarchar(255) NOT NULL,
    
    PRIMARY KEY (address_id)
);

CREATE table addr_emp(
	address_id int,
    emp_no int,
    addr_type nvarchar(255),
    CONSTRAINT addr_emp_id PRIMARY KEY(address_id,emp_no),
    CONSTRAINT FK_addr
      FOREIGN KEY (address_id) REFERENCES address (address_id),
	CONSTRAINT FK_employee
		FOREIGN KEY (emp_no) references employees (emp_no)
);


select * from employees
inner join addr_emp
on employees.emp_no = addr_emp.emp_no
inner join address
on addr_emp.address_id = address.address_id
where addr_emp.addr_type = "primary"
