DROP TABLE IF EXISTS user_has_destination;
DROP TABLE IF EXISTS courier_log;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS destination;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS keys;

CREATE TABLE users (
  user_id INT,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  user_type VARCHAR(50) NOT NULL,
  PRIMARY KEY(user_id)
);

INSERT INTO
  users(user_id, username, password, user_type)
VALUES
  (1, 'customer1', '123456', 'customer');
INSERT INTO
  users(user_id, username, password, user_type)
VALUES
  (2, 'customer2', '456789', 'customer');
INSERT INTO
  users(user_id, username, password, user_type)
VALUES
  (3, 'courier1', '123456', 'courier');
INSERT INTO
  users(user_id, username, password, user_type)
VALUES
  (4, 'courier2', '456789', 'courier');

CREATE TABLE destination (
    destination_id INT,
    address VARCHAR(200) NOT NULL,
    PRIMARY KEY(destination_id)
);

INSERT INTO
  destination(destination_id, address)
VALUES
  (1, 'Melbourne');
INSERT INTO
  destination(destination_id, address)
VALUES
  (2, 'Sydney');
INSERT INTO
  destination(destination_id, address)
VALUES
  (3, 'Geelong');
INSERT INTO
  destination(destination_id, address)
VALUES
  (4, 'Beijing');

CREATE TABLE user_has_destination (
    user_id INT REFERENCES users(user_id) ON DELETE CASCADE NOT NULL,
    destination_id INT REFERENCES destination(destination_id) ON DELETE CASCADE NOT NULL,
    PRIMARY KEY(user_id, destination_id)
);

INSERT INTO
  user_has_destination(user_id, destination_id)
VALUES
  (1, 1);
INSERT INTO
  user_has_destination(user_id, destination_id)
VALUES
  (1, 2);
INSERT INTO
  user_has_destination(user_id, destination_id)
VALUES
  (1, 3);
INSERT INTO
  user_has_destination(user_id, destination_id)
VALUES
  (1, 4);
INSERT INTO
  user_has_destination(user_id, destination_id)
VALUES
  (2, 1);
INSERT INTO
  user_has_destination(user_id, destination_id)
VALUES
  (2, 2);
INSERT INTO
  user_has_destination(user_id, destination_id)
VALUES
  (2, 3);
INSERT INTO
  user_has_destination(user_id, destination_id)
VALUES
  (2, 4);

CREATE TABLE orders (
    order_id INT,
    status VARCHAR(50) NOT NULL,
    item_size FLOAT NOT NULL,
    item_weight FLOAT NOT NULL,
    destination_id INT REFERENCES destination(destination_id) ON DELETE CASCADE NOT NULL,
    customer_id INT REFERENCES users(user_id) ON DELETE CASCADE NOT NULL,
    courier_id INT REFERENCES users(user_id) ON DELETE CASCADE,
    PRIMARY KEY(order_id)
);

INSERT INTO
  orders(
    order_id,
    status,
    item_size,
    item_weight,
    destination_id,
    customer_id,
    courier_id
  )
VALUES
  (1, 'Delivered', 9.4, 15.2, 2, 1, 3);
INSERT INTO
  orders(
    order_id,
    status,
    item_size,
    item_weight,
    destination_id,
    customer_id,
    courier_id
  )
VALUES
  (2, 'Shipped', 18.4, 14.3, 4, 1, 3);
INSERT INTO
  orders(
    order_id,
    status,
    item_size,
    item_weight,
    destination_id,
    customer_id,
    courier_id
  )
VALUES
  (3, 'Shipped', 3.5, 14.7, 3, 1, 4);
INSERT INTO
  orders(
    order_id,
    status,
    item_size,
    item_weight,
    destination_id,
    customer_id,
    courier_id
  )
VALUES
  (4, 'Shipped', 8.4, 11.3, 2, 1, 3);
INSERT INTO
  orders(
    order_id,
    status,
    item_size,
    item_weight,
    destination_id,
    customer_id
  )
VALUES
  (5, 'Confirmed', 1.6, 5.3, 1, 1);
INSERT INTO
  orders(
    order_id,
    status,
    item_size,
    item_weight,
    destination_id,
    customer_id
  )
VALUES
  (6, 'Confirmed', 7.6, 2.3, 3, 1);
