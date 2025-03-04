package IGU;

import javax.swing.*;
import java.awt.*;

public class InscripcionProfesorIGU extends JPanel {
    public InscripcionProfesorIGU() {
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Nombre:"));
        JTextField nombreField = new JTextField();
        add(nombreField);

        add(new JLabel("Apellidos:"));
        JTextField apellidosField = new JTextField();
        add(apellidosField);

        add(new JLabel("Email:"));
        JTextField emailField = new JTextField();
        add(emailField);

        add(new JLabel("Tipo de contrato:"));
        JComboBox<String> contratoBox = new JComboBox<>(new String[]{"Tiempo Completo", "Medio Tiempo", "Catedrático"});
        add(contratoBox);

        JButton inscribirBtn = new JButton("Inscribir");
        add(inscribirBtn);
    }
}
