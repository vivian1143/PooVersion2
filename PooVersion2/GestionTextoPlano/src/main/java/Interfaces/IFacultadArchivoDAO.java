package Interfaces;

import Modelos.Facultad;
import java.util.List;

public interface IFacultadArchivoDAO {
    void addFacultad(Facultad facultad);
    Facultad getFacultadById(Integer id);
    List<Facultad> getAllFacultades();
    void updateFacultad(Facultad facultad);
    void deleteFacultad(Integer id);
}