package Vista;

import javax.swing.*;
import java.awt.*;

public class VistaCategoria extends JFrame {
    public JTextField Tid, Tcategoria;
    public JButton Bagregar, Beliminar, Bsalir;
    public JTextArea Tareacategoria;

    public VistaCategoria() {
        super("Administración de Categorías (MVC)");
        setSize(335, 305);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel l1 = new JLabel("ID:");
        l1.setBounds(10, 10, 30, 20);
        add(l1);

        Tid = new JTextField();
        Tid.setBounds(100, 10, 150, 20);
        add(Tid);

        JLabel l2 = new JLabel("Categoría:");
        l2.setBounds(10, 40, 80, 20);
        add(l2);

        Tcategoria = new JTextField();
        Tcategoria.setBounds(100, 40, 150, 20);
        add(Tcategoria);

        Bagregar = new JButton("Agregar");
        Bagregar.setBounds(10, 70, 90, 25);
        add(Bagregar);

        Beliminar = new JButton("Eliminar");
        Beliminar.setBounds(110, 70, 90, 25);
        add(Beliminar);

        Bsalir = new JButton("Salir");
        Bsalir.setBounds(210, 70, 90, 25);
        add(Bsalir);

        Tareacategoria = new JTextArea();
        Tareacategoria.setEditable(false);
        JScrollPane scroll = new JScrollPane(Tareacategoria);
        scroll.setBounds(10, 105, 300, 150);
        add(scroll);
        Tid.setEditable(true);
        Tcategoria.setEditable(true);
        setLocationRelativeTo(null);
    }
}