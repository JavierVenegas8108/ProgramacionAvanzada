package App;
import Modelo.Gestion;
import Vista.VistaGral;
import Controlador.Cvistagral;
import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
	    VistaGral vista = new VistaGral();
	    Gestion modelo = new Gestion();
	    Cvistagral control = new Cvistagral(vista, modelo);
	    
	    vista.setVisible(true);
	
        
    }
}