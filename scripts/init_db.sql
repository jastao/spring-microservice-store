# This sql script creates product catalog databases if not exist
CREATE DATABASE IF NOT EXISTS `product_catalog_db`;

# Create grant right to user
GRANT ALL ON `product_catalog_db`.* TO 'user'@'%';
