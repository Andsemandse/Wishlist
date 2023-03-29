DROP DATABASE IF EXISTS wishlist_DB;

CREATE DATABASE wishlist_DB;
USE wishlist_DB;

CREATE TABLE person (
  id INT NOT NULL AUTO_INCREMENT,
  personName VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE wishlist (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  person_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (person_id) REFERENCES person(id)
);


CREATE TABLE wish (
  id INT NOT NULL AUTO_INCREMENT,
  wishName VARCHAR(255) NOT NULL,
  details VARCHAR(255) NOT NULL,
  price INT,
  amount INT,
  fulfilled TINYINT,
  wishlist_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (wishlist_id) REFERENCES wishlist(id)
);

commit;