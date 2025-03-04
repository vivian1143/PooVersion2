package Documentos;

import Modelos.Programa;
import Interfaces.IProgramaArchivoDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramaArchivoDAO implements IProgramaArchivoDAO {
    private static final String FILE_PATH = "programas.txt";

    @Override
    public void addPrograma(Programa programa) {
        List<Programa> lista = getAllProgramas();
        lista.add(programa);
        saveAllProgramas(lista);
    }

    @Override
    public Programa getProgramaById(double id) {
        for (Programa p : getAllProgramas()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Programa> getAllProgramas() {
        List<Programa> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    Double id = Double.parseDouble(data[0]);
                    String nombre = data[1];
                    double duracion = Double.parseDouble(data[2]);
                    Date registro = new Date(data[3]);
                    Facultad facultad = new Facultad(data[4]);

                    Programa p = new Programa(id, nombre, duracion, registro, facultad);
                    lista.add(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void updatePrograma(Programa programa) {
        List<Programa> lista = getAllProgramas();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == programa.getId()) {
                lista.set(i, programa);
                break;
            }
        }
        saveAllProgramas(lista);
    }

    @Override
    public void deletePrograma(double id) {
        List<Programa> lista = getAllProgramas();
        lista.removeIf(p -> p.getId() == id);
        saveAllProgramas(lista);
    }

    private void saveAllProgramas(List<Programa> lista) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (Programa p : lista) {
                writer.write(p.getId() + "," + p.getNombre() + "," + p.getFacultad());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
