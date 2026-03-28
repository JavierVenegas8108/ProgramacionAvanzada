package Modelo;

public class MRequisitos {
    private String descripcion;
    private boolean cumple; 

    public MRequisitos(String descripcion, boolean cumple) {
        this.descripcion = descripcion;
        this.cumple = cumple;
    }

    
    public String getDescripcion() {
    	return descripcion; 
    	}
    public void setDescripcion(String descripcion) {
    	this.descripcion = descripcion; 
    	}

    public boolean isCumple() {
    	return cumple; 
    	}
    public void setCumple(boolean cumple) {
    	this.cumple = cumple; 
    	}
}