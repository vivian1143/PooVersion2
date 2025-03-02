package Modelos;

public class Estudiante {
    private double codigo;
    private Programa programa;
    private boolean activo;
    private double promedio;

    public Estudiante(double codigo, Programa programa, boolean activo, double promedio) {
        this.codigo = codigo;
        this.programa = programa;
        this.activo = activo;
        this.promedio = promedio;
    }

    public double getCodigo() {
        return codigo;
    }

    public void setCodigo(double codigo) {
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
