-- 1. Crear la base de datos (opcional, si no existe)
CREATE DATABASE IF NOT EXISTS academia;
USE academia;

-- 2. Crear tabla Persona
CREATE TABLE IF NOT EXISTS persona (
  idPersona INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL,
  apellido VARCHAR(50) NOT NULL,
  dni VARCHAR(20),
  tipoPersona ENUM('ESTUDIANTE','PROFESOR') NOT NULL
);

-- 3. Crear tabla Estudiante
--    (su PK es la misma que Persona para simular herencia,
--     o podrías tener un ID separado si prefieres).
CREATE TABLE IF NOT EXISTS estudiante (
  idEstudiante INT PRIMARY KEY,
  codigoEstudiante VARCHAR(20) NOT NULL,
  FOREIGN KEY (idEstudiante) REFERENCES persona(idPersona)
);

-- 4. Crear tabla Profesor
CREATE TABLE IF NOT EXISTS profesor (
  idProfesor INT PRIMARY KEY,
  especialidad VARCHAR(100),
  FOREIGN KEY (idProfesor) REFERENCES persona(idPersona)
);

-- 5. Crear tabla Facultad
CREATE TABLE IF NOT EXISTS facultad (
  idFacultad INT AUTO_INCREMENT PRIMARY KEY,
  nombreFacultad VARCHAR(100) NOT NULL
);

-- 6. Crear tabla Programa
CREATE TABLE IF NOT EXISTS programa (
  idPrograma INT AUTO_INCREMENT PRIMARY KEY,
  nombrePrograma VARCHAR(100) NOT NULL,
  idFacultad INT NOT NULL,
  FOREIGN KEY (idFacultad) REFERENCES facultad(idFacultad)
);

-- 7. Crear tabla Curso
CREATE TABLE IF NOT EXISTS curso (
  idCurso INT AUTO_INCREMENT PRIMARY KEY,
  nombreCurso VARCHAR(100) NOT NULL,
  codigoCurso VARCHAR(20) NOT NULL,
  idPrograma INT NOT NULL,
  FOREIGN KEY (idPrograma) REFERENCES programa(idPrograma)
);

-- 8. Tabla intermedia para CursoProfesor (muchos a muchos)
CREATE TABLE IF NOT EXISTS curso_profesor (
  idCurso INT NOT NULL,
  idProfesor INT NOT NULL,
  PRIMARY KEY (idCurso, idProfesor),
  FOREIGN KEY (idCurso) REFERENCES curso(idCurso),
  FOREIGN KEY (idProfesor) REFERENCES profesor(idProfesor)
);

-- 9. Tabla Inscripcion (relación entre Estudiante y Curso, por ejemplo)
CREATE TABLE IF NOT EXISTS inscripcion (
  idInscripcion INT AUTO_INCREMENT PRIMARY KEY,
  fechaInscripcion DATE NOT NULL,
  idEstudiante INT NOT NULL,
  idCurso INT NOT NULL,
  FOREIGN KEY (idEstudiante) REFERENCES estudiante(idEstudiante),
  FOREIGN KEY (idCurso) REFERENCES curso(idCurso)
);