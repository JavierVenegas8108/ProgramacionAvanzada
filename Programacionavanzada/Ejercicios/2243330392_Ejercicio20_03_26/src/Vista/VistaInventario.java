package Vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaInventario extends JInternalFrame {
    public JTextField txtId, txtNom, txtCat, txtPre, txtStock;
    public JButton btnAdd, btnDel, btnUpd, btnSearch, btnExcelGral, btnExcelCat;
    public JTable tabla;
    public DefaultTableModel modelo;
    public JLabel lblImagen;

    public VistaInventario() {
        super("Gestión de Inventario", true, true, true, true);
        setSize(900, 500);
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

        lblImagen = new JLabel("Sin Imagen");
        lblImagen.setPreferredSize(new Dimension(150, 150));
        lblImagen.setBorder(BorderFactory.createTitledBorder("Vista Previa"));
        lblImagen.setHorizontalAlignment(JLabel.CENTER);
        g.gridx=0; g.gridy=4; g.gridwidth=2;
        pForm.add(lblImagen, g);

        add(pForm, BorderLayout.WEST);

        modelo = new DefaultTableModel(new String[]{"ID", "Nombre", "Precio", "Stock"}, 0);
        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel pButtons = new JPanel();
        btnAdd = new JButton("Agregar");
        btnDel = new JButton("Eliminar");
        btnUpd = new JButton("Actualizar");
        btnSearch = new JButton("Buscar");
        btnExcelGral = new JButton("Excel General");
        btnExcelCat = new JButton("Excel Categoría");

        pButtons.add(btnAdd); pButtons.add(btnDel); pButtons.add(btnUpd); 
        pButtons.add(btnSearch); pButtons.add(btnExcelGral); pButtons.add(btnExcelCat);
        
        add(pButtons, BorderLayout.SOUTH);
    }
}