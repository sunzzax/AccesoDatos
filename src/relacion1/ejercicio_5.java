
package relacion1;

import java.io.File;


public class ejercicio_5 {
    public static void main(String[] args) {
        
        String directorio = ("C://UD1//EJEMPLO");
        
        File dir = new File (directorio);
        
        if(!dir.exists()){
            dir.mkdirs();
        }else{
            System.out.println("El directorio  con la ruta " + dir.getAbsolutePath() + " ya existe.");
        }
        
    }
}
