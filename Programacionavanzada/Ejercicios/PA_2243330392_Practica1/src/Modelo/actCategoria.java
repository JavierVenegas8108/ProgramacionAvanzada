package Modelo;

public class actCategoria {
    private String idcategoria;
    private String categoria;

    public actCategoria(String idcategoria, String categoria) {
        this.idcategoria = idcategoria;
        this.categoria = categoria;
    }

    public String getIdcategoria() { return idcategoria; }
    public void setIdcategoria(String idcategoria) { this.idcategoria = idcategoria; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    @Override
    public String toString() {
        return idcategoria + " - " + categoria;
    }

    public String toCSV() {
        return idcategoria + "," + categoria;
    }

    public static actCategoria fromCSV(String lineaCSV) {
        String[] partes = lineaCSV.split(",");
        if (partes.length >= 2) {
            return new actCategoria(partes[0].trim(), partes[1].trim());
        }
        return null;
    }
}