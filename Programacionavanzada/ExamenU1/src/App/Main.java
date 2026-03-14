package App;
import Modelo.Gestion;
import Vista.VistaGral;
import Controlador.Cvistagral;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Gestion m = new Gestion();
            VistaGral v = new VistaGral();
            new Cvistagral(v, m);
            v.setVisible(true);
        });
    }
}