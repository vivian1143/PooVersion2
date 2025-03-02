package Interfaces;

import Modelos.Facultad;
import java.util.List;

public interface IFacultadDAO {
    void addFacultad(Facultad facultad);
    Facultad getFacultadById(int id);
    List<Facultad> getAllFacultades();
    void updateFacultad(Facultad facultad);
    void deleteFacultad(int id);
}