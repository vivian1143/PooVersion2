package Documentos;

import Modelos.Inscripcion;
import Interfaces.IInscripcionArchivoDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionArchivoDAO implements IIncripcionArchivoDAO {
    private static final String FILE_PATH = "inscripciones.txt";

    @Override
    public void addInscripcion(Inscripcion inscripcion) {
        List<Inscripcion> lista = getAllInscripciones();
        lista.add(inscripcion);
        saveAllInscripciones(lista);
    }

    @Override
    public Inscripcion getInscripcionById(double id) {
        for (Inscripcion i : getAllInscripciones()) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    @Override
    public List<Inscripcion> getAllInscripciones() {
        List<Inscripcion> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Inscripcion i = new Inscripcion(Double.parseDouble(data[0]), data[1], data[2], data[3]);
                lista.add(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void updateInscripcion(Inscripcion inscripcion) {
        List<Inscripcion> lista = getAllInscripciones();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == inscripcion.getId()) {
                lista.set(i, inscripcion);
                break;
            }
        }
        saveAllInscripciones(lista);
    }

    @Override
    public void deleteInscripcion(double id) {
        List<Inscripcion> lista = getAllInscripciones();
        lista.removeIf(i -> i.getId() == id);
        saveAllInscripciones(lista);
    }

    private void saveAllInscripciones(List<Inscripcion> lista) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (Inscripcion i : lista) {
                writer.write(i.getId() + "," + i.getCurso() + "," + i.getEstudiante() + "," + i.getFecha());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
