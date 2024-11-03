
package relacion1;

import java.io.File;
import java.util.Scanner;


public class ejercicio_2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduzca el nombre del path:");
        File path = new File (scan.nextLine());
        
        if (path.exists()){
            System.out.println("El path " + path.getName() + " existe.");
            
            if(path.canRead()){
                System.out.println("El path " + path.getName() + " se puede leer.");
            }else{
                System.out.println("El path " + path.getName() + " no se puede leer.");
            }
            
        }else{
            System.out.println("El path " + path.getName() + " no existe.");
        }
        
    }
}
