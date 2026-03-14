package Modelo;
import java.util.ArrayList;
import java.util.Iterator;

public class Gestion {
    private ArrayList<Entidad> productos;

    public Gestion() {
        this.productos = new ArrayList<>();
    }

    public void agregar(Entidad e) { productos.add(e); }

    public boolean eliminar(String id) {
        Iterator<Entidad> it = productos.iterator();
        while (it.hasNext()) {
            if (it.next().getId().equals(id)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public boolean actualizar(String id, Entidad nueva) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId().equals(id)) {
                productos.set(i, nueva);
                return true;
            }
        }
        return false;
    }

    public Entidad buscar(String id) {
        for (Entidad e : productos) {
            if (e.getId().equals(id)) return e;
        }
        return null;
    }

    public boolean existe(String id) { return buscar(id) != null; }
    public ArrayList<Entidad> getProductos() { return productos; }
}