package Interfaces;

import Modelos.Profesor;
import java.util.List;

public interface IProfesorArchivoDAO {
    void addProfesor(Profesor profesor);
    Profesor getProfesorById(Integer id);
    List<Profesor> getAllProfesores();
    void updateProfesor(Profesor profesor);
    void deleteProfesor(Integer id);
}