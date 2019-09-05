DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS couriers;
DROP SEQUENCE IF EXISTS customer_seq;
DROP SEQUENCE IF EXISTS courier_seq;
DROP SEQUENCE IF EXISTS order_seq;
DROP TYPE IF EXISTS order_status;

CREATE SEQUENCE customer_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 99999999
    CACHE 1;
    
CREATE SEQUENCE courier_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 99999999
    CACHE 1;

CREATE SEQUENCE order_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 99999999
    CACHE 1;

CREATE TABLE customers (
   customer_id INTEGER DEFAULT NEXTVAL('customer_seq'),
   name VARCHAR(50) NOT NULL,
   username VARCHAR(50) NOT NULL,
   password VARCHAR(50) NOT NULL,
   PRIMARY KEY(customer_id)
);

INSERT INTO customers(name, username, password) VALUES ('Zhengqing Zhu', 'zhengqingz', '123456');
INSERT INTO customers(name, username, password) VALUES ('He Zhu', 'hez', '456789');

CREATE TABLE couriers (
   courier_id INTEGER DEFAULT NEXTVAL('courier_seq'),
     name VARCHAR(50) NOT NULL,
     username VARCHAR(50) NOT NULL,
   password VARCHAR(50) NOT NULL,
   PRIMARY KEY(courier_id)
);

INSERT INTO couriers(name, username, password) VALUES ('Tom', 'tom', '123456');
INSERT INTO couriers(name, username, password) VALUES ('Mark', 'mark', '456789');

CREATE TYPE order_status as ENUM('Confirmed', 'Shipped', 'Delivered');

CREATE TABLE orders (
   order_id INTEGER DEFAULT NEXTVAL('order_seq'),
     departure VARCHAR(50) NOT NULL,
     destination VARCHAR(50) NOT NULL,
     status order_status NOT NULL,
     customer_id INTEGER REFERENCES customers(customer_id) ON DELETE CASCADE NOT NULL,
     courier_id INTEGER REFERENCES couriers(courier_id) ON DELETE CASCADE,
   PRIMARY KEY(order_id)
);

INSERT INTO orders(departure, destination, status, customer_id) VALUES ('Melbourne', 'Geelong', 'Confirmed', 1);
INSERT INTO orders(departure, destination, status, customer_id, courier_id) VALUES ('Shanghai', 'Beijing', 'Shipped', 1, 1);
INSERT INTO orders(departure, destination, status, customer_id, courier_id) VALUES ('Melbourne', 'Sydney', 'Delivered', 2, 2);