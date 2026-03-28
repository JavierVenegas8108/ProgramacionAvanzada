package Controlador;

import java.awt.Color;
import java.awt.Component;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Modelo.*;
import Vista.VistaGral;

public class ControladorGral {
    private VistaGral vista;
    private ControladorDatos modelo;

    public ControladorGral(VistaGral vista, ControladorDatos modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        cargarDatosDesdeExcel();
        configurarCalculosTabla();
        configurarRenderizadoRojo();
        
        this.vista.btnCargar.addActionListener(e -> consultarEvaluacion());
        this.vista.btnGuardar.addActionListener(e -> {
            guardarEvaluacion();
            generarReporteExcel();
        });
        this.vista.btnEliminar.addActionListener(e -> eliminarEvaluacion());
        this.vista.btnNuevo.addActionListener(e -> limpiarInterfaz());
        this.vista.cbAsignatura.addActionListener(e -> {
            filtrarProfesores();
            cargarAtributoEgreso();
        });
        this.vista.cbProfesor.addActionListener(e -> filtrarGrupos());
    }

    private void cargarAtributoEgreso() {
        String materiaSel = (String) vista.cbAsignatura.getSelectedItem();
        if (materiaSel == null || materiaSel.equals("Seleccione Asignatura...")) return;

        try (BufferedReader br = new BufferedReader(new FileReader("Datosbase.xlsx - AsignaturaAtributo.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (partes.length >= 3 && partes[1].trim().equalsIgnoreCase(materiaSel)) {
                    vista.panelProducto.txtObservaciones.setText("ATRIBUTO DE EGRESO: " + partes[2].replace("\"", ""));
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Nota: No se pudo cargar el atributo desde el CSV.");
        }
    }

    private void configurarCalculosTabla() {
        DefaultTableModel model = (DefaultTableModel) vista.panelRubrica.tablaRubrica.getModel();
        model.addTableModelListener(e -> {
            int col = e.getColumn();
            int row = e.getFirstRow();
            if (col >= 1 && col <= 3) {
                try {
                    double c1 = Double.parseDouble(model.getValueAt(row, 1).toString());
                    double c2 = Double.parseDouble(model.getValueAt(row, 2).toString());
                    double c3 = Double.parseDouble(model.getValueAt(row, 3).toString());
                    double promedio = (c1 + c2 + c3) / 3.0;
                    SwingUtilities.invokeLater(() -> model.setValueAt(Math.round(promedio * 100.0) / 100.0, row, 4));
                } catch (Exception ex) {}
            }
        });
    }

    private void configurarRenderizadoRojo() {
        vista.panelRubrica.tablaRubrica.getColumnModel().getColumn(4).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (value != null) {
                    try {
                        double nota = Double.parseDouble(value.toString());
                        if (nota < 7.0 && nota > 0) {
                            c.setBackground(new Color(255, 150, 150));
                            c.setForeground(Color.BLACK);
                        } else {
                            c.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
                            c.setForeground(isSelected ? table.getSelectionForeground() : Color.BLACK);
                        }
                    } catch (Exception e) {}
                }
                return c;
            }
        });
    }

    private void generarReporteExcel() {
        String id = vista.cbAsignatura.getSelectedItem() + "_" + vista.cbGrupo.getSelectedItem();
        try (Workbook wb = new XSSFWorkbook()) {
            Sheet s = wb.createSheet("Reporte");
            Row r0 = s.createRow(0); r0.createCell(0).setCellValue("REPORTE DE EVALUACIÓN SAE-AE");
            Row r1 = s.createRow(2); r1.createCell(0).setCellValue("Asignatura: " + vista.cbAsignatura.getSelectedItem());
            Row r2 = s.createRow(3); r2.createCell(0).setCellValue("Profesor: " + vista.cbProfesor.getSelectedItem());
            
            DefaultTableModel m = (DefaultTableModel) vista.panelRubrica.tablaRubrica.getModel();
            for (int i = 0; i < m.getRowCount(); i++) {
                Row r = s.createRow(6 + i);
                r.createCell(0).setCellValue(m.getValueAt(i, 0).toString());
                r.createCell(1).setCellValue(m.getValueAt(i, 4).toString());
            }
            try (FileOutputStream out = new FileOutputStream("Reporte_" + id + ".xlsx")) { wb.write(out); }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void cargarDatosDesdeExcel() {
        try (FileInputStream fis = new FileInputStream(new File("Datosbase.xlsx"));
             Workbook wb = new XSSFWorkbook(fis)) {
            Sheet s = wb.getSheetAt(0);
            Set<String> mats = new TreeSet<>();
            for (Row r : s) {
                if (r.getRowNum() == 0) continue;
                if (r.getCell(2) != null) mats.add(r.getCell(2).toString().trim());
            }
            vista.cbAsignatura.removeAllItems();
            vista.cbAsignatura.addItem("Seleccione Asignatura...");
            for (String m : mats) vista.cbAsignatura.addItem(m);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void filtrarProfesores() {
        String sel = (String) vista.cbAsignatura.getSelectedItem();
        if (sel == null || sel.equals("Seleccione Asignatura...")) return;
        vista.cbProfesor.removeAllItems();
        Set<String> profs = new TreeSet<>();
        try (FileInputStream fis = new FileInputStream(new File("Datosbase.xlsx"));
             Workbook wb = new XSSFWorkbook(fis)) {
            Sheet s = wb.getSheetAt(0);
            for (Row r : s) {
                if (r.getRowNum() > 0 && r.getCell(2).toString().trim().equals(sel))
                    profs.add(r.getCell(1).toString().trim());
            }
            for (String p : profs) vista.cbProfesor.addItem(p);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void filtrarGrupos() {
        String mSel = (String) vista.cbAsignatura.getSelectedItem();
        String pSel = (String) vista.cbProfesor.getSelectedItem();
        if (pSel == null || pSel.contains("Esperando")) return;
        vista.cbGrupo.removeAllItems();
        Set<String> grps = new TreeSet<>();
        try (FileInputStream fis = new FileInputStream(new File("Datosbase.xlsx"));
             Workbook wb = new XSSFWorkbook(fis)) {
            Sheet s = wb.getSheetAt(0);
            for (Row r : s) {
                if (r.getRowNum() > 0 && r.getCell(2).toString().trim().equals(mSel) && r.getCell(1).toString().trim().equals(pSel))
                    grps.add(r.getCell(0).toString().trim());
            }
            for (String g : grps) vista.cbGrupo.addItem(g);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void consultarEvaluacion() {
        String id = vista.cbAsignatura.getSelectedItem() + "_" + vista.cbProfesor.getSelectedItem() + "_" + vista.cbGrupo.getSelectedItem();
        MEvaluacion enc = modelo.buscarPorId(id);
        if (enc != null) {
            rellenarInterfaz(enc);
            vista.setEstatus(Color.GREEN);
        } else {
            limpiarInterfaz();
            cargarAlumnosNuevos();
            vista.setEstatus(Color.YELLOW);
        }
    }

    private void cargarAlumnosNuevos() {
        DefaultTableModel m = (DefaultTableModel) vista.panelRubrica.tablaRubrica.getModel();
        m.setRowCount(0);
        String mSel = vista.cbAsignatura.getSelectedItem().toString();
        String gSel = vista.cbGrupo.getSelectedItem().toString();
        try (FileInputStream fis = new FileInputStream(new File("Datosbase.xlsx"));
             Workbook wb = new XSSFWorkbook(fis)) {
            Sheet s = wb.getSheetAt(0);
            int count = 0;
            for (Row r : s) {
                if (r.getRowNum() > 0 && r.getCell(0).toString().trim().equals(gSel) && r.getCell(2).toString().trim().equals(mSel)) {
                    m.addRow(new Object[]{r.getCell(4).toString().trim(), 0.0, 0.0, 0.0, 0.0});
                    if (++count >= 4) break;
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void guardarEvaluacion() {
        MEvaluacion ev = new MEvaluacion(vista.cbAsignatura.getSelectedItem().toString(), vista.cbProfesor.getSelectedItem().toString(), vista.cbGrupo.getSelectedItem().toString());
        ev.setNombreProyecto(vista.panelProducto.txtNombreProyecto.getText());
        ev.setFecha(vista.panelProducto.txtFecha.getText());
        ev.setObservaciones(vista.panelProducto.txtObservaciones.getText());
        DefaultTableModel mR = (DefaultTableModel) vista.panelRubrica.tablaRubrica.getModel();
        for (int i = 0; i < mR.getRowCount(); i++) 
            ev.getAlumnos().add(new EvaluacionAlumno(mR.getValueAt(i,0).toString(), Double.parseDouble(mR.getValueAt(i,1).toString()), Double.parseDouble(mR.getValueAt(i,2).toString()), Double.parseDouble(mR.getValueAt(i,3).toString()), Double.parseDouble(mR.getValueAt(i,4).toString())));
        modelo.guardarOActualizar(ev);
        vista.setEstatus(Color.GREEN);
        JOptionPane.showMessageDialog(vista, "Datos guardados y Reporte Excel generado.");
    }

    private void eliminarEvaluacion() {
        String id = vista.cbAsignatura.getSelectedItem() + "_" + vista.cbProfesor.getSelectedItem() + "_" + vista.cbGrupo.getSelectedItem();
        if (JOptionPane.showConfirmDialog(vista, "¿Eliminar?") == JOptionPane.YES_OPTION) {
            modelo.eliminar(id);
            limpiarInterfaz();
        }
    }

    private void limpiarInterfaz() {
        vista.panelProducto.txtNombreProyecto.setText("");
        vista.panelProducto.txtObservaciones.setText("");
        ((DefaultTableModel) vista.panelRubrica.tablaRubrica.getModel()).setRowCount(0);
        vista.setEstatus(Color.RED);
    }

    private void rellenarInterfaz(MEvaluacion e) {
        vista.panelProducto.txtNombreProyecto.setText(e.getNombreProyecto());
        vista.panelProducto.txtFecha.setText(e.getFecha());
        vista.panelProducto.txtObservaciones.setText(e.getObservaciones());
        DefaultTableModel m = (DefaultTableModel) vista.panelRubrica.tablaRubrica.getModel();
        m.setRowCount(0);
        for (EvaluacionAlumno a : e.getAlumnos()) m.addRow(new Object[]{a.getNombre(), a.getCriterio1(), a.getCriterio2(), a.getCriterio3(), a.getPromedio()});
    }
}