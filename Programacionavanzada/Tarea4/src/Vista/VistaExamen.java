package Vista;

import javax.swing.*;
import java.awt.*;

public class VistaExamen extends JFrame {
    public JLabel lblEnunciado = new JLabel("Cargue un archivo CSV para iniciar");
    public JRadioButton[] rbOpciones = new JRadioButton[4];
    public ButtonGroup grupoOpciones = new ButtonGroup(); 
    public JButton btnSiguiente = new JButton("Siguiente");
    public JMenuItem itemAbrir = new JMenuItem("Abrir");
    public JMenu menuOpciones = new JMenu("Opciones"); 

    public VistaExamen() {
        setTitle("Examen OOP - MVC");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1));

        JMenuBar mb = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        menuArchivo.add(itemAbrir);
        mb.add(menuArchivo);
        mb.add(menuOpciones);
        setJMenuBar(mb);

        add(lblEnunciado);
        for (int i = 0; i < 4; i++) {
            rbOpciones[i] = new JRadioButton();
            grupoOpciones.add(rbOpciones[i]);
            add(rbOpciones[i]);
        }
        add(btnSiguiente);
    }
}