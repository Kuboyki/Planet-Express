//Realizado por Sandra Blázquez Aldea bu0060 y Dong Jinghong IWSIM11

import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Sandra Blázquez Aldea
 * @author Dong Jinghong
 * @version 1.0
 */
public class Utilidades {

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     *
     * @param teclado que hace referencia al teclado físico por el que se introduce el número correspondiente
     * @param mensaje que se muestra por pantalla para indicar que se necesita introducir por teclado
     * @param minimo numero mínimo que se permite introducir
     * @param maximo numero máximo que se permite introducir
     * @return numero correspondiente
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
     * @param teclado que hace referencia al teclado físico por el que se introduce el número correspondiente
     * @param mensaje que se muestra por pantalla para indicar que se necesita introducir por teclado
     * @param minimo  numero mínimo que se permite introducir
     * @param maximo numero máximo que se permite introducir
     * @return numero correspondiente
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
     * @param teclado que hace referencia al teclado físico por el que se introduce el número correspondiente
     * @param mensaje que se muestra por pantalla para indicar que se necesita introducir por teclado
     * @param minimo  numero mínimo que se permite introducir
     * @param maximo numero máximo que se permite introducir
     * @return numero correspondiente
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
     * @param teclado que hace referencia al teclado físico por el que se introduce la letra correspondiente
     * @param mensaje que se muestra por pantalla para indicar que se necesita introducir por teclado
     * @param minimo la letra mínima que se puede introducir
     * @param maximo la letra maxima  que se puede introducir
     * @return char letra correspondiente
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
     * @param teclado que hace referencia al teclado físico por el que se introduce la fecha correspondiente
     * @param mensaje que se muestra por pantalla para indicar que se necesita introducir por teclado
     * @return Fecha que corresponde
     */
    //fecha de entrada
    public static Fecha leerFecha(Scanner teclado, String mensaje) {
        int dia;
        int mes;
        int anio;
        System.out.print(mensaje);

        dia = leerNumero(teclado, "Ingrese día: ", 1, 31);

        mes = leerNumero(teclado, "Ingrese mes: ", 1, 12);


        anio = leerNumero(teclado, "Ingrese año: ", 1900, 3000);
        if ((dia > 28 && mes == 2) || (dia == 31 && (mes == 4 || mes == 6 || mes == 9 || mes == 11))) {
            System.out.println("Fecha introducida incorrecta.");
            leerFecha(teclado, mensaje);
        }
        //Febrero= 28 días
        // Abril, Junio, Septiembre, Noviembre= 30 días
        // Enero, Marzo, Mayo, Julio, Agosto , Octubre, Diciembre = 31 días
        return new Fecha(dia, mes, anio);
    }


    /**
     * TODO: Solicita una fecha y hora repetidamente hasta que se introduzcan unas correctas
     *
     * @param teclado que hace referencia al teclado físico por el que se introduce la fecha correspondiente
     * @param mensaje que se muestra por pantalla para indicar que se necesita introducir por teclado
     * @return Fecha que corresponde
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
     * @param teclado que hace referencia al teclado físico por el que se introduce la fecha correspondiente
     * @param s mensaje que se muestra por pantalla para indicar que se necesita introducir por teclado
     * @return la cadena que se itroduce por teclado
     */
    public static String leerCadena(Scanner teclado, String s) {
        System.out.print(s);
        return teclado.next();
    }
}


