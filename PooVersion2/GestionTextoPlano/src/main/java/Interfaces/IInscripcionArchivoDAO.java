package Interfaces;

import Modelos.Inscripcion;
import java.util.List;

public interface IInscripcionArchivoDAO {
    void addInscripcion(Inscripcion inscripcion);
    Inscripcion getInscripcionById(Integer id);
    List<Inscripcion> getAllInscripciones();
    void updateInscripcion(Inscripcion inscripcion);
    void deleteInscripcion(Integer id);
}