package Modelo;

public class Bebidas extends Productos {

	public Bebidas(String nombre, Double precio) {
		super(nombre, precio, "Perecedero");
		// TODO Auto-generated constructor stub
	}
	@Override
	public String mostrarInformacion() {
		// TODO Auto-generated method stub
		return "Carga pesada / Refrigerado";
	}

}
