package Interfaces;

import Modelos.Programa;
import DAO.ValidationException;
import java.util.List;

public interface IProgramaDAO {
    void addPrograma(Programa programa) throws ValidationException;
    Programa getProgramaById(int id);
    List<Programa> getAllProgramas();
    void updatePrograma(Programa programa) throws ValidationException;
    void deletePrograma(int id);
}