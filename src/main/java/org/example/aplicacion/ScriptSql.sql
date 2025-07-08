-- Crear la base de datos
CREATE DATABASE bd_hibernate;
-- Usar la base de datos
USE bd_hibernate;
-- Crear la tabla mascotas
CREATE TABLE mascotas (
id_mascota INT(11) NOT NULL AUTO_INCREMENT,
nombre VARCHAR(45) DEFAULT NULL,
raza VARCHAR(45) DEFAULT NULL,
color VARCHAR(45) DEFAULT NULL,
sexo VARCHAR(45) DEFAULT NULL,
PRIMARY KEY (id_mascota)
);
