package Vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaPuntoVenta extends JInternalFrame {
    public JTextField txtIdVenta, txtCant;
    public JButton btnAddCar, btnCobrar;
    public JTable tabla;
    public DefaultTableModel modelo;

    public VistaPuntoVenta() {
        super("Ventas", true, true, true, true);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel norte = new JPanel();
        norte.add(new JLabel("ID:")); txtIdVenta = new JTextField(5); norte.add(txtIdVenta);
        norte.add(new JLabel("Cant:")); txtCant = new JTextField(3); norte.add(txtCant);
        btnAddCar = new JButton("Agregar"); norte.add(btnAddCar);
        add(norte, BorderLayout.NORTH);

        modelo = new DefaultTableModel(new String[]{"ID","Nom","Cant","Subtotal"}, 0);
        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnCobrar = new JButton("Cobrar y Ticket TXT");
        add(btnCobrar, BorderLayout.SOUTH);
    }
}