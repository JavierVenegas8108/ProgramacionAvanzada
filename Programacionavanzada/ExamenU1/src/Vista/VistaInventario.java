package Vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaInventario extends JInternalFrame {
    public JTextField txtId, txtNom, txtCat, txtPre, txtStock;
    public JButton btnAdd, btnDel, btnUpd, btnSearch;
    public JTable tabla;
    public DefaultTableModel modelo;

    public VistaInventario() {
        super("Gestión", true, true, true, true);
        setSize(800, 500);
        setLayout(new BorderLayout());

        JPanel pForm = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5,5,5,5); g.fill = GridBagConstraints.HORIZONTAL;

        g.gridx=0; g.gridy=0; pForm.add(new JLabel("ID:"), g);
        txtId = new JTextField(10); g.gridx=1; pForm.add(txtId, g);
        g.gridx=0; g.gridy=1; pForm.add(new JLabel("Nombre:"), g);
        txtNom = new JTextField(10); g.gridx=1; pForm.add(txtNom, g);
        g.gridx=0; g.gridy=2; pForm.add(new JLabel("Precio:"), g);
        txtPre = new JTextField(10); g.gridx=1; pForm.add(txtPre, g);
        g.gridx=0; g.gridy=3; pForm.add(new JLabel("Stock:"), g);
        txtStock = new JTextField(10); g.gridx=1; pForm.add(txtStock, g);

        add(pForm, BorderLayout.WEST);
        modelo = new DefaultTableModel(new String[]{"ID","Nom","Pre","Stock"}, 0);
        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel pBtn = new JPanel();
        btnAdd = new JButton("Guardar"); btnSearch = new JButton("Buscar");
        btnDel = new JButton("Eliminar"); btnUpd = new JButton("Actualizar");
        pBtn.add(btnAdd); pBtn.add(btnSearch); pBtn.add(btnUpd); pBtn.add(btnDel);
        add(pBtn, BorderLayout.SOUTH);
    }
}