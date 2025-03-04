CREATE DATABASE IF NOT EXISTS academia;
USE academia;

CREATE TABLE IF NOT EXISTS Persona (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    UNIQUE (email)
) AUTO_INCREMENT = 10000;

CREATE TABLE IF NOT EXISTS Facultad (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    decano_id INT,
    FOREIGN KEY (decano_id) REFERENCES Persona(id)
) AUTO_INCREMENT = 200;

CREATE TABLE IF NOT EXISTS Programa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    duracion DOUBLE NOT NULL,
    registro DATE NOT NULL,
    facultad_id INT,
    FOREIGN KEY (facultad_id) REFERENCES Facultad(id)
) AUTO_INCREMENT = 700;

CREATE TABLE IF NOT EXISTS Curso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    programa_id INT,
    activo BOOLEAN NOT NULL,
    FOREIGN KEY (programa_id) REFERENCES Programa(id)
) AUTO_INCREMENT = 4000;

CREATE TABLE IF NOT EXISTS Estudiante (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    codigo INT NOT NULL,
    programa_id INT,
    activo BOOLEAN NOT NULL,
    promedio DOUBLE NOT NULL,
    FOREIGN KEY (programa_id) REFERENCES Programa(id),
    UNIQUE (email)
) AUTO_INCREMENT = 60000;

CREATE TABLE IF NOT EXISTS Inscripcion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    curso_id INT,
    año INT NOT NULL,
    semestre INT NOT NULL,
    estudiante_id INT,
    FOREIGN KEY (curso_id) REFERENCES Curso(id),
    FOREIGN KEY (estudiante_id) REFERENCES Estudiante(id)
) AUTO_INCREMENT = 35000;

CREATE TABLE IF NOT EXISTS CursoProfesor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    profesor_id INT,
    año INT NOT NULL,
    semestre INT NOT NULL,
    curso_id INT,
    FOREIGN KEY (profesor_id) REFERENCES Persona(id),
    FOREIGN KEY (curso_id) REFERENCES Curso(id)
) AUTO_INCREMENT = 1;