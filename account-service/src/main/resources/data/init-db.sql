-- Create database
DROP DATABASE IF EXISTS account_db;
CREATE DATABASE IF NOT EXISTS account_db char set UTF8MB4;

-- Create user
CREATE USER 'financial'@'%' IDENTIFIED BY 'financial$';

-- Grant privileges
GRANT ALL PRIVILEGES ON account_db.* TO 'financial'@'%';
FLUSH PRIVILEGES;
