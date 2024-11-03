package relacion1;

import java.io.File;
import java.util.Scanner;

public class ejercicio_3 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Por favor introduzca el nombre del path que esta buscando:");
        File path;
        do {
            path = new File(scan.nextLine());

            if (path.exists()) {
                System.out.println("El path " + path.getName() + " existe.");

                if (path.isDirectory()) {
                    System.out.println("El path " + path.getName() + " es un directorio.");
                } else if (path.isFile()) {
                    System.out.println("El path " + path.getName() + " es un fichero");
                }

            } else {
                System.out.println("El path " + path.getName() + " no existe.");
            }

        } while (!path.exists());
    }
}
