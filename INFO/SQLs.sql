CREATE DATABASE products_new_db;

USE products_new_db;

CREATE TABLE IF NOT EXISTS products
( id INTEGER NOT NULL AUTO_INCREMENT,
  name VARCHAR(128) NOT NULL,
  quota VARCHAR(128) NOT NULL,
  price VARCHAR(56) NOT NULL,
  PRIMARY KEY (id)
);

