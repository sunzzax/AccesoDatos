
package relacion1;

import java.io.File;


public class ejercicio_1 {
    public static void main(String[] args) {
        File ruta = new File(".");
        String directorio [] = ruta.list();
        
        if(ruta != null){
            for (String contenido : directorio){
                System.out.println(contenido);
            }
        }else{
            System.out.println("El directorio esta vacio.");
            System.out.println("");
        }
    }
}
