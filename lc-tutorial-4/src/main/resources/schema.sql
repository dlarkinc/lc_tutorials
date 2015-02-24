CREATE TABLE artists ( 
  id int(11) NOT NULL AUTO_INCREMENT, 
  fullName varchar(100) NOT NULL, 
  gender varchar(10) NOT NULL, 
  PRIMARY KEY (id) 
);

CREATE TABLE movements ( 
  id int(11) NOT NULL AUTO_INCREMENT, 
  name varchar(100) NOT NULL, 
  PRIMARY KEY (id) 
);

CREATE TABLE artist_movements ( 
  artist_id int(11) NOT NULL, 
  movement_id int(11) NOT NULL, 
  PRIMARY KEY (artist_id,movement_id) 
);
