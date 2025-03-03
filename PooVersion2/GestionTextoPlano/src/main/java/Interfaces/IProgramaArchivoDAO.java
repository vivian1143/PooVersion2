package Interfaces;

import Modelos.Programa;
import java.util.List;

public interface IProgramaArchivoDAO {
    void addPrograma(Programa programa);
    Programa getProgramaById(double id);
    List<Programa> getAllProgramas();
    void updatePrograma(Programa programa);
    void deletePrograma(double id);
}