INSERT INTO
  orders(
    order_id,
    status,
    item_size,
    item_weight,
    destination_id,
    customer_id
  )
VALUES
  (7, 'Confirmed', 3.4, 8.7, 4, 1);
INSERT INTO
  orders(
    order_id,
    status,
    item_size,
    item_weight,
    destination_id,
    customer_id,
    courier_id
  )
VALUES
  (8, 'Delivered', 1.5, 5.4, 3, 2, 4);
INSERT INTO
  orders(
    order_id,
    status,
    item_size,
    item_weight,
    destination_id,
    customer_id,
    courier_id
  )
VALUES
  (9, 'Shipped', 14.5, 6.1, 3, 2, 4);
INSERT INTO
  orders(
    order_id,
    status,
    item_size,
    item_weight,
    destination_id,
    customer_id,
    courier_id
  )
VALUES
  (10, 'Shipped', 2.7, 5.6, 4, 2, 3);
INSERT INTO
  orders(
    order_id,
    status,
    item_size,
    item_weight,
    destination_id,
    customer_id,
    courier_id
  )
VALUES
  (11, 'Shipped', 9.1, 7.2, 1, 2, 4);
INSERT INTO
  orders(
    order_id,
    status,
    item_size,
    item_weight,
    destination_id,
    customer_id
  )
VALUES
  (12, 'Confirmed', 2.1, 7.9, 1, 2);
INSERT INTO
  orders(
    order_id,
    status,
    item_size,
    item_weight,
    destination_id,
    customer_id
  )
VALUES
  (13, 'Confirmed', 3.7, 9.5, 2, 2);
INSERT INTO
  orders(
    order_id,
    status,
    item_size,
    item_weight,
    destination_id,
    customer_id
  )
VALUES
  (14, 'Confirmed', 10.6, 8.7, 3, 2);

CREATE TABLE courier_log (
    courier_id INT REFERENCES users(user_id) ON DELETE CASCADE NOT NULL,
    log_date VARCHAR(50) NOT NULL,
    sent_count INT NOT NULL,
    PRIMARY KEY(courier_id, log_date)
);

INSERT INTO
  courier_log(courier_id, log_date, sent_count)
VALUES
  (3, '2019-10-03', 11);
INSERT INTO
  courier_log(courier_id, log_date, sent_count)
VALUES
  (3, '2019-10-04', 9);
INSERT INTO
  courier_log(courier_id, log_date, sent_count)
VALUES
  (3, '2019-10-05', 7);
INSERT INTO
  courier_log(courier_id, log_date, sent_count)
VALUES
  (4, '2019-10-03', 8);
INSERT INTO
  courier_log(courier_id, log_date, sent_count)
VALUES
  (4, '2019-10-04', 10);
INSERT INTO
  courier_log(courier_id, log_date, sent_count)
VALUES
  (4, '2019-10-05', 5);

CREATE TABLE courier_log (
    courier_id INT REFERENCES users(user_id) ON DELETE CASCADE NOT NULL,
    log_date DATE NOT NULL,
    sent_count INT NOT NULL,
    PRIMARY KEY(courier_id, log_date)
);

INSERT INTO courier_log(courier_id, log_date, sent_count) VALUES (3, '2019-10-03', 11);
INSERT INTO courier_log(courier_id, log_date, sent_count) VALUES (3, '2019-10-04', 9);
INSERT INTO courier_log(courier_id, log_date, sent_count) VALUES (3, '2019-10-05', 7);
INSERT INTO courier_log(courier_id, log_date, sent_count) VALUES (4, '2019-10-03', 8);
INSERT INTO courier_log(courier_id, log_date, sent_count) VALUES (4, '2019-10-04', 10);
INSERT INTO courier_log(courier_id, log_date, sent_count) VALUES (4, '2019-10-05', 5);

CREATE TABLE keys (
    table_name VARCHAR(50) NOT NULL,
    next_id INT NOT NULL,
    PRIMARY KEY(table_name)
);

INSERT INTO
  keys(table_name, next_id)
VALUES
  ('users', 5);
INSERT INTO
  keys(table_name, next_id)
VALUES
  ('destination', 5);
INSERT INTO
  keys(table_name, next_id)
VALUES
  ('orders', 15);