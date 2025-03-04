package Documentos;

import Modelos.Estudiante;
import Interfaces.IEstudianteArchivoDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteArchivoDAO implements IEstudianteArchivoDAO {
    private static final String FILE_PATH = "estudiantes.txt";

    @Override
    public void addEstudiante(Estudiante estudiante) {
        List<Estudiante> lista = getAllEstudiantes();
        lista.add(estudiante);
        saveAllEstudiantes(lista);
    }

    @Override
    public Estudiante getEstudianteById(double id) {
        List<Estudiante> lista = getAllEstudiantes();
        for (Estudiante e : lista) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    @Override
    public List<Estudiante> getAllEstudiantes() {
        List<Estudiante> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Programa programa = new Programa(
                        Double.parseDouble(data[5]), // ID del programa
                        data[6], // Nombre del programa
                        Double.parseDouble(data[7]), // Duración
                        new Date(), // Fecha de registro (aquí puedes leerla si está en el archivo)
                        null // Puedes agregar aquí la Facultad si la tienes en el archivo
                );

                // Crear el estudiante con todos los datos correctos
                Estudiante e = new Estudiante(
                        Double.parseDouble(data[0]), // ID
                        data[1], // Nombre
                        data[2], // Apellidos
                        data[3], // Email
                        Double.parseDouble(data[4]), // Código
                        programa, // Programa como objeto, no String
                        Boolean.parseBoolean(data[8]), // Activo
                        Double.parseDouble(data[9]) // Promedio
                );

                lista.add(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void updateEstudiante(Estudiante estudiante) {
        List<Estudiante> lista = getAllEstudiantes();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == estudiante.getId()) {
                lista.set(i, estudiante);
                break;
            }
        }
        saveAllEstudiantes(lista);
    }

    @Override
    public void deleteEstudiante(double id) {
        List<Estudiante> lista = getAllEstudiantes();
        lista.removeIf(e -> e.getId() == id);
        saveAllEstudiantes(lista);
    }

    private void saveAllEstudiantes(List<Estudiante> lista) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (Estudiante e : lista) {
                writer.write(e.getId() + "," + e.getNombre() + "," + e.getApellidos() + "," + e.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
