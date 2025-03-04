package IGU;

import Servicios.InscripcionEstudiantes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InscripcionEstudianteIGU extends JPanel {
    private InscripcionEstudiantes inscripcionEstudiantes;

    public InscripcionEstudianteIGU() {
        inscripcionEstudiantes = new InscripcionEstudiantes();
        setLayout(new GridLayout(7, 2, 10, 10));

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

        add(new JLabel("Estado activo:"));
        JCheckBox activoCheck = new JCheckBox();
        add(activoCheck);

        add(new JLabel("Promedio:"));
        JTextField promedioField = new JTextField();
        add(promedioField);

        JButton inscribirBtn = new JButton("Inscribir");
        add(inscribirBtn);

        inscribirBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String apellidos = apellidosField.getText();
                String email = emailField.getText();
                int codigo = Integer.parseInt(codigoField.getText());
                boolean activo = activoCheck.isSelected();
                double promedio = Double.parseDouble(promedioField.getText());

                try {
                    inscripcionEstudiantes.addEstudiante(nombre, apellidos, email, codigo, activo, promedio);
                    JOptionPane.showMessageDialog(null, "Estudiante inscrito exitosamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al inscribir estudiante: " + ex.getMessage());
                }
            }
        });
    }
}