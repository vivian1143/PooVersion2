package Validaciones;

import Modelos.Curso;
import Modelos.Estudiante;
import Modelos.Profesor;
import Modelos.Programa;
import javax.management.InvalidAttributeValueException;

public class Validaciones {

    public static void validarCurso(Curso curso) throws InvalidAttributeValueException {
        if (curso.getNombre() == null || curso.getNombre().isEmpty()) {
            throw new InvalidAttributeValueException("El nombre del curso no puede estar vacío.");
        }
        if (curso.getPrograma() == null || curso.getPrograma().getId() == null) {
            throw new InvalidAttributeValueException("El curso debe estar asociado a un programa.");
        }
    }

    public static void validarEstudiante(Estudiante estudiante) throws InvalidAttributeValueException {
        if (estudiante.getCodigo() <= 0) {
            throw new InvalidAttributeValueException("El código del estudiante debe ser un número positivo.");
        }
        if (estudiante.getPrograma() == null || estudiante.getPrograma().getId() == null) {
            throw new InvalidAttributeValueException("El estudiante debe estar asociado a un programa.");
        }
        if (estudiante.getPromedio() < 0.0 || estudiante.getPromedio() > 5.0) {
            throw new InvalidAttributeValueException("El promedio debe estar entre 0.0 y 5.0.");
        }
    }

    public static void validarProfesor(Profesor profesor) throws InvalidAttributeValueException {
        if (profesor.getNombre() == null || profesor.getNombre().isEmpty()) {
            throw new InvalidAttributeValueException("El nombre del profesor no puede estar vacío.");
        }
        if (profesor.getApellidos() == null || profesor.getApellidos().isEmpty()) {
            throw new InvalidAttributeValueException("Los apellidos del profesor no pueden estar vacíos.");
        }
        if (profesor.getEmail() == null || profesor.getEmail().isEmpty()) {
            throw new InvalidAttributeValueException("El email del profesor no puede estar vacío.");
        }
        if (profesor.getTipoContrato() == null || profesor.getTipoContrato().isEmpty()) {
            throw new InvalidAttributeValueException("El tipo de contrato del profesor no puede estar vacío.");
        }
    }
}
