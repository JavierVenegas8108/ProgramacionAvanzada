package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import Modelo.Tickets; 

public class VistaPuntoVenta extends JInternalFrame {
    public JTextField txtFolio, txtTotal, txtBuscarId;
    public JButton btnAgregar, btnFinalizar, btnEliminar;
    public JTable tablaVenta;
    public DefaultTableModel modeloVenta;

    public VistaPuntoVenta() {
        super("Punto de Venta", true, true, true, true);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel pNorte = new JPanel();
        pNorte.add(new JLabel("Folio:"));
        txtFolio = new JTextField(5);
        txtFolio.setText("1001"); 
        pNorte.add(txtFolio);
        
        pNorte.add(new JLabel("ID Producto:"));
        txtBuscarId = new JTextField(10);
        pNorte.add(txtBuscarId);
        btnAgregar = new JButton("Añadir");
        pNorte.add(btnAgregar);
        add(pNorte, BorderLayout.NORTH);

        modeloVenta = new DefaultTableModel(new String[]{"ID", "Nombre", "Precio", "Cant", "Subtotal"}, 0);
        tablaVenta = new JTable(modeloVenta);
        add(new JScrollPane(tablaVenta), BorderLayout.CENTER);

        JPanel pSur = new JPanel();
        pSur.add(new JLabel("TOTAL: $"));
        txtTotal = new JTextField(8);
        txtTotal.setEditable(false);
        pSur.add(txtTotal);
        
        btnFinalizar = new JButton("Finalizar Venta");
        pSur.add(btnFinalizar);
        add(pSur, BorderLayout.SOUTH);
    }

    public Tickets getTicketGenerado() {
        if (txtFolio.getText().isEmpty() || txtTotal.getText().isEmpty()) {
            return null;
        }
        
        Tickets t = new Tickets();
        t.setFolio(txtFolio.getText());
        try {
            t.setTotal(Double.parseDouble(txtTotal.getText()));
        } catch (Exception e) {
            t.setTotal(0.0);
        }
        t.setFecha(new java.util.Date().toString());
        
        return t;
    }
}