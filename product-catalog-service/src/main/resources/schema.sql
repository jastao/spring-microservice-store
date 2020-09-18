CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(50) NOT NULL,
    description VARCHAR(200),
    product_code VARCHAR(20) NOT NULL,
    price BIGINT NOT NULL
);