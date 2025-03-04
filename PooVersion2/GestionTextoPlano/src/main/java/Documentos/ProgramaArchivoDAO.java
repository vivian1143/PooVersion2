package Documentos;

import Modelos.Programa;
import Modelos.Facultad;
import Interfaces.IProgramaArchivoDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
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
    public Programa getProgramaById(Integer id) {
        for (Programa p : getAllProgramas()) {
            if (p.getId().equals(id)) {
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
                    Integer id = Integer.parseInt(data[0]);
                    String nombre = data[1];
                    double duracion = Double.parseDouble(data[2]);
                    Date registro = new Date(Long.parseLong(data[3]));
                    Facultad facultad = new Facultad(Integer.parseInt(data[4]), "", null);

                    Programa p = new Programa(id, nombre, duracion, registro, facultad);
                    lista.add(p);
                }
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
            if (lista.get(i).getId().equals(programa.getId())) {
                lista.set(i, programa);
                break;
            }
        }
        saveAllProgramas(lista);
    }

    @Override
    public void deletePrograma(Integer id) {
        List<Programa> lista = getAllProgramas();
        lista.removeIf(p -> p.getId().equals(id));
        saveAllProgramas(lista);
    }

    private void saveAllProgramas(List<Programa> lista) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (Programa p : lista) {
                writer.write(p.getId() + "," + p.getNombre() + "," + p.getDuracion() + "," + p.getRegistro().getTime() + "," + p.getFacultad().getId());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}