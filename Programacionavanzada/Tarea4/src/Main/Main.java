package Main;

import Controlador.ControladorExamen;
import Modelo.GestorArchivos;
import Vista.VistaExamen;

public class Main {
    public static void main(String[] args) {
        VistaExamen vista = new VistaExamen();
        GestorArchivos gestor = new GestorArchivos();
        new ControladorExamen(vista, gestor);
        vista.setVisible(true);
    }
}