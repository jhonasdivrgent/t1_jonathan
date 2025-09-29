
-- 1) Crear base de datos
CREATE DATABASE COMPRA
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

USE COMPRA;

-- 2) Crear tabla clientes
CREATE TABLE clientes (
  idcliente INT AUTO_INCREMENT PRIMARY KEY,
  nombres VARCHAR(100) NOT NULL,
  apellidos VARCHAR(100) NOT NULL,
  celular VARCHAR(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3) Crear tabla pedidos
CREATE TABLE pedidos (
  idpedido INT AUTO_INCREMENT PRIMARY KEY,
  total_items INT NOT NULL,
  precio DECIMAL(10,2) NOT NULL,
  idcliente INT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_pedidos_clientes FOREIGN KEY (idcliente)
    REFERENCES clientes(idcliente)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 4) Inserts: 3 clientes
INSERT INTO clientes (nombres, apellidos, celular) VALUES
('Carlos', 'Gonzalez', '987654321'),
('María', 'Pérez', '912345678'),
('Luis', 'Rodríguez', '999888777');

-- 5) Inserts: 3 pedidos por cada cliente (asume ids 1,2,3)
INSERT INTO pedidos (total_items, precio, idcliente) VALUES
-- pedidos cliente 1
(2, 25.00, 1),
(1, 12.50, 1),
(4, 50.00, 1),

-- pedidos cliente 2
(3, 36.00, 2),
(2, 20.00, 2),
(5, 75.00, 2),

-- pedidos cliente 3
(1, 10.00, 3),
(2, 22.00, 3),
(6, 120.00, 3);


SELECT * FROM clientes;

-- Ver pedidos
SELECT * FROM pedidos;