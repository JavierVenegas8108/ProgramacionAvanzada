package Controlador;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Modelo.*;
import Vista.*;

public class Cvistagral implements ActionListener {
    private VistaGral padre;
    private VistaInventario vInv;
    private VistaPuntoVenta vVenta;
    private Gestion modelo;

    public Cvistagral(VistaGral padre, Gestion modelo) {
        this.padre = padre;
        this.modelo = modelo;

        this.padre.itemGestion.addActionListener(e -> abrirInv());
        this.padre.itemVenta.addActionListener(e -> abrirVenta());
        this.padre.itemSalir.addActionListener(e -> System.exit(0));

        ArrayList<Entidad> datosCSV = ArchivoServicio.importarCSV();
        if (datosCSV != null && !datosCSV.isEmpty()) {
            for (Entidad ent : datosCSV) {
                this.modelo.agregar(ent);
            }
            System.out.println("Carga inicial: " + datosCSV.size() + " productos listos.");
        }
    }

    private void abrirInv() {
        vInv = new VistaInventario();
        padre.getDesktop().add(vInv);
        
        vInv.btnAdd.addActionListener(this);
        vInv.btnDel.addActionListener(this);
        vInv.btnSearch.addActionListener(this);  

        vInv.tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int fila = vInv.tabla.getSelectedRow();
                    if (fila != -1) {
                        String id = vInv.tabla.getValueAt(fila, 0).toString();
                        Entidad ent = modelo.buscar(id);
                        
                        if (ent != null) {
                            CargadorImagenes.cargarFoto(vInv.lblImagen, ent.getRutaImagen());
                        }
                    }
                }
            }
        });

        actualizarTabla();
        vInv.setVisible(true);
    }

    private void abrirVenta() {
        vVenta = new VistaPuntoVenta();
        padre.getDesktop().add(vVenta);
        vVenta.setVisible(true);
    }

    public void actualizarTabla() {
        if (vInv != null) {
            vInv.modelo.setRowCount(0); 
            for (Entidad e : modelo.getProductos()) {
                vInv.modelo.addRow(new Object[]{
                    e.getId(), 
                    e.getNombre(), 
                    e.getPrecio(), 
                    e.getCantidad()
                });
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (vInv != null && e.getSource() == vInv.btnAdd) {
            try {
                Entidad n = new Entidad(
                    vInv.txtId.getText(), 
                    vInv.txtNom.getText(), 
                    "General", 
                    Double.parseDouble(vInv.txtPre.getText()), 
                    Integer.parseInt(vInv.txtStock.getText()), 
                    "img/default.jpg" 
                );
                modelo.agregar(n);
                actualizarTabla();
                JOptionPane.showMessageDialog(vInv, "Producto agregado.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vInv, "Error en los datos.");
            }
        }
        
        if (vInv != null && e.getSource() == vInv.btnDel) {
            int fila = vInv.tabla.getSelectedRow();
            if (fila != -1) {
                String id = vInv.tabla.getValueAt(fila, 0).toString();
                modelo.eliminar(id);
                actualizarTabla();
                vInv.lblImagen.setIcon(null);
                vInv.lblImagen.setText("Eliminado");
            }
        }
        
        if (vInv != null && e.getSource() == vInv.btnSearch) {
            String idBusqueda = vInv.txtId.getText();
            Entidad ent = modelo.buscar(idBusqueda);
            if (ent != null) {
                CargadorImagenes.cargarFoto(vInv.lblImagen, ent.getRutaImagen());
            } else {
                JOptionPane.showMessageDialog(vInv, "No se encontró el ID");
            }
        }
    }
}