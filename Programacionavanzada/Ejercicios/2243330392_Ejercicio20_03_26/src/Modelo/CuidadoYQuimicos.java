package Modelo;

public class CuidadoYQuimicos extends Productos {

	public CuidadoYQuimicos(String nombre, Double precio) {
		super(nombre, precio, "Higiene / Limpieza");
		// TODO Auto-generated constructor stub
	}
	@Override
	public String mostrarInformacion() {
		// TODO Auto-generated method stub
		return "Productos de cuidado personal, No mezclar con alimentos";
	}

}
