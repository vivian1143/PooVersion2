-- 1. Crear la base de datos (opcional, si no existe)
CREATE DATABASE IF NOT EXISTS academia;
USE academia;

-- 2. Crear tabla Persona
CREATE TABLE IF NOT EXISTS persona (
  idPersona INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL,
  apellidos VARCHAR(50) NOT NULL,
  email VARCHAR(100)
);

-- 3. Crear tabla Estudiante
CREATE TABLE IF NOT EXISTS estudiante (
  idEstudiante INT PRIMARY KEY,
  codigo DOUBLE NOT NULL,
  programa_id INT,
  activo BOOLEAN NOT NULL,
  promedio DOUBLE,
  FOREIGN KEY (idEstudiante) REFERENCES persona(idPersona)
);

-- 4. Crear tabla Profesor
CREATE TABLE IF NOT EXISTS profesor (
  idProfesor INT PRIMARY KEY,
  tipoContrato VARCHAR(50),
  FOREIGN KEY (idProfesor) REFERENCES persona(idPersona)
);

-- 5. Crear tabla Facultad
CREATE TABLE IF NOT EXISTS facultad (
  idFacultad INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  decano_id INT,
  FOREIGN KEY (decano_id) REFERENCES persona(idPersona)
);

-- 6. Crear tabla Programa
CREATE TABLE IF NOT EXISTS programa (
  idPrograma INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  duracion DOUBLE NOT NULL,
  registro DATE,
  facultad_id INT,
  FOREIGN KEY (facultad_id) REFERENCES facultad(idFacultad)
);

-- 7. Crear tabla Curso
CREATE TABLE IF NOT EXISTS curso (
  idCurso INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  programa_id INT,
  activo BOOLEAN NOT NULL,
  FOREIGN KEY (programa_id) REFERENCES programa(idPrograma)
);

-- 8. Crear tabla CursoProfesor (relación muchos a muchos)
CREATE TABLE IF NOT EXISTS curso_profesor (
  idCurso INT NOT NULL,
  idProfesor INT NOT NULL,
  año INT NOT NULL,
  semestre INT NOT NULL,
  PRIMARY KEY (idCurso, idProfesor),
  FOREIGN KEY (idCurso) REFERENCES curso(idCurso),
  FOREIGN KEY (idProfesor) REFERENCES profesor(idProfesor)
);

-- 9. Crear tabla Inscripcion (relación entre Estudiante y Curso)
CREATE TABLE IF NOT EXISTS inscripcion (
  idInscripcion INT AUTO_INCREMENT PRIMARY KEY,
  curso_id INT NOT NULL,
  año INT NOT NULL,
  semestre INT NOT NULL,
  estudiante_id INT NOT NULL,
  FOREIGN KEY (curso_id) REFERENCES curso(idCurso),
  FOREIGN KEY (estudiante_id) REFERENCES estudiante(idEstudiante)
);