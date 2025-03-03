package Servicios;

import DAO.InscripcionDAO;
import Modelos.Inscripcion;
import DAO.ValidationException;
import java.util.List;

public class InscripcionesPersonas {
    private InscripcionDAO inscripcionDAO;

    public InscripcionesPersonas() {
        this.inscripcionDAO = new InscripcionDAO();
    }

    public void addInscripcion(Inscripcion inscripcion) throws ValidationException {
        inscripcionDAO.addInscripcion(inscripcion);
    }

    public Inscripcion getInscripcionById(int id) {
        return inscripcionDAO.getInscripcionById(id);
    }

    public List<Inscripcion> getAllInscripciones() {
        return inscripcionDAO.getAllInscripciones();
    }

    public void updateInscripcion(Inscripcion inscripcion) throws ValidationException {
        inscripcionDAO.updateInscripcion(inscripcion);
    }

    public void deleteInscripcion(int id) {
        inscripcionDAO.deleteInscripcion(id);
    }
}