package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VCotejo extends JPanel {

    public JTable tablaCotejo;
    public DefaultTableModel modeloCotejo;
    public JButton btnAgregarRequisito, btnEliminarRequisito, btnMarcarTodos;

    public VCotejo() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] columnas = {"ID", "Requisito / Indicador", "Cumple", "Observación"};

        modeloCotejo = new DefaultTableModel(columnas, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 2) return Boolean.class; 
                return super.getColumnClass(columnIndex);
            }
        };

        tablaCotejo = new JTable(modeloCotejo);
        tablaCotejo.setRowHeight(25); 
        
        modeloCotejo.addRow(new Object[]{"1", "Entrega en tiempo", false, ""});
        modeloCotejo.addRow(new Object[]{"2", "Usa estándares de código", false, ""});
        
        JScrollPane scrollPane = new JScrollPane(tablaCotejo);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnMarcarTodos = new JButton("✅ Marcar todos");
        btnAgregarRequisito = new JButton("＋ Requisito");
        btnEliminarRequisito = new JButton("－ Eliminar");
        
        panelBotones.add(btnMarcarTodos);
        panelBotones.add(btnAgregarRequisito);
        panelBotones.add(btnEliminarRequisito);

        add(new JLabel("Lista de Cotejo: Verificación de Requisitos Técnicos", JLabel.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
        btnMarcarTodos.addActionListener(e -> marcarTodosLosChecks(true));
    }

    public void marcarTodosLosChecks(boolean estado) {
        for (int i = 0; i < modeloCotejo.getRowCount(); i++) {
            modeloCotejo.setValueAt(estado, i, 2); 
        }
    }
}