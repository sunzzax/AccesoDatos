

package tienda;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Programa de prueba de carga de productos a partir de un archivo de texto
 */
public class TextoProductosToBinario {
    private static final String NOMBRE_ARCHIVO_ENTRADA_TXT_DEFAULT = "productos_entrada.txt";
    private static final String NOMBRE_ARCHIVO_BINARIO = "productosentrada.dat";
    /**
     * Método principal
     *
     * @param args argumentos de la línea de ordenes
     */
    public static void main(String[] args) {

        List<Producto> listaProductos = new ArrayList<>();
        String linea;

        try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_ARCHIVO_ENTRADA_TXT_DEFAULT))) {

            while ((linea = br.readLine()) != null) {
                String[] estructura = linea.split("::");

                // Verifica que la línea tiene exactamente 4 elementos
                if (estructura.length == 4) {
                    try {
                        // Convierte el cuarto elemento (precio) a un tipo double
                        Double precio = Double.parseDouble(estructura[3]);

                        // Crea un objeto Producto con los datos extraídos
                        Producto producto = new Producto(estructura[0], estructura[1], estructura[2], precio);
                        listaProductos.add(producto);

                    } catch (NumberFormatException ex) {
                        // Muestra un mensaje si el precio no es un número válido

                    }
                } else {
                    // Muestra un mensaje si la línea no tiene el formato correcto

                }
            }

            // Muestra el número total de productos válidos cargados y la lista de productos
            System.out.println("CONTENIDO DEL ARCHIVO DE TEXTO");
            System.out.println("------------------------------");
            System.out.println("Total de productos: " + listaProductos.size());
            int contador = 1;
            for (Producto saltoLinea : listaProductos) {
                System.out.println("PRODUCTO " + contador + ": " + saltoLinea);
                contador++;

            }

        } catch (FileNotFoundException ex) {
            System.out.println("Error al encontrar el archivo...");
        } catch (IOException ex) {
            System.out.println("Errror al leer el archivo de texto .txt");
        }

        // Serialización de la listaProductos a .dat
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOMBRE_ARCHIVO_BINARIO))){
            
            oos.writeObject(listaProductos);
            System.out.println("\nLista de productos guardada en: " + NOMBRE_ARCHIVO_BINARIO);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextoProductosToBinario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TextoProductosToBinario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
