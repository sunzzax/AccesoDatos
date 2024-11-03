package tienda;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Carga en memoria una lista objetos de tipo Producto a partir del fichero
 * binario proporcionado y los muestra por consola tal y como se indica en el
 * ejemplo. El fichero .dat que te proporcionamos contiene una estructura de
 * tipo LinkedList serializada con esos diez productos de ejemplo. Una vez que
 * se tengan los productos en memoria, se generará el archivo de texto
 * productos_salida.txt donde cada producto estará representado por una línea.
 *
 * @author Profesor
 */
public class BinarioProductosToTexto {

    private static final String NOMBRE_ARCHIVO_ENTRADA_BIN_DEFAULT = "productos_entrada.dat";
    private static final String productos_salida = "productos_salida.txt";

    /**
     * Programa principal de prueba de escritura de productos en un archivo de
     * texto
     *
     * @param args argumentos de la línea de ordenes
     */
    public static void main(String[] args) throws IOException {
        
        List<Producto> listaProductos;

        try (FileInputStream fis = new FileInputStream(NOMBRE_ARCHIVO_ENTRADA_BIN_DEFAULT); ObjectInputStream entrada = new ObjectInputStream(fis)) {

            listaProductos = (List<Producto>) entrada.readObject();

            if (listaProductos != null) {
                System.out.println("Contenido del archivo:");
                for (Producto contenido : listaProductos) {
                    System.out.println(contenido);
                }
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(productos_salida));) {

                for (Producto producto : listaProductos) {

                    bw.write("id:" + producto.getId() + "#" + "nombre:" + producto.getNombre() + "#" + "descripción:"
                            + producto.getDescripcion() + "#" + "precio:" + producto.getPrecio() + "\n");

                }

            }

        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {

        }

    }
}
