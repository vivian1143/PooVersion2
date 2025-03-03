package Documentos;

import Modelos.Curso;
import Interfaces.ICursoArchivoDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CursoArchivoDAO implements ICursoArchivoDAO {
    private static final String FILE_PATH = "cursos.txt";

    @Override
    public void addCurso(Curso curso) {
        List<Curso> cursos = getAllCursos();
        cursos.add(curso);
        saveAllCursos(cursos);
    }

    @Override
    public Curso getCursoById(double id) {
        List<Curso> cursos = getAllCursos();
        for (Curso curso : cursos) {
            if (curso.getId() == id) {
                return curso;
            }
        }
        return null;
    }

    @Override
    public List<Curso> getAllCursos() {
        List<Curso> cursos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Curso curso = new Curso(
                        Double.parseDouble(data[0]),
                        data[1],
                        data[2]
                );
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
            if (cursos.get(i).getId() == curso.getId()) {
                cursos.set(i, curso);
                break;
            }
        }
        saveAllCursos(cursos);
    }

    @Override
    public void deleteCurso(double id) {
        List<Curso> cursos = getAllCursos();
        cursos.removeIf(curso -> curso.getId() == id);
        saveAllCursos(cursos);
    }

    private void saveAllCursos(List<Curso> cursos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (Curso curso : cursos) {
                writer.write(curso.getId() + "," + curso.getNombre() + "," + curso.getDescripcion());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}