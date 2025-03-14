-- Create database
DROP DATABASE IF EXISTS customer_db;
CREATE DATABASE IF NOT EXISTS customer_db char set UTF8MB4;

-- Create user
CREATE USER 'financial'@'%' IDENTIFIED BY 'financial$';

-- Grant privileges
GRANT ALL PRIVILEGES ON customer_db.* TO 'financial'@'%';
FLUSH PRIVILEGES;
