package Documentos;

import Modelos.Profesor;
import Interfaces.IProfesorArchivoDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorArchivoDAO implements IProfesorArchivoDAO{
    private static final String FILE_PATH = "profesores.txt";

    @Override
    public void addProfesor(Profesor profesor) {
        List<Profesor> lista = getAllProfesores();
        lista.add(profesor);
        saveAllProfesores(lista);
    }

    @Override
    public Profesor getProfesorById(double id) {
        for (Profesor p : getAllProfesores()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Profesor> getAllProfesores() {
        List<Profesor> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Profesor p = new Profesor(Double.parseDouble(data[0]), data[1], data[2]);
                lista.add(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void updateProfesor(Profesor profesor) {
        List<Profesor> lista = getAllProfesores();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == profesor.getId()) {
                lista.set(i, profesor);
                break;
            }
        }
        saveAllProfesores(lista);
    }

    @Override
    public void deleteProfesor(double id) {
        List<Profesor> lista = getAllProfesores();
        lista.removeIf(p -> p.getId() == id);
        saveAllProfesores(lista);
    }

    private void saveAllProfesores(List<Profesor> lista) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (Profesor p : lista) {
                writer.write(p.getId() + "," + p.getNombre() + "," + p.getEspecialidad());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
