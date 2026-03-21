package Modelo;

public class Anaquel extends Productos{

	public Anaquel(String nombre, Double precio) {
		super(nombre, precio,"No perecedero");
		
	}

	@Override
	public String mostrarInformacion() {
		// TODO Auto-generated method stub
		return "Temperatura ambiente / Estanteria seca";
	}


}
