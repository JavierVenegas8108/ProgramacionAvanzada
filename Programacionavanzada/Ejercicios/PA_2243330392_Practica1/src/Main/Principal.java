package Main;
import Vista.VistaCategoria;
import Controlador.ControladorCategoria;

public class Principal {
    public static void main(String[] args) {
        VistaCategoria vista = new VistaCategoria();
        new ControladorCategoria(vista);
        vista.setVisible(true);
    }
}
