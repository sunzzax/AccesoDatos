package relacion4;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Programa para trabajar con archivos de texto.
 *
 * @profesorado
 */
public class Ejercicio1 {

    /**
     * Método principal.
     */
    private static String coeficientes = "coeficientes.txt";
    private static String listadoAlumnos = "listadoAlumnos.txt";
    private static String acta = "acta.txt";

    public static void main(String args[]) {
        //******************* EJERCICIO 1 *******************//
        /* Leer el fichero coeficientes.txt que únicamente contiene una línea, 
         * en la que aparecen los valores que utilizaremos para el cálculo de las notas, 
         * separados por el carácter ';' donde:
         * 1. El primer campo, 45, corresponde al porcentaje de la nota teórica.
         * 2. El segundo campo, 55, corresponde al porcentaje de la nota práctica.
         * 3. El tercer campo, 5, es el valor donde comienza la calificación de Aprobado.
         * 4. El cuarto campo, 7, es el valor donde comienza la calificación de Notable.
         * 5. El quinto campo, 8.5, es el valor donde comienza el Sobresaliente.
         */
        String linea;
        String contenido[];
        // Variables para almacenar los coeficientes
        double porcentajeTeoria = 0;
        double porcentajePractica = 0;
        double aprobado = 0;
        double notable = 0;
        double sobresaliente = 0;

        System.out.println("lectura de coeficientes:");

        try (BufferedReader br = new BufferedReader(new FileReader(coeficientes))) {
            linea = br.readLine();
            if (linea != null) {
                
                contenido = linea.split(";");
                
                porcentajeTeoria = Double.parseDouble(contenido[0]); // 45
                porcentajePractica = Double.parseDouble(contenido[1]); // 55
                aprobado = Double.parseDouble(contenido[2]); // 5
                notable = Double.parseDouble(contenido[3]); // 7
                sobresaliente = Double.parseDouble(contenido[4]); // 8.5

                System.out.println("Coeficientes leídos:");
                System.out.println("Porcentaje Teoría: " + porcentajeTeoria);
                System.out.println("Porcentaje Práctica: " + porcentajePractica);
                System.out.println("Nota Aprobado: " + aprobado);
                System.out.println("Nota Notable: " + notable);
                System.out.println("Nota Sobresaliente: " + sobresaliente);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("No se encuentra el archivo de coeficientes");
        } catch (IOException ex) {
            System.err.println("Error al leer el archivo de coeficientes");
        }

        //******************* EJERCICIO 2 *******************//
        /* Leer cada uno de los alumnos que se almacenan en el fichero de texto 
         * listadoAlumnos.txt y obtener sus datos, que se separan por el carácter ";"
         * Formato: Nombre Apellidos;DNI;NotaTeoria;NotaPractica
         */
        System.out.println("\nlectura de listadoAlumnos:");

        //******************* EJERCICIO 3 *******************//
        /* Escribir en el fichero acta.txt cada alumno junto con sus datos calculados
         * siguiendo el formato mostrado en la imagen del enunciado:
         * - Usar los coeficientes leídos para calcular la nota final
         * - Determinar la nota del acta (Suspenso, Aprobado, Notable, Sobresaliente)
         * - Escribir toda la información en el archivo
         */
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(acta))) {
            // Escribir la cabecera en el acta
            bw.write("Nombre y apellidos \t DNI \t Nota teoría \t Nota práct.\t Nota final \t Nota acta\n");

            // Leer alumnos y escribir en el acta
            try (BufferedReader br = new BufferedReader(new FileReader(listadoAlumnos))) {
                while ((linea = br.readLine()) != null) {
                    contenido = linea.split(";");

                    // Obtener los datos del alumno
                    String nombre = contenido[0];
                    String dni = contenido[1];
                    double notaTeoria = Double.parseDouble(contenido[2]);
                    double notaPractica = Double.parseDouble(contenido[3]);

                    // Calcular nota final usando los coeficientes del ejercicio 1
                    double notaFinal = calcularNotaFinal(notaTeoria, notaPractica,
                            porcentajeTeoria, porcentajePractica);

                    // Determinar nota del acta usando los umbrales del ejercicio 1
                    String notaActa = calcularNotaActa(notaFinal, aprobado, notable, sobresaliente);

                    // Escribir en el acta con el formato requerido
                    bw.write(String.format("%s \t %s \t %.2f \t %.2f \t %.2f \t %s \n",
                            nombre, dni, notaTeoria, notaPractica, notaFinal, notaActa));

                    // Mostrar progreso
                    System.out.println("Procesado: " + nombre);
                }
            } catch (FileNotFoundException ex) {
                System.err.println("No se encuentra el archivo de alumnos");
            } catch (IOException ex) {
                System.err.println("Error al leer el archivo de alumnos");
            }

        } catch (IOException ex) {
            System.err.println("Error al escribir en el acta");
        }
    }

    /**
     * Método auxiliar para calcular la nota final según los coeficientes
     *
     * @param notaT Nota de teoría que ha obtenido el alumno.
     * @param notaP Nota de las prácicas del alumno.
     * @param cT Coeficiente de lo que puntúa la teoría.
     * @param cP Coeficiente de lo que puntúan las prácticas.
     * @return Nota final del alumno.
     */
    public static double calcularNotaFinal(double notaT, double notaP, double cT, double cP) {
        return (notaT * cT) / 100 + (notaP * cP) / 100;
    }

    /**
     * Método auxiliar para determinar la nota cualitativa según los umbrales
     *
     * @param nota Nota final de un alumno.
     * @param aprobado Valor umbral entre el suspenso y el aprobado.
     * @param notable Valor umbral entre el aprobado y el notable.
     * @param sobresaliente Valor umbral entre el notable y el sobresaliente.
     * @return Nota del acta (Suspenso, Aprobado, Notable o Sobresaliente).
     */
    public static String calcularNotaActa(double nota, double aprobado, double notable, double sobresaliente) {
        if (nota < aprobado) {
            return "Suspenso";
        } else if (nota < notable) {
            return "Aprobado";
        } else if (nota < sobresaliente) {
            return "Notable";
        } else {
            return "Sobresaliente";
        }
    }
}
