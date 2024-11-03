

package tienda;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Clase que lee de un fichero binario, donde se grabó una lista de objetos
 * Producto y muestra el contenido de lo leído por consola.
 */
public class BinarioProductosToConsola {

    // Constante que define el nombre del archivo que vamos a leer
    private static final String NOMBRE_ARCHIVO_ENTRADA_BIN_DEFAULT = "productos_entrada.dat";

    /**
     * Método principal del programa
     *
     * @param args argumentos de la línea de ordenes (no se utilizan)
     */
    public static void main(String[] args) {

        // Declaramos una lista que contendrá objetos de tipo Producto
        List<Producto> listaProductos;
       
        // Bloque try-with-resources que asegura que los streams se cierren automáticamente
        try (
                // Creamos un FileInputStream para leer el archivo binario
                FileInputStream fis = new FileInputStream(NOMBRE_ARCHIVO_ENTRADA_BIN_DEFAULT); // Creamos un ObjectInputStream para deserializar objetos del archivo
                 ObjectInputStream entrada = new ObjectInputStream(fis)) {
            // Leemos la lista completa del archivo y la convertimos al tipo correcto
            listaProductos = (List<Producto>) entrada.readObject();

            // Inicializamos un contador para numerar los productos
            int contador = 1;

            // Verificamos que la lista no sea null
            if (listaProductos != null) {
                System.out.println("CONTENIDO DEL ARCHIVO BINARIO");
                System.out.println("-----------------------------");
                System.out.println("Total de productos: " + listaProductos.size());

                // Recorremos cada producto en la lista
                for (Producto contenido : listaProductos) {
                    // Imprimimos cada producto con su número de orden
                    System.out.println("PRODUCTO " + contador + " " + contenido);
                    // Incrementamos el contador para el siguiente producto
                    contador++;
                }

                // PROFESORA:
//                if (listaProductos != null) {
//                    // Escribir por consola
//                    System.out.println("INFORMACIÓN LEÍDA DEL FICHERO SERIALIZADO");
//                    System.out.println("-----------------------------------------");
//                    System.out.printf("Total de productos: %d\n", listaProductos.size());
//                    for (int i = 0; i < listaProductos.size(); i++) {
//                        System.out.printf("PRODUCTO%3d: %s\n", i, listaProductos.get(i).toString());
//                    }
//                } else {
//                    System.out.println("Lista de productos vacía.");
//                }

            }

            // Manejamos las posibles excepciones que puedan ocurrir
        } catch (FileNotFoundException ex) {
            // Se ejecuta si no se encuentra el archivo
            System.err.println("No se encontró el archivo: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            // Se ejecuta si hay problemas leyendo el archivo
            System.err.println("Error de lectura/escritura: " + ex.getMessage());
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            // Se ejecuta si no se encuentra la definición de la clase al deserializar
            System.err.println("No se encontró la clase al deserializar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
