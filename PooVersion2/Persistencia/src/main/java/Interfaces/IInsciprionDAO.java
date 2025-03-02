package Interfaces;

import Modelos.Inscripcion;
import java.util.List;

public interface IInscripcionDAO {
    void addInscripcion(Inscripcion inscripcion);
    Inscripcion getInscripcionById(int id);
    List<Inscripcion> getAllInscripciones();
    void updateInscripcion(Inscripcion inscripcion);
    void deleteInscripcion(int id);
}