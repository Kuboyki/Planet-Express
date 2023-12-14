import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version 1.0
 */
public class Utilidades {

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     *
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return int numero
     */
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        int numero;
        do {
            System.out.print(mensaje);
            numero = teclado.nextInt();

        } while (numero < minimo || numero > maximo);
        return numero;
    }


    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     *
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return long numero
     */
    public static long leerNumero(Scanner teclado, String mensaje, long minimo, long maximo) {
        long numero;
        do {
            System.out.print(mensaje);
            numero = teclado.nextLong();

        } while (numero < minimo || numero > maximo);
        return numero;
    }

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     *
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return double numero
     */
    public static double leerNumero(Scanner teclado, String mensaje, double minimo, double maximo) {
        double numero;
        do {
            System.out.print(mensaje);
            numero = teclado.nextDouble();

        } while (numero < minimo || numero > maximo);
        return numero;
    }

    /**
     * TODO: TODO: Solicita una letra repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     *
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return char letra
     */
    public static char leerLetra(Scanner teclado, String mensaje, char minimo, char maximo) {
        char letra;
        do {
            System.out.print(mensaje);
            letra = teclado.next().charAt(0);

        } while (letra < minimo || letra > maximo);
        return letra;
    }


    /**
     * TODO: Solicita una fecha repetidamente hasta que se introduzca una correcta
     *
     * @param teclado
     * @param mensaje
     * @return Fecha
     */
    //fecha de entrada
    public static Fecha leerFecha(Scanner teclado, String mensaje) {
        int dia;
        int mes;
        int anio;
        System.out.print(mensaje); //por ejemplo: fecha de salida
        // imprime mensaje que introduces en el main

        //dia
        dia = leerNumero(teclado, "Ingrese día: ", 1, 31);

        //mes
        mes = leerNumero(teclado, "Ingrese mes: ", 1, 12);

        //hay que hacer lo de que pasa un año ????
        //año
        anio = leerNumero(teclado, "Ingrese año: ", 1900, 3000);
        //como lo pone en la clase fecha

        return new Fecha(dia, mes, anio);
    }


    /**
     * TODO: Solicita una fecha y hora repetidamente hasta que se introduzcan unas correctas
     *
     * @param teclado
     * @param mensaje
     * @return Fecha
     */
    //fecha de salida
    public static Fecha leerFechaHora(Scanner teclado, String mensaje) {
        int dia;
        int mes;
        int anio;
        int hora;
        int minuto;
        int segundo;

        //hora
        hora = leerNumero(teclado, "Ingrese hora: ", 0, 24);
        //se puede poner así o hay que llamar al enum????

        //minutos
        minuto = leerNumero(teclado, "Ingrese minuto: ", 0, 60);

        //segundo
        segundo = leerNumero(teclado, "Ingrese segundo: ", 0, 60);

        return new Fecha(leerFecha(teclado, mensaje).getDia(), leerFecha(teclado, mensaje).getMes(), leerFecha(teclado, mensaje).getAnio(), hora, minuto, segundo);

    }

    /**
     * TODO: Imprime por pantalla el String pasado por parámetro
     *
     * @param teclado
     * @param s
     * @return
     */
    public static String leerCadena(Scanner teclado, String s) {
        System.out.print(s);
        return teclado.next();
    }
}


