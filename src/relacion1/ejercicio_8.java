package relacion1;

import java.io.File;
import java.io.FilenameFilter;

public class ejercicio_8 {

    public static void main(String[] args) {
        
        // Obtiene la carpeta actual
        File carpetaActual = new File(".");
        String extension = ".xml";        
        
        // Utilizar un objeto FilenameFilter para filtrar por extensión
        FilenameFilter filtroXML = new FilenameFilter() {
            @Override
            //El método accept es el método clave para filtrar los archivos.
            public boolean accept(File carpetaActual, String nombreArchivo) {
                return nombreArchivo.endsWith(extension); // Aceptar solo archivos .txt
            }
        };
        
        // Obtener una lista de archivos que pasan el filtro
        String listado [] = carpetaActual.list(filtroXML);
        
        // Comprueba si la carpeta está vacía
        if(listado == null){
            System.out.println("La carpeta esta vacia o no existe");
        }else{
            System.out.println("Contenido de la carpeta actual:");
            for (String lista : listado){
            System.out.println(lista);
        }
        }
    }
}
