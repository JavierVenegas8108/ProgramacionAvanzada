package Main;

import Vista.VistaCategoriaB;
import Controlador.ControladorCategoriaB;

public class Main {
    public static void main(String[] args) {
        VistaCategoriaB vista = new VistaCategoriaB();
        new ControladorCategoriaB(vista);
        vista.setVisible(true);
    }
}