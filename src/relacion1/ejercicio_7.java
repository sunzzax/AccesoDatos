package relacion1;

import java.io.File;
import java.util.Scanner;

public class ejercicio_7 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduzca la ruta de la carpeta:");

        File carpeta = new File(scan.nextLine());

        // Verificamos que la carpeta exista y sea un directorio
        if (carpeta.exists() && carpeta.isDirectory()) {
            // Llamamos a borrarContenido para eliminar el contenido de la carpeta
            borrarContenido(carpeta);
            System.out.println("La carpeta ha sido borrada exitosamente.");
        } else {
            System.out.println("La ruta especificada no es una carpeta válida.");
        }
    }

    public static void borrarContenido(File carpeta) {
        // Ejemplo:
        // Supongamos que la carpeta 'miCarpeta' contiene:
        // - Un archivo llamado 'archivo1.txt'
        // - Una subcarpeta llamada 'subCarpeta', que contiene 'archivo2.txt'

        for (File archivo : carpeta.listFiles()) { // Iteramos sobre cada archivo/subcarpeta en la carpeta

            if (archivo.isDirectory()) {
                // Al llegar a 'subCarpeta', volvemos a llamar a borrarContenido para borrar su contenido
                borrarContenido(archivo); // Se llama a borrarContenido(subCarpeta)
            }

            // Si es archivo, lo borramos. Si es una carpeta vacía, también se borra aquí.
            archivo.delete();
            // Dentro de 'subCarpeta', se borra 'archivo2.txt'
            // Luego se borra 'subCarpeta' (ahora vacía)
        }

        // Finalmente, borramos la carpeta principal
        carpeta.delete();
        // Después de que se ha eliminado 'subCarpeta' y 'archivo1.txt', se borra 'miCarpeta'
    }
}

//Carpeta Principal/
//|- archivo1.txt
//|- Subcarpeta/
//    |- archivo2.txt
//
//Pasos que sigue:
//1. Entra en Carpeta Principal
//2. Encuentra archivo1.txt -> Lo borra
//3. Encuentra Subcarpeta -> Entra en ella
//   3.1. Encuentra archivo2.txt -> Lo borra
//   3.2. Borra Subcarpeta (ya vacía)
//4. Borra Carpeta Principal
