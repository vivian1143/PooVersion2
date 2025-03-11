CREATE DATABASE IF NOT EXISTS academia;
USE academia;


CREATE TABLE IF NOT EXISTS persona (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    tipoContrato VARCHAR(50),
    UNIQUE (email)
) AUTO_INCREMENT = 10000;

CREATE TABLE IF NOT EXISTS facultad (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    decano_id INT,
    FOREIGN KEY (decano_id) REFERENCES persona(id)
) AUTO_INCREMENT = 200;

CREATE TABLE IF NOT EXISTS programa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    duracion DOUBLE NOT NULL,
    registro DATE NOT NULL,
    facultad_id INT,
    FOREIGN KEY (facultad_id) REFERENCES facultad(id)
) AUTO_INCREMENT = 700;

CREATE TABLE IF NOT EXISTS curso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    programa_id INT,
    activo BOOLEAN NOT NULL,
    FOREIGN KEY (programa_id) REFERENCES programa(id)
) AUTO_INCREMENT = 4000;

CREATE TABLE IF NOT EXISTS estudiante (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    codigo INT NOT NULL,
    programa_id INT,
    activo BOOLEAN NOT NULL,
    promedio DOUBLE NOT NULL,
    FOREIGN KEY (programa_id) REFERENCES programa(id),
    UNIQUE (email)
) AUTO_INCREMENT = 60000;

CREATE TABLE IF NOT EXISTS inscripcion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    curso_id INT,
    año INT NOT NULL,
    semestre INT NOT NULL,
    estudiante_id INT,
    FOREIGN KEY (curso_id) REFERENCES curso(id),
    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id)
) AUTO_INCREMENT = 35000;

CREATE TABLE IF NOT EXISTS cursoprofesor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    profesor_id INT,
    año INT NOT NULL,
    semestre INT NOT NULL,
    curso_id INT,
    FOREIGN KEY (profesor_id) REFERENCES persona(id),
    FOREIGN KEY (curso_id) REFERENCES curso(id)
) AUTO_INCREMENT = 1;
