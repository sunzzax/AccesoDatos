package relacion1;

import java.io.File;
import java.util.Scanner;

public class ejercicio_6 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        File fichero = new File(scan.nextLine());

        if (fichero.exists() && fichero.isFile()) {
            fichero.delete();
        }else{
            System.out.println("No existe el fichero");
        }
    }
}
