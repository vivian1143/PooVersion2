package Documentos;

import Modelos.Facultad;
import Modelos.Persona;
import Interfaces.IFacultadArchivoDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FacultadArchivoDAO implements IFacultadArchivoDAO {
    private static final String FILE_PATH = "facultades.txt";

    @Override
    public void addFacultad(Facultad facultad) {
        List<Facultad> lista = getAllFacultades();
        lista.add(facultad);
        saveAllFacultades(lista);
    }

    @Override
    public Facultad getFacultadById(Integer id) {
        for (Facultad f : getAllFacultades()) {
            if (f.getId().equals(id)) {
                return f;
            }
        }
        return null;
    }

    @Override
    public List<Facultad> getAllFacultades() {
        List<Facultad> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Persona decano = new Persona(0, data[2], "", ""); // Solo guarda el nombre del decano temporalmente
                Facultad f = new Facultad(Integer.parseInt(data[0]), data[1], decano);

                lista.add(f);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void updateFacultad(Facultad facultad) {
        List<Facultad> lista = getAllFacultades();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().equals(facultad.getId())) {
                lista.set(i, facultad);
                break;
            }
        }
        saveAllFacultades(lista);
    }

    @Override
    public void deleteFacultad(Integer id) {
        List<Facultad> lista = getAllFacultades();
        lista.removeIf(f -> f.getId().equals(id));
        saveAllFacultades(lista);
    }

    private void saveAllFacultades(List<Facultad> lista) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (Facultad f : lista) {
                writer.write(f.getId() + "," + f.getNombre() + "," + f.getDecano().getNombre());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}