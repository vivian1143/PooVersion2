package Documentos;

import Modelos.Curso;
import Modelos.Programa;
import Interfaces.ICursoArchivoDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CursoArchivoDAO implements ICursoArchivoDAO {
    private static final String FILE_PATH = "/PooVersion2/GestionTextoPlano/src/main/resources/cursos.txt";

    @Override
    public void addCurso(Curso curso) {
        List<Curso> cursos = getAllCursos();
        cursos.add(curso);
        saveAllCursos(cursos);
    }

    @Override
    public Curso getCursoById(Integer id) {
        List<Curso> cursos = getAllCursos();
        for (Curso curso : cursos) {
            if (curso.getId().equals(id)) {
                return curso;
            }
        }
        return null;
    }

    @Override
    public List<Curso> getAllCursos() {
        List<Curso> cursos = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Integer id = Integer.parseInt(data[0]);
                String nombre = data[1];

                // Create a Programa object (adjust according to your model)
                Programa programa = new Programa(0, data[2], 0, new Date(), null); // Adjust as needed

                boolean activo = Boolean.parseBoolean(data[3]);

                Curso curso = new Curso(id, nombre, programa, activo);
                cursos.add(curso);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    @Override
    public void updateCurso(Curso curso) {
        List<Curso> cursos = getAllCursos();
        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getId().equals(curso.getId())) {
                cursos.set(i, curso);
                break;
            }
        }
        saveAllCursos(cursos);
    }

    @Override
    public void deleteCurso(Integer id) {
        List<Curso> cursos = getAllCursos();
        cursos.removeIf(curso -> curso.getId().equals(id));
        saveAllCursos(cursos);
    }

    private void saveAllCursos(List<Curso> cursos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (Curso curso : cursos) {
                writer.write(curso.getId() + "," + curso.getNombre() + "," + curso.getPrograma().getNombre() + "," + curso.isActivo());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}