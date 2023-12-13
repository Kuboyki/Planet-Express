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
//com diferencias que el mensaje es para dias o el mensaje es para mes o anio????????????'''
        //dia
        do {
            System.out.print(mensaje);//"Ingrese día: "
            dia=teclado.nextInt();
        }while(dia<=0 || dia>31);

        //mes
        if (dia ==31) {
            do {
                System.out.print(mensaje);//"Ingrese mes: "
                mes=teclado.nextInt();

            } while (mes != 1 || mes !=3|| mes != 5|| mes != 7|| mes != 8|| mes != 10|| mes != 12);
            //meses con dia 31: Enero, Marzo, Mayo, Julio, Agosto , Octubre, Diciembre
        }else if(dia==30){
            do {
                System.out.print(mensaje);//"Ingrese mes: "
                mes=teclado.nextInt();

            } while (mes!=4 ||mes!=6||mes!=9||mes!=11 );
            //meses con dia 30 (Abril, Junio, Septiembre, Noviembre)
        }else if(dia==29){
            do {
                System.out.print(mensaje);//"Ingrese mes: "
                mes=teclado.nextInt();
            } while (mes ==2 );
            //El único mes sin día 29 es febrero
        }else if(dia<=28) {
            do {
                System.out.print(mensaje);//"Ingrese mes: "
                mes = teclado.nextInt();
            } while (mes <= 0 || mes > 12);
            //todos los meses tienen dia 28 o menos


            //no valen años bisiestos, no???????????
            //son los años de 2023 para delante, no??????
            //hay que hacer lo de que pasa un año ???????
            do {
                System.out.print(mensaje);//"Ingrese año: "
                anio = teclado.nextInt();
            } while (anio < 2023);

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
    leerFecha(teclado, mensaje);
    //com diferencias el mensaje ????????, como saco las diferentes variables ???o tengo que volver a hacer el metodo de antes ??????

        System.out.print("Introduzca la fecha de salida: ");

            //hora
            do {
                System.out.print(mensaje);//"Ingrese hora: "
                hora = teclado.nextInt();
            } while (hora < 0 || hora > 24);
            //se puede poner así o hay que llamar al enum????

            //minutos
            do {
                System.out.print(mensaje);//"Ingrese minuto: "
                minuto = teclado.nextInt();
            } while (minuto < 0 || minuto > 60);

            //segundo
            do {
                System.out.print(mensaje);//"Ingrese segundo: "
                segundo = teclado.nextInt();
            } while (segundo < 0 || segundo > 60);
        }

        return new Fecha(dia, mes, anio, hora, minuto, segundo);
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
