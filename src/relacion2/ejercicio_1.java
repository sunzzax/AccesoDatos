package relacion2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ejercicio_1 {

    private static String alumnos = "alumnos.txt"; // Declaro el String fuera para que el metodo pueda acceder.

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int opcion = 0;

        do {

            System.out.println("------------------------------------------------");
            System.out.println("MENU");
            System.out.println("------------------------------------------------");
            System.out.println("1. Registrar un alumno");
            System.out.println("2. Mostrar datos del fichero");
            System.out.println("3. Ejecutar consulta mayores de 30");
            System.out.println("4. Ejecutar consulta alumnos Juan");
            System.out.println("5. Eliminar alumno");
            System.out.println("6. Salir");
            System.out.println("Seleccione una opcion:");

            opcion = scan.nextInt();

            switch (opcion) {

                case 1:

                    scan.nextLine();

                    System.out.println("Introduzca el nombre del alumno:");
                    String nombre = scan.nextLine();

                    System.out.println("Introduzca el correo electronico del alumno:");
                    String correo = scan.nextLine();

                    System.out.println("Introduzca la edad del alumno:");
                    int edad = scan.nextInt();

                    System.out.println("Introduzca el telefono del alumno:");
                    long telefono = scan.nextLong();

                    registrarAlumno(nombre, correo, edad, telefono);

                    break;

                case 2:

                    mostrarAlumnos();

                    break;

                case 3:

                    mayores30();

                    break;

                case 4:

                    consultaJuan();

                    break;

                case 5:

                    eliminarAlumno();

                    break;

                case 6:
                    break;

            }

        } while (opcion != 6);

    }

    // Metodo para registrar a un alumno.
    public static void registrarAlumno(String nombre, String correo, int edad, long telefono) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(alumnos, true))) {

            bw.write(nombre + "#" + correo + "#" + edad + "#" + telefono + "\n");

        } catch (IOException ex) {
            System.out.println("Ha ocurrido un error...");
            System.out.printf("ERROR %s", ex.getMessage());
        }
    }

    public static void mostrarAlumnos() {

        try (BufferedReader br = new BufferedReader(new FileReader(alumnos))) {

            String linea;
            int TotalRegistros = 0;

            while ((linea = br.readLine()) != null) {
                String campos[] = linea.split("#");

                if (campos.length == 4) {
                    String nombre = campos[0];
                    String email = campos[1];
                    int edad = Integer.parseInt(campos[2]);
                    long telefono = Long.parseLong(campos[3]);

                    System.out.println("Nombre " + nombre);
                    System.out.println("Email " + email);
                    System.out.println("Edad " + edad);
                    System.out.println("Telefono " + telefono);

                    TotalRegistros++;

                }

            }

            System.out.println("Hay " + TotalRegistros + " registros");

        } catch (FileNotFoundException ex) {
            System.out.println("Ha ocurrido un error...");
            System.out.printf("ERROR %s", ex.getMessage());

        } catch (IOException ex) {
            System.out.println("Ha ocurrido un error...");
            System.out.printf("ERROR %s", ex.getMessage());
        }
    }

    public static void mayores30() {
        try (BufferedReader br = new BufferedReader(new FileReader(alumnos))) {

            String linea;
            int totalRegistros = 0;

            while ((linea = br.readLine()) != null) {
                String campos[] = linea.split("#");

                if (campos.length == 4) {
                    String nombre = campos[0];
                    String email = campos[1];
                    int edad = Integer.parseInt(campos[2]);
                    long numero = Long.parseLong(campos[3]);

                    if (edad >= 30) {
                        System.out.println(nombre);
                        System.out.println(email);
                        System.out.println(edad);
                        System.out.println(numero);
                        totalRegistros++;
                    }

                }

            }

            System.out.println("Numero de registros mayores de 30: " + totalRegistros);

        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
    }

    public static void consultaJuan() {
        String linea;
        int totalRegistros = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(alumnos))) {

            while ((linea = br.readLine()) != null) {

                String campos[] = linea.split("#");

                if (campos.length == 4) {

                    String nombre = campos[0];
                    String email = campos[1];
                    int edad = Integer.parseInt(campos[2]);
                    long numero = Long.parseLong(campos[3]);

                    if (nombre.equals("Juan")) {
                        System.out.println(nombre);
                        System.out.println(email);
                        System.out.println(edad);
                        System.out.println(numero);
                        totalRegistros++;
                    }

                }

            }

            System.out.println("Numero de Registros llamados Juan: " + totalRegistros);

        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }

    }

    public static void eliminarAlumno() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Introduzca el nombre del usuario ha eliminar:");
        String nombreEliminar = scan.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(alumnos)); PrintWriter pw = new PrintWriter(new FileWriter("temporal.txt"))) {

            String lineaActual;

            while ((lineaActual = br.readLine()) != null) {

                String campos[] = lineaActual.split("#");

                if (campos.length == 4) {
                    if (!campos[0].equals(nombreEliminar)) { // si el nombre antiguo no es igual al nombre
                        // nuevo entonces se pasa el alumno con todos sus datos a temporal.txt
                        pw.write(lineaActual); // lo escribe en temporal.txt
                        pw.println(); // Salto de linea
                    }
                }
            }

        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }

        File archivoOriginal = new File(alumnos);

        if (archivoOriginal.delete()) {
            File archivoTemporal = new File("temporal.txt");
            archivoTemporal.renameTo(archivoOriginal);
            System.out.println("Linea eliminada con existo");
        } else {
            System.out.println("No se pudo");
        }

        /*
        
        EJEMPLO:
        Lee el archivo línea por línea:

        Cuando lee "Jose#20#1234#Madrid" → como campos[0] es "Jose", NO lo copia a temporal
        Cuando lee "Ana#22#5678#Barcelona" → como campos[0] NO es "Jose", SÍ lo copia a temporal
        Cuando lee "Luis#19#9012#Valencia" → como campos[0] NO es "Jose", SÍ lo copia a temporal
        Cuando lee "Maria#21#3456#Sevilla" → como campos[0] NO es "Jose", SÍ lo copia a temporal
         
        */
    }

}
