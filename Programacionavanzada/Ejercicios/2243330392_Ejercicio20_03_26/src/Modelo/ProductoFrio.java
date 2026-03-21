package Modelo;

public class ProductoFrio extends Productos {

	private double temperaturaRecomendada;
	double temperatura = 4.0;
	public ProductoFrio(String nombre,Double precio) {
		super(nombre, precio, "Perecedero");

		this.temperaturaRecomendada = temperatura;
		// TODO Auto-generated constructor stub
	}
	@Override
	public String mostrarInformacion() {
		// TODO Auto-generated method stub
		return "Refrigerado / Anaquel frio";
	}
	
}
