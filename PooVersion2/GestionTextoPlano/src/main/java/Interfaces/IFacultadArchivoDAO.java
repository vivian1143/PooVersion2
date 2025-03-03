package Interfaces;

import Modelos.Facultad;
import java.util.List;

public interface IFacultadArchivoDAO {
    void addFacultad(Facultad facultad);
    Facultad getFacultadById(double id);
    List<Facultad> getAllFacultades();
    void updateFacultad(Facultad facultad);
    void deleteFacultad(double id);
}
