package Interfaces;

import Modelos.Programa;
import java.util.List;

public interface IProgramaDAO {
    void addPrograma(Programa programa);
    Programa getProgramaById(int id);
    List<Programa> getAllProgramas();
    void updatePrograma(Programa programa);
    void deletePrograma(int id);
}
