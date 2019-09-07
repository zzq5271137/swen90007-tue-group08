DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS user_has_destination;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS destination;
DROP TABLE IF EXISTS keys;

CREATE TABLE users (
   user_id INT,
   username VARCHAR(50) NOT NULL,
   password VARCHAR(50) NOT NULL,
   user_type VARCHAR(50) NOT NULL,
   PRIMARY KEY(user_id)
);

INSERT INTO users(user_id, username, password, user_type) VALUES (1, 'zhengqingz', '123456', 'customer');
INSERT INTO users(user_id, username, password, user_type) VALUES (2, 'hez', '456789', 'customer');
INSERT INTO users(user_id, username, password, user_type) VALUES (3, 'john', '123456', 'courier');
INSERT INTO users(user_id, username, password, user_type) VALUES (4, 'mark', '456789', 'courier');

CREATE TABLE destination (
    destination_id INT,
    address VARCHAR(200) NOT NULL,
    PRIMARY KEY(destination_id)
);

INSERT INTO destination(destination_id, address) VALUES (1, 'Melbourne');
INSERT INTO destination(destination_id, address) VALUES (2, 'Sydney');
INSERT INTO destination(destination_id, address) VALUES (3, 'Geelong');

CREATE TABLE user_has_destination (
    user_id INT REFERENCES users(user_id) ON DELETE CASCADE NOT NULL,
    destination_id INT REFERENCES destination(destination_id) ON DELETE CASCADE NOT NULL,
    PRIMARY KEY(user_id, destination_id)
);

INSERT INTO user_has_destination(user_id, destination_id) VALUES (1, 1);
INSERT INTO user_has_destination(user_id, destination_id) VALUES (2, 2);
INSERT INTO user_has_destination(user_id, destination_id) VALUES (2, 3);

CREATE TABLE orders (
    order_id INT,
    status VARCHAR(50) NOT NULL,
    item_size INT NOT NULL,
    item_weight INT NOT NULL,
    destination_id INT REFERENCES destination(destination_id) ON DELETE CASCADE NOT NULL,
    customer_id INT REFERENCES users(user_id) ON DELETE CASCADE NOT NULL,
    courier_id INT REFERENCES users(user_id) ON DELETE CASCADE,
    PRIMARY KEY(order_id)
);

-- CREATE TYPE order_status as ENUM('Confirmed', 'Shipped', 'Delivered');
INSERT INTO orders(order_id, status, item_size, item_weight, destination_id, customer_id) VALUES (1, 'Confirmed', 1, 5, 1, 1);
INSERT INTO orders(order_id, status, item_size, item_weight, destination_id, customer_id, courier_id) VALUES (2, 'Shipped', 9, 15, 2, 1, 3);
INSERT INTO orders(order_id, status, item_size, item_weight, destination_id, customer_id) VALUES (3, 'Confirmed', 2, 7, 3, 2);
INSERT INTO orders(order_id, status, item_size, item_weight, destination_id, customer_id, courier_id) VALUES (4, 'Delivered', 1, 5, 2, 2, 4);

CREATE TABLE keys (
    table_name VARCHAR(50) NOT NULL,
    next_id INT NOT NULL,
    PRIMARY KEY(table_name)
);

INSERT INTO keys(table_name, next_id) VALUES ('users', 5);
INSERT INTO keys(table_name, next_id) VALUES ('destination', 4);
INSERT INTO keys(table_name, next_id) VALUES ('user_has_destination', 4);
INSERT INTO keys(table_name, next_id) VALUES ('orders', 5);
