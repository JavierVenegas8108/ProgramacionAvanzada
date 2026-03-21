package Modelo;

public class FrutaVerduras extends Productos {

	public FrutaVerduras(String nombre, Double precio) {
		super(nombre, precio, "Perecedero");
		// TODO Auto-generated constructor stub
	}
	@Override
	public String mostrarInformacion() {
		// TODO Auto-generated method stub
		return "Refrigerado / Anaquel fresco";
	}

}
