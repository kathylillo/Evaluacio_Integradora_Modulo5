CREATE DATABASE alke_wallet;
USE alke_wallet;

CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    balance DECIMAL(10,2) DEFAULT 0.00
);

  CREATE TABLE transaction_type (
    type_id INT PRIMARY KEY AUTO_INCREMENT,
    type_name VARCHAR(50) NOT NULL
);
INSERT INTO transaction_type (type_name) VALUES ('DEPOSIT');
INSERT INTO transaction_type (type_name) VALUES ('WITHDRAWAL');

CREATE TABLE transaction (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    amount DECIMAL(10, 2) NOT NULL,
    type_id INT NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (type_id) REFERENCES transaction_type(type_id)
);

-- Insertar usuario
INSERT INTO  users (username,password,balance )
  VALUES ('JuanPablo','Profe12345',0);
  
  select * from users;
  
  INSERT INTO transaction(user_id,amount,type_id,transaction_date)
  VALUES (1,100,1,now());

  INSERT INTO transaction(user_id,amount,type_id,transaction_date)
  VALUES (1,80,2,now());
  
  select * from transaction; 
  
-- Eliminar todas las transacciones de un usuario específico
DELETE FROM transaction WHERE user_id = 1;

 INSERT INTO  users (username,password,balance )
  VALUES ('KathyLillo','12345',1000);
  
-- Eliminar un usuario específico por su id 
DELETE FROM users WHERE user_id = 2;