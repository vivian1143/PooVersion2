package Modelos;

public class CursoProfesor {
    private int id;
    private Profesor profesor;
    private int año;
    private int semestre;
    private Curso curso;

    public CursoProfesor(int id, Profesor profesor, int año, int semestre, Curso curso) {
        this.id = id;
        this.profesor = profesor;
        this.año = año;
        this.semestre = semestre;
        this.curso = curso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "CursoProfesor{" +
                "id=" + id +
                ", profesor=" + profesor +
                ", año=" + año +
                ", semestre=" + semestre +
                ", curso=" + curso +
                '}';
    }
}