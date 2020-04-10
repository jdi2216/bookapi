-- create user with name bookapi
CREATE USER 'bookapidev'@'localhost' IDENTIFIED BY 'bookapidev';

-- provide all the permission
GRANT ALL PRIVILEGES ON * . * TO 'bookapidev'@'localhost';

-- create password bookapidev
ALTER USER 'bookapidev'@'localhost' IDENTIFIED WITH mysql_native_password BY 'bookapidev123';