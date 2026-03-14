package Controlador;
import Modelo.*;
import Vista.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class Cvistagral implements ActionListener {
    private VistaGral padre;
    private VistaInventario vInv;
    private VistaPuntoVenta vVenta;
    private Gestion modelo;
    private ArrayList<Entidad> carrito = new ArrayList<>();

    public Cvistagral(VistaGral padre, Gestion modelo) {
        this.padre = padre; this.modelo = modelo;
        for(Entidad e : ArchivoServicio.importarCSV()) modelo.agregar(e);
        this.padre.getItemGestion().addActionListener(e -> abrirInv());
        this.padre.getItemVenta().addActionListener(e -> abrirVenta());
    }

    private void abrirInv() {
        vInv = new VistaInventario(); padre.getDesktop().add(vInv);
        vInv.btnAdd.addActionListener(this); vInv.btnSearch.addActionListener(this);
        vInv.btnDel.addActionListener(this); vInv.btnUpd.addActionListener(this);
        actualizarTabla(); vInv.setVisible(true);
    }

    private void abrirVenta() {
        vVenta = new VistaPuntoVenta(); padre.getDesktop().add(vVenta);
        vVenta.btnAddCar.addActionListener(this); vVenta.btnCobrar.addActionListener(this);
        vVenta.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(vInv != null) {
            if(e.getSource()==vInv.btnAdd) guardar();
            if(e.getSource()==vInv.btnSearch) buscar();
            if(e.getSource()==vInv.btnDel) eliminar();
            if(e.getSource()==vInv.btnUpd) actualizar();
        }
        if(vVenta != null) {
            if(e.getSource()==vVenta.btnAddCar) agregarACarrito();
            if(e.getSource()==vVenta.btnCobrar) cobrar();
        }
    }

    private void guardar() {
        try {
            if(modelo.existe(vInv.txtId.getText())) return;
            Entidad en = new Entidad();
            en.setId(vInv.txtId.getText()); en.setNombre(vInv.txtNom.getText());
            en.setPrecio(Double.parseDouble(vInv.txtPre.getText()));
            en.setCantidad(Integer.parseInt(vInv.txtStock.getText()));
            modelo.agregar(en); finalizar();
        } catch(Exception ex) { JOptionPane.showMessageDialog(null, "Error en campos"); }
    }

    private void buscar() {
        Entidad en = modelo.buscar(vInv.txtId.getText());
        if(en!=null) { vInv.txtNom.setText(en.getNombre()); vInv.txtPre.setText(""+en.getPrecio()); }
    }

    private void eliminar() {
        if(JOptionPane.showConfirmDialog(null, "¿Eliminar?")==0) {
            modelo.eliminar(vInv.txtId.getText()); finalizar();
        }
    }

    private void actualizar() {
        Entidad en = new Entidad();
        en.setId(vInv.txtId.getText()); en.setNombre(vInv.txtNom.getText());
        en.setPrecio(Double.parseDouble(vInv.txtPre.getText()));
        en.setCantidad(Integer.parseInt(vInv.txtStock.getText()));
        modelo.actualizar(en.getId(), en); finalizar();
    }

    private void agregarACarrito() {
        Entidad en = modelo.buscar(vVenta.txtIdVenta.getText());
        if(en!=null) {
            int c = Integer.parseInt(vVenta.txtCant.getText());
            Entidad v = new Entidad(); v.setNombre(en.getNombre()); v.setPrecio(en.getPrecio()); v.setCantidad(c);
            carrito.add(v);
            vVenta.modelo.addRow(new Object[]{en.getId(), en.getNombre(), c, (en.getPrecio()*c)});
        }
    }

    private void cobrar() {
        double total = 0;
        for(Entidad e : carrito) total += (e.getPrecio()*e.getCantidad());
        ArchivoServicio.imprimirTicket(carrito, total);
        JOptionPane.showMessageDialog(null, "Ticket Generado");
        carrito.clear(); vVenta.modelo.setRowCount(0);
    }

    private void finalizar() {
        ArchivoServicio.exportarCSV(modelo.getProductos());
        actualizarTabla();
    }

    private void actualizarTabla() {
        vInv.modelo.setRowCount(0);
        for(Entidad e : modelo.getProductos()) vInv.modelo.addRow(new Object[]{e.getId(), e.getNombre(), e.getPrecio(), e.getCantidad()});
    }
}