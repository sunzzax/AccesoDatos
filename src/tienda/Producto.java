package tienda;

import java.io.Serializable;

/**
 * Clase Producto
 * @author profesor
 */
public class Producto implements Serializable {
    private String id;
    private String nombre;
    private String descripcion;
    private double precio;
    private static final long serialVersionUID = 42L;
    
    Producto (String id, String nombre, String descripcion, double precio) {
        this.id = id ;
        this.nombre = nombre ;
        this.descripcion = descripcion ;
        this.precio= precio ;
    }
    
    
    public String getId () {
        return this.id;
    }

    public String getNombre () {
        return this.nombre;
    }
    
    public String getDescripcion () {
        return this.descripcion;
    }

    public double getPrecio () {
        return this.precio;
    }
    
    @Override
    public String toString() {
        return String.format ("{id:%s, nombre:%s, descripcion:%s, precio:%.2f}",
                this.id, this.nombre, this.descripcion, this.precio);
    }
    
}
