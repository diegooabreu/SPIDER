
GRANT USAGE ON *.* TO 'spider_rm'@'localhost';
FLUSH PRIVILEGES;
GRANT SELECT, EXECUTE, SHOW VIEW, ALTER, ALTER ROUTINE, CREATE, CREATE ROUTINE, CREATE TEMPORARY TABLES, CREATE VIEW, DELETE, DROP, EVENT, INDEX, INSERT, REFERENCES, TRIGGER, UPDATE, LOCK TABLES  ON `spider\_rm`.* TO 'spider_rm'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;