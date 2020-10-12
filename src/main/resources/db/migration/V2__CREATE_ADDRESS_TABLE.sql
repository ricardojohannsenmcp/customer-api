CREATE TABLE address (

id integer UNSIGNED AUTO_INCREMENT PRIMARY KEY,
state varchar (255),
city varchar (255),
neighborhood varchar (255),
zip_code varchar (50),
additional_information text,
main boolean,
customer_id integer,
street varchar(255),
number varchar(20)
);