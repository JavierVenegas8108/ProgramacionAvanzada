package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VRubrica extends JPanel {

    public JTable tablaRubrica;
    public DefaultTableModel modeloTabla;
    public JButton btnAgregarFila, btnEliminarFila;

    public VRubrica() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] columnas = {
            "Nombre del Alumno", 
            "Criterio 1 (20%)", 
            "Criterio 2 (30%)", 
            "Criterio 3 (50%)", 
            "Promedio Final"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 4;
            }
        };

        tablaRubrica = new JTable(modeloTabla);
        configurarRenderizadorColor(); 

        JScrollPane scrollPane = new JScrollPane(tablaRubrica);
        
        JPanel panelBotonesTab = new JPanel(new GridLayout(2, 1, 5, 5));
        btnAgregarFila = new JButton("＋ Alumno");
        btnEliminarFila = new JButton("－ Eliminar");
        
        panelBotonesTab.add(btnAgregarFila);
        panelBotonesTab.add(btnEliminarFila);

       
        JPanel contenedorBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contenedorBotones.add(panelBotonesTab);

        add(new JLabel("Evaluación por Atributos de Egreso (Rúbrica)", JLabel.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(contenedorBotones, BorderLayout.EAST);
    }

    
    private void configurarRenderizadorColor() {
        tablaRubrica.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, 
                    boolean isSelected, boolean hasFocus, int row, int column) {
                
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (value != null) {
                    try {
                        double nota = Double.parseDouble(value.toString());
                        if (nota < 70) {
                            c.setBackground(new Color(255, 200, 200));
                            c.setForeground(Color.RED);
                        } else {
                            c.setBackground(Color.WHITE);
                            c.setForeground(Color.BLACK);
                        }
                    } catch (NumberFormatException e) {
                        c.setBackground(Color.WHITE);
                    }
                }
                return c;
            }
        });
    }
}