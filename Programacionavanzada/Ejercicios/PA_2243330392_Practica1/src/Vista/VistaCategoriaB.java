package Vista;

import javax.swing.*;
import java.awt.*;

public class VistaCategoriaB extends JFrame {
    public JTextField Tid, Tcategoria;
    public JButton Bagregar, Beliminar, Bsalir;
    public JTextArea Tareacategoria;

    public VistaCategoriaB() {
        super("Administracion de Categorias");
        setSize(335, 305);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel labelId = new JLabel("ID:");
        labelId.setBounds(10, 10, 30, 20);
        add(labelId);

        Tid = new JTextField();
        Tid.setBounds(100, 10, 150, 20);
        add(Tid);

        JLabel labelCategoria = new JLabel("Categoria:");
        labelCategoria.setBounds(10, 40, 80, 20);
        add(labelCategoria);

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
        Tareacategoria.setFont(new Font("Monospaced", Font.PLAIN, 12));
        Tareacategoria.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(Tareacategoria);
        scrollPane.setBounds(10, 105, 300, 150);
        add(scrollPane);

        setLocationRelativeTo(null);
    }
}