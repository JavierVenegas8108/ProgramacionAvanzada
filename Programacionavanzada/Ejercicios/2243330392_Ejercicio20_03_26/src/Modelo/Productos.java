package Modelo;

public abstract class Productos {

	private String nombre;
	private Double precio;
	private String categoria;
	
	public Productos(String nombre, Double precio,String categoria) {
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
	}
	
	public abstract String mostrarInformacion();

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Double getPrecio() {
		return precio;
	}
	
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
}
