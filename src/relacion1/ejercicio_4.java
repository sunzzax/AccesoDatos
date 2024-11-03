
package relacion1;

import java.io.File;
import java.util.Scanner;


public class ejercicio_4 {
    public static void main(String[] args) {
        
        Scanner scan = new Scanner (System.in);
        
        System.out.println("Por favor introduzca el nombre del path:");
        File path;
        
        do{
        path = new File(scan.nextLine());
        
       String contenido[];
       
       if(path.exists() && path.isDirectory()){
           System.out.println("El path " + path.getName() + " existe.");
           File [] archivos = path.listFiles();
           
           if(archivos != null){
               for (File archivo : archivos){
                   if(archivo.isDirectory()){
                       System.out.println("[DIRECTORIO] " + archivo.getName());
                   }else{
                       System.out.println("[ARCHIVO] " + archivo.getName());
                   }
               }
           }           
         }else{
           System.out.println("No existe, vuelva a repetirlo...");
       }
        }while(!path.exists());
    }
}
