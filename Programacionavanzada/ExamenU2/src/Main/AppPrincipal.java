package Main;

import Modelo.ControladorDatos; // O GestionDatos, según el nombre de tu clase de persistencia
import Vista.VistaGral;
import Controlador.ControladorGral;
import javax.swing.SwingUtilities;

public class AppPrincipal {

    public static void main(String[] args) {
        // Ejecutamos la interfaz en el hilo de despacho de eventos de Swing (Buenas prácticas)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 1. Instanciar el Modelo (El que maneja el JSON)
                ControladorDatos modelo = new ControladorDatos();

                // 2. Instanciar la Vista (La ventana principal)
                VistaGral vista = new VistaGral();

                // 3. Instanciar el Controlador (El que une a ambos)
                // Se le pasan la vista y el modelo por parámetros
                new ControladorGral(vista, modelo);

                // 4. Hacer visible la ventana
                vista.setVisible(true);
            }
        });
    }
}