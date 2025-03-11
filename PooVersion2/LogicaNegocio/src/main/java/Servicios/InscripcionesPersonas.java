package Servicios;

import DAO.ProfesorDAO;
import Documentos.ProfesorArchivoDAO;
import Modelos.Profesor;
import DAO.ValidationException;
import javax.management.InvalidAttributeValueException;

public class InscripcionesPersonas {
    private ProfesorDAO profesorDAO;
    private ProfesorArchivoDAO profesorArchivoDAO;

    public InscripcionesPersonas() {
        this.profesorDAO = new ProfesorDAO();
        this.profesorArchivoDAO = new ProfesorArchivoDAO();
    }

    public void addProfesor(String nombre, String apellidos, String email, String tipoContrato) throws ValidationException {
        try {
            Profesor profesor = new Profesor(null, nombre, apellidos, email, tipoContrato);
            profesorDAO.addProfesor(profesor);
            profesorArchivoDAO.addProfesor(profesor);
        } catch (InvalidAttributeValueException e) {
            // Manejar la excepción aquí, por ejemplo, registrando el error o lanzando una excepción personalizada
            throw new ValidationException("Invalid attribute value: " + e.getMessage());
        }
    }
}