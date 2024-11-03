package relacion3;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 * Clase principal que implementa un sistema de gestión de registros de usuarios
 * utilizando acceso aleatorio a ficheros.
 * Los registros contienen ID, edad y nombre de usuarios.
 */
public class ProyectoBaseAccesoAleatorio {
    // Nombre del archivo donde se almacenarán los registros
    private static final String registros = "registros.dat";
    
    // Definición de las longitudes fijas para cada campo del registro
    private static final int LONG_REGISTRO = 48; // Longitud total del registro en bytes
    // Desglose: 4 bytes (int id) + 4 bytes (int edad) + 40 bytes (20 chars * 2 bytes por char para el nombre)
    
    /**
     * Método principal que muestra el menú de opciones y gestiona la interacción con el usuario
     */
    public static void main(String[] args) {
        int opcion = 0;
        Scanner scan = new Scanner(System.in);
        
        // Bucle principal del programa
        do {
            // Mostrar menú de opciones
            System.out.println("INTRODUZCA UNA OPCION:");
            System.out.println("1. Agregar nuevos registros al final del fichero.");
            System.out.println("2. Buscar registros por identificador.");
            System.out.println("3. Actualizar registros existentes.");
            System.out.println("4. Salir de la aplicación.");
            opcion = scan.nextInt();
            
            switch (opcion) {
                case 1: // Opción para agregar nuevo registro
                    System.out.println("Introduzca un identificador para el usuario:");
                    int id = scan.nextInt();
                    
                    System.out.println("Introduzca la edad del usuario:");
                    int edad = scan.nextInt();
                    
                    scan.nextLine(); // Limpia el buffer del scanner
                    
                    System.out.println("Introduzca el nombre del usuario:");
                    String nombre = scan.nextLine();
                    
                    agregarRegistro(id, edad, nombre);
                    break;
                    
                case 2: // Opción para buscar registro por ID
                    System.out.println("Introduzca el identificador del usuario que quiere mostrar:");
                    id = scan.nextInt();
                    
                    mostrarRegistro(id);
                    break;
                    
                case 3: // Opción para actualizar registro existente
                    System.out.println("Introduzca el identificador:");
                    id = scan.nextInt();
                    
                    System.out.println("Introduzca la nueva edad:");
                    int nuevaEdad = scan.nextInt();
                    
                    scan.nextLine(); // Limpia el buffer del scanner
                    
                    System.out.println("Introduzca el nuevo nombre:");
                    String nuevoNombre = scan.nextLine();
                    
                    actualizarRegistro(id, nuevaEdad, nuevoNombre);
                    break;
                    
                case 4: // Salir del programa
                    break;
            }
        } while (opcion != 4);
    }

    /**
     * Agrega un nuevo registro al final del archivo
     * @param id Identificador único del usuario
     * @param edad Edad del usuario
     * @param nombre Nombre del usuario
     */
    public static void agregarRegistro(int id, int edad, String nombre) {
        try (RandomAccessFile raf = new RandomAccessFile(registros, "rw")) {
            raf.seek(raf.length()); // Posicionamiento al final del archivo
            
            // Escritura de datos en formato binario
            raf.writeInt(id);
            raf.writeInt(edad);
            raf.writeChars(rellenaString(nombre, 20)); // Rellena el nombre hasta 20 caracteres
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo: " + ex.getMessage());
        }
    }

    /**
     * Busca y muestra un registro por su ID
     * @param id Identificador del registro a buscar
     */
    public static void mostrarRegistro(int id) {
        try (RandomAccessFile raf = new RandomAccessFile(registros, "r")) {
            boolean encontrado = false;
            
            // Búsqueda secuencial en el archivo
            while (raf.getFilePointer() < raf.length() && !encontrado) {
                long posicionInicial = raf.getFilePointer();
                int registroId = raf.readInt();
                
                if (registroId == id) {
                    // Si encuentra el ID, lee el resto de datos
                    int edad = raf.readInt();
                    String nombre = leeString(raf, 20);
                    
                    // Muestra la información
                    System.out.println("ID: " + registroId);
                    System.out.println("Edad: " + edad);
                    System.out.println("Nombre: " + nombre.trim());
                    encontrado = true;
                } else {
                    // Si no es el registro buscado, salta al siguiente
                    raf.seek(posicionInicial + LONG_REGISTRO);
                }
            }
            
            if (!encontrado) {
                System.out.println("No se encontró ningún registro con el ID: " + id);
            }
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo: " + ex.getMessage());
        }
    }

    /**
     * Actualiza un registro existente por su ID
     * @param id Identificador del registro a actualizar
     * @param nuevaEdad Nueva edad a guardar
     * @param nuevoNombre Nuevo nombre a guardar
     */
    public static void actualizarRegistro(int id, int nuevaEdad, String nuevoNombre) {
        try (RandomAccessFile raf = new RandomAccessFile(registros, "rw")) {
            boolean encontrado = false;
            
            // Búsqueda secuencial en el archivo
            while (raf.getFilePointer() < raf.length() && !encontrado) {
                long posicionInicial = raf.getFilePointer();
                int registroId = raf.readInt();
                
                if (registroId == id) {
                    // Si encuentra el ID, vuelve a la posición después del ID
                    raf.seek(posicionInicial + 4);
                    
                    // Actualiza edad y nombre
                    raf.writeInt(nuevaEdad);
                    raf.writeChars(rellenaString(nuevoNombre, 20));
                    
                    System.out.println("Registro actualizado correctamente.");
                    encontrado = true;
                } else {
                    // Si no es el registro buscado, salta al siguiente
                    raf.seek(posicionInicial + LONG_REGISTRO);
                }
            }
            
            if (!encontrado) {
                System.out.println("No se encontró ningún registro con el ID: " + id);
            }
        } catch (IOException ex) {
            System.out.println("Error al actualizar el archivo: " + ex.getMessage());
        }
    }

    /**
     * Rellena una cadena con espacios hasta alcanzar la longitud especificada
     * @param str Cadena original
     * @param length Longitud deseada
     * @return Cadena rellenada con espacios o truncada si excede la longitud
     */
    private static String rellenaString(String str, int length) {
        StringBuilder sb = new StringBuilder(str);
        // Rellena con espacios si es más corta
        while (sb.length() < length) {
            sb.append(' ');
        }
        // Trunca si es más larga
        if (sb.length() > length) {
            return sb.substring(0, length);
        }
        return sb.toString();
    }

    /**
     * Lee una cadena de caracteres de longitud fija desde el archivo
     * @param raf Archivo de acceso aleatorio
     * @param length Longitud de la cadena a leer
     * @return Cadena leída
     */
    private static String leeString(RandomAccessFile raf, int length) {
        StringBuilder sb = new StringBuilder(length);
        try {
            // Lee caracter por caracter hasta completar la longitud
            for (int i = 0; i < length; i++) {
                sb.append(raf.readChar());
            }
        } catch (IOException ex) {
            System.out.println("Error en la lectura del registro que contiene el nombre");
            System.out.printf("ERROR: %s", ex.getMessage());
        }
        return sb.toString();
    }
}
