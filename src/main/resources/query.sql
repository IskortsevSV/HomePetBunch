DROP DATABASE IF EXISTS flowers;

CREATE DATABASE IF NOT EXISTS flowers;

use flowers;



CREATE TABLE flowers(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  mark VARCHAR(45) NOT NULL
);

CREATE TABLE bunch(
  id INT NOT NULL auto_increment ,
  PRIMARY KEY (id)
);

CREATE TABLE flowers_exists(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  mark_id INT NOT NULL,
  price DOUBLE(4,2) NOT NULL,
  length_steak INT NOT NULL,
  ice_level INT NOT NULL,
  bunch_id int,
  foreign key (mark_id) references flowers(id),
  foreign key (bunch_id) references bunch(id)
);


CREATE TABLE user(
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  admin BOOLEAN NOT NULL DEFAULT FALSE,
  primary key(id)
);


CREATE TABLE user_bunch(
  user_id INT NOT NULL,
  bunch_id INT NOT NULL,
  primary key (user_id, bunch_id),
  foreign key (bunch_id) references bunch(id),
  foreign key (user_id) references user(id)
);


INSERT INTO flowers(mark) VALUES('Romashka'),
  ('Fialka'),
  ('Podsnezhnik'),
  ('Rose'),
  ('Tulpin');

