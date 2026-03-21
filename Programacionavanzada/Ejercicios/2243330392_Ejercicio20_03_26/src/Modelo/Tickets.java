package Modelo;
import java.io.*;
import java.util.ArrayList;

public class Tickets {
	private String folio;
	private ArrayList<Entidad> productos;
	private double total;
	private String fecha;
	public Tickets() {
		this.folio = folio;
		this.productos = productos;
		this.total = total;
		this.fecha = fecha;
	}
	
	public String getFolio() {
		return folio;
	}
	
	public ArrayList<Entidad> getProductos() {
		return productos;
	}
	
	public double getTotal() {
		return total;
	}
	
	
    public static void generarTicket(ArrayList<Entidad> carrito, double total) {
        String nombreArchivo = "Ticket_" + System.currentTimeMillis() + ".txt";
        try (PrintWriter out = new PrintWriter(new FileWriter(nombreArchivo))) {
            out.println("********** TICKET DE VENTA **********");
            out.println("ID | Producto | Precio | Cant.");
            out.println("------------------------------------");
            for (Entidad e : carrito) {
                out.printf("%s | %s | $%.2f | %d\n", e.getId(), e.getNombre(), e.getPrecio(), e.getCantidad());
            }
            out.println("------------------------------------");
            out.printf("TOTAL A PAGAR: $%.2f\n", total);
            out.println("¡Gracias por su compra!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}