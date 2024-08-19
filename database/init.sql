CREATE DATABASE IF NOT EXISTS bakery OWNER jvg_25;
GRANT ALL PRIVILEGES ON DATABASE bakery TO jvg_25;

CREATE TABLE IF NOT EXISTS "public"."bakeryuser"(
    user_id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    lastname VARCHAR(30) NOT NULL,
    email TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    birth_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    phone VARCHAR(25) NOT NULL,
    role varchar(10) NOT NULL DEFAULT 'user',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS "public"."bakeryproduct"(
    product_id SERIAL NOT NULL PRIMARY KEY,
    price NUMERIC NOT NULL,
    name VARCHAR(50) NOT NULL,
    description TEXT DEFAULT '',
    category VARCHAR(255) NOT NULL DEFAULT '',
    stock INT NOT NULL DEFAULT 0,
    image TEXT DEFAULT '',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS "public"."bakerytoken"(
    user_email TEXT NOT NULL PRIMARY KEY,
    TOKEN TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS "public"."bakeryorder"(
    order_id SERIAL NOT NULL PRIMARY KEY,
    total_amount REAL NOT NULL,
    payment_method VARCHAR(20) NOT NULL DEFAULT 'cash',
    status VARCHAR(30) NOT NULL DEFAULT 'placed',
    order_user_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP DEFAULT NULL,
    FOREIGN KEY(order_user_id) REFERENCES bakeryUser(user_id)
);

CREATE TABLE IF NOT EXISTS "public"."bakeryorderwithproducts"(
    order_products_id INT NOT NULL,
    product_order_id INT NOT NULL,
    price REAL NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (order_products_id, product_order_id),
    FOREIGN KEY(order_products_id) REFERENCES bakeryOrder(order_id),
    FOREIGN KEY(product_order_id) REFERENCES bakeryProduct(product_id)
);

CREATE TABLE IF NOT EXISTS "public"."bakerycart" (
    cart_id INT NOT NULL PRIMARY KEY,
    total_amount REAL NOT NULL DEFAULT 0.0,
    FOREIGN KEY(cart_id) REFERENCES bakeryUser(user_id)
);

CREATE TABLE IF NOT EXISTS "public"."bakerycartwithproducts" (
    cart_products_id INT NOT NULL,
    product_cart_id INT NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY(cart_products_id, product_cart_id),
    FOREIGN KEY(cart_products_id) REFERENCES bakeryCart(cart_id),
    FOREIGN KEY(product_cart_id) REFERENCES bakeryProduct(product_id)
);
