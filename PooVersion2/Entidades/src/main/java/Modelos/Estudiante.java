package Modelos;

public class Estudiante extends Persona {
    private int codigo;
    private Programa programa;
    private boolean activo;
    private double promedio;

    public Estudiante(Integer id, String nombre, String apellidos, String email, int codigo, Programa programa, boolean activo, double promedio) {
        super(id, nombre, apellidos, email);
        this.codigo = codigo;
        this.programa = programa;
        this.activo = activo;
        this.promedio = promedio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "codigo=" + codigo +
                ", programa=" + programa +
                ", activo=" + activo +
                ", promedio=" + promedio +
                '}';
    }
}