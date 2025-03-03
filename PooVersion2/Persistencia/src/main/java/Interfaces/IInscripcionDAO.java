package Interfaces;

import Modelos.Inscripcion;
import DAO.ValidationException;
import java.util.List;

public interface IInscripcionDAO {
    void addInscripcion(Inscripcion inscripcion) throws ValidationException;
    Inscripcion getInscripcionById(int id);
    List<Inscripcion> getAllInscripciones();
    void updateInscripcion(Inscripcion inscripcion) throws ValidationException;
    void deleteInscripcion(int id);
}