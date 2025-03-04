package Modelos;

public class Profesor extends Persona {
    private String tipoContrato;

    public Profesor(double id, String nombre, String apellidos, String email, String tipoContrato) {
        super(id, nombre, apellidos, email);
        this.tipoContrato = tipoContrato;
    }
    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "tipoContrato='" + tipoContrato + '\'' +
                '}';
    }
}
