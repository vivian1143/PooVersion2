package IGU;

import javax.swing.*;
import java.awt.*;

public class InscripcionEstudianteIGU extends JPanel {
    public InscripcionEstudianteIGU() {
        setLayout(new GridLayout(8, 2, 10, 10));

        add(new JLabel("Nombre:"));
        JTextField nombreField = new JTextField();
        add(nombreField);

        add(new JLabel("Apellidos:"));
        JTextField apellidosField = new JTextField();
        add(apellidosField);

        add(new JLabel("Email:"));
        JTextField emailField = new JTextField();
        add(emailField);

        add(new JLabel("Código de estudiante:"));
        JTextField codigoField = new JTextField();
        add(codigoField);

        add(new JLabel("Programa académico:"));
        JComboBox<String> programaBox = new JComboBox<>(new String[]{"Ingeniería", "Medicina", "Derecho"});
        add(programaBox);

        add(new JLabel("Estado activo:"));
        JCheckBox activoCheck = new JCheckBox();
        add(activoCheck);

        add(new JLabel("Promedio:"));
        JTextField promedioField = new JTextField();
        add(promedioField);

        JButton inscribirBtn = new JButton("Inscribir");
        add(inscribirBtn);
    }
}
