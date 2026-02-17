CREATE TABLE clientes (
     id INT AUTO_INCREMENT PRIMARY KEY,
     nombreComercial VARCHAR(120) NOT NULL,
    nombrePropietario VARCHAR(120) NOT NULL,
    documento VARCHAR(20),
    telefono VARCHAR(20),
    direccion VARCHAR(200),
    email VARCHAR(120),
    activo BOOLEAN DEFAULT TRUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
