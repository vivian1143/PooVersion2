package Servicios;

import DAO.ProfesorDAO;
import Documentos.ProfesorArchivoDAO;
import Modelos.Profesor;
import DAO.ValidationException;

public class InscripcionesPersonas {
    //private ProfesorDAO profesorDAO;
    private ProfesorArchivoDAO profesorArchivoDAO;

    public InscripcionesPersonas() {
        //this.profesorDAO = new ProfesorDAO();
        this.profesorArchivoDAO = new ProfesorArchivoDAO();
    }

    public void addProfesor(String nombre, String apellidos, String email, String tipoContrato) throws ValidationException {
        Profesor profesor = new Profesor(null, nombre, apellidos, email, tipoContrato);
        //profesorDAO.addProfesor(profesor);
        profesorArchivoDAO.addProfesor(profesor);
    }
}