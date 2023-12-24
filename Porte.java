//Realizado por Sandra Blázquez Aldea bu0060 y Dong Jinghong IWSIM11

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase que representa los viajes, los portes
 * @author Sandra Blázquez Aldea
 * @author Dong Jinghong
 * @version 1.0
 */
public class Porte {
    private boolean[][] huecos;
    private String id;
    private Nave nave;
    private PuertoEspacial origen;
    private int muelleOrigen;
    private Fecha salida;
    private PuertoEspacial destino;
    private int muelleDestino;
    private Fecha llegada;
    private double precio;
    private ListaEnvios listaEnvios;

    /**
     * TODO: Completa el constructo de la clase
     *
     * @param id para identeificar el porte
     * @param nave que corresponde al porte
     * @param origen del porte
     * @param muelleOrigen del porte
     * @param salida del porte
     * @param destino del porte
     * @param muelleDestino del porte
     * @param llegada del porte
     * @param precio del porte
     */
    public Porte(String id, Nave nave, PuertoEspacial origen, int muelleOrigen, Fecha salida, PuertoEspacial destino, int muelleDestino, Fecha llegada, double precio) {
        this.id = id;
        this.nave = nave;
        this.origen = origen;
        this.muelleOrigen = muelleOrigen;
        this.salida = salida;
        this.destino = destino;
        this.muelleDestino = muelleDestino;
        this.llegada = llegada;
        this.precio = precio;
        huecos = new boolean[nave.getFilas()][nave.getColumnas()];
        listaEnvios = new ListaEnvios(nave.getFilas() * nave.getColumnas());
    }
    /**
     *
     * @return el identificador del porte
     */
    public String getID() {
        return id;
    }
    /**
     *
     * @return la nave que se usa en el porte
     */
    public Nave getNave() {
        return nave;
    }
    /**
     *
     * @return el puerto inicial del porte
     */
    public PuertoEspacial getOrigen() {
        return origen;
    }
    /**
     *
     * @return  el número de muelles del puerto inicial del porte
     */
    public int getMuelleOrigen() {
        return muelleOrigen;
    }
    /**
     *
     * @return la fecha en la que sale el porte a su destino
     */
    public Fecha getSalida() {
        return salida;
    }
    /**
     *
     * @return el puerto al que se dirige el porte
     */
    public PuertoEspacial getDestino() {
        return destino;
    }
    /**
     *
     * @return el número de muelles del destino al que va el porte
     */
    public int getMuelleDestino() {
        return muelleDestino;
    }
    /**
     *
     * @return la fecha en la que llega el porte a su destino
     */
    public Fecha getLlegada() {
        return llegada;
    }

    /**
     *
     * @return el precio del porte
     */
    public double getPrecio() {
        return precio;
    }

    /**
     *
     * @return el número de huecos libres que hay en el porte
     */
    // TODO: Devuelve el número de huecos libres que hay en el porte
    public int numHuecosLibres() {
        int huecosLibres = 0;
        for (int i = 0; i < huecos.length; i++) {
            for (int j = 0; j < huecos.length; j++) {
                if (!huecos[i][j]) {
                    huecosLibres++;
                }
            }
        }        return huecosLibres;
    }

    /**
     *
     * @return true si la lista de portes está llena o no
     */
    // TODO: ¿Están llenos todos los huecos?
    public boolean porteLleno() {
       return listaEnvios.estaLlena();


    }

    /**
     *
     * @param fila que ocupa el envío
     * @param columna que ocupa el envío
     * @return true si está ocupado el hueco consultado o no
     */
    // TODO: ¿Está ocupado el hueco consultado?
    public boolean huecoOcupado(int fila, int columna) {
        boolean ocupado = false;
        if (huecos[fila-1][columna-1]) {
            ocupado = true;
        }
        return ocupado;
    }

    /**
     *  Función que devuelve el envio que se busca
     * @param localizador con el que se identifica el porte
     * @return el envio que se busca usando el localizador
     */
    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);

    }


    /**
     * TODO: Devuelve el objeto Envio que corresponde con una fila o columna,
     *
     * @param fila que ocupa el envío
     * @param columna que ocupa el envío
     * @return el objeto Envio que corresponde, o null si está libre o se excede en el límite de fila y columna
     */
    public Envio buscarEnvio(int fila, int columna) {
        return listaEnvios.buscarEnvio(id, fila, columna);

    }




    /**
     * TODO: Método que Si está desocupado el hueco que indica el envio, lo pone ocupado y devuelve true,
     *  si no devuelve false
     *
     * @param envio que se quiere introducir en la matriz
     * @return true en caso de que se haya introducido correctamente o false en caso contrario
     */
    public boolean ocuparHueco(Envio envio) {
        boolean ocupado = false;

        if (!huecoOcupado(envio.getFila(), envio.getColumna())) {
            ocupado=true;
            huecos[envio.getFila()-1][envio.getColumna()-1]=true;

        }
        return ocupado;
    }


    /**
     * TODO: A través del localizador del envio, se desocupará el hueco
     *
     * @param localizador con el que se identifica el envío que se quiere eliminar
     * @return true en caso de que se haya eliminado el envío correctamente y false en caso contrario
     */
    public boolean desocuparHueco(String localizador) {
        return listaEnvios.eliminarEnvio(localizador);

    }

    /**
     * TODO: Devuelve una cadena con información completa del porte
     *
     * @return ejemplo del formato -> "Porte PM0066 de Gaia Galactic Terminal(GGT) M5 (01/01/2023 08:15:00) a
     * Cidonia(CID) M1 (01/01/2024 11:00:05) en Planet Express One(EP-245732X) por 13424,56 SSD, huecos libres: 10"
     */
    public String toString() {
        return "Porte " + getID() + " de " + getOrigen().getNombre() + "(" + getOrigen().getCodigo() + ") " + getMuelleOrigen() +
                " (" + getSalida().getDia() + "/" + getSalida().getMes() + "/" + getSalida().getAnio() + " " + getSalida().getHora() + ":"
                + getSalida().getMinuto() + ":" + getSalida().getSegundo() + ") a " + getDestino().getNombre() + " (" + getDestino().getCodigo() + ") " + getMuelleDestino() + " ("
                + getLlegada().getDia() + "/" + getLlegada().getMes() + "/" + getLlegada().getAnio() + " " + getLlegada().getHora() + ":"
                + getLlegada().getMinuto() + ":" + getLlegada().getSegundo() + ") en " + getNave() + "(" + getNave().getMatricula() + ") por"
                + getPrecio() + "SSD, huecos libres: " + numHuecosLibres();
    }

    /**
     * TODO: Devuelve una cadena con información abreviada del vuelo
     *
     * @return ejemplo del formato -> "Porte PM0066 de GGT M5 (01/01/2023 08:15:00) a CID M1 (01/01/2024 11:00:05)"
     */
    public String toStringSimple() {
        return "Porte " + getID() + " de " + getOrigen().getCodigo() + " " + getMuelleOrigen() +
                " (" + getSalida().getDia() + "/" + getSalida().getMes() + "/" + getSalida().getAnio() + " " + getSalida().getHora() + ":"
                + getSalida().getMinuto() + ":" + getSalida().getSegundo() + ") a " + getDestino().getCodigo() + " " + getMuelleDestino() + " ("
                + getLlegada().getDia() + "/" + getLlegada().getMes() + "/" + getLlegada().getAnio() + " " + getLlegada().getHora() + ":"
                + getLlegada().getMinuto() + ":" + getLlegada().getSegundo() + ")";

    }

    /**
     * TODO: Devuelve true si el código origen, destino y fecha son los mismos que el porte
     *
     * @param codigoOrigen que corresponde al aeropuerto desde el que se parte
     * @param codigoDestino que corresponde al aeropuerto al que se llega
     * @param fecha en la que se sale de un aeropuerto a otro
     * @return true en caso de que los portes correspondientes coincidan y false en caso contrario
     */
    public boolean coincide(String codigoOrigen, String codigoDestino, Fecha fecha) {
        boolean coincide = false;
        if (getOrigen().getCodigo().equals(codigoOrigen) && getDestino().getCodigo().equals(codigoDestino) &&
                (getSalida().equals(fecha) || getLlegada().equals(fecha))) {

            coincide = true;
        }
        return coincide;
    }

    /**
     * TODO: Muestra la matriz de huecos del porte, ejemplo:
     *        A  B  C
     *      1[ ][ ][ ]
     *      2[X][X][X]
     *      3[ ][ ][ ]
     *      4[ ][X][ ]
     *     10[ ][ ][ ]
     */
    public void imprimirMatrizHuecos() {

        System.out.print("    ");
        final String cadena = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        final String numeros = "123456789";
        for (int i = 0; i < huecos.length; i++) {
            System.out.print(cadena.charAt(i));
        }
        System.out.println(" ");
        for (int i = 0; i < contarFila(); i++) {
            System.out.print(numeros.charAt(i));
            for (int j = 0; j <contarColumna(); j++) {
                if (huecoOcupado(i, j)) {
                    System.out.print("[X]");
                } else if (!huecoOcupado(i, j)) {
                    System.out.print("[ ]");
                }
            }
            System.out.println(" ");
        }
    }

    public int contarFila() {
      return huecos.length;
    }

    public int contarColumna() {
        return huecos[0].length;
    }

    /**
     * TODO: Devuelve true si ha podido escribir en un fichero la lista de envíos del porte, siguiendo las indicaciones
     *  del enunciado
     *
     * @param fichero que corresponde al fichero en el que se quiere escribir la lista de envios del porte
     * @return true si ha podido escribir en un fichero la lista de envíos del porte y false en caso contrario
     */
    public boolean generarListaEnvios(String fichero) {
        PrintWriter pw = null;

        boolean leer = false;
        PrintWriter salida1 = null;
        try {
            pw = new PrintWriter(new PrintWriter(fichero));
            pw.println("-------------------------------------------------------------------------");
            pw.println("--------- Lista de envíos del porte " + id + " ---------");
            pw.println("-------------------------------------------------------------------------");
            pw.println("Hueco Cliente");
            for (int i = 0; i <= huecos.length - 1; i++) {
                for (int j = 0; j <= huecos.length - 1; j++) {
                    pw.printf(buscarEnvio(i, j).getHueco()+ " "+ buscarEnvio(i, j).getCliente().getNombre()+ " "+
                            buscarEnvio(i, j).getCliente().getApellidos()+ " "+ buscarEnvio(i, j).getCliente().getEmail());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.print("Fichero " + fichero + " no encontrado" + e.getMessage());
        } finally {

        if (salida1 != null) {

                    salida1.close();

            }
        }
        return leer;
    }





    /**
     * TODO: Genera un ID de porte. Este consistirá en una cadena de 6 caracteres, de los cuales los dos primeros
     *  serán PM y los 4 siguientes serán números aleatorios.
     *  NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
     *
     * @param rand numero aleatorio
     * @return ejemplo -> "PM0123"
     */
    public static String generarID(Random rand) {
        String ID = "PM";
        int[] numeros = rand.ints(4, 0, 9).toArray();
        for (int numero : numeros) {
            ID += String.valueOf(numero);
        }
        return ID;
    }


    /**
     * TODO: Crea y devuelve un objeto Porte de los datos que selecciona el usuario de puertos espaciales
     *  y naves y la restricción de que no puede estar repetido el identificador, siguiendo las indicaciones
     *  del enunciado.
     *  La función solicita repetidamente los parametros hasta que sean correctos
     *
     * @param teclado que hace referencia al teclado físico por el que se introduce la identificación de las distintas variables
     * @param rand numero random
     * @param puertosEspaciales para acceder a otros métodos
     * @param naves para acceder a otros métodos
     * @param portes para acceder a otros métodos
     * @return porte creado
     */
    public static Porte altaPorte(Scanner teclado, Random rand, ListaPuertosEspaciales puertosEspaciales, ListaNaves naves, ListaPortes portes) {
      //  String codigoOrigen = Utilidades.leerCadena(teclado, "Ingrese código de puerto Origen: ");
        PuertoEspacial  puerto1 = puertosEspaciales.seleccionarPuertoEspacial(teclado, "Ingrese código de puerto Origen: ");//esto se pone en el main ????
        int muelle1 = Utilidades.leerNumero(teclado, "Ingresar el muelle de Origen (1 - 4): ", 1, 4);
      //  String codigoDestino = Utilidades.leerCadena(teclado, "Ingrese código de puerto Destino: ");
       // PuertoEspacial puerto2 = puertosEspaciales.buscarPuertoEspacial(codigoDestino);//esto se pone en el main ????
        PuertoEspacial puerto2 = puertosEspaciales.seleccionarPuertoEspacial(teclado, "Ingrese código de puerto Destino: ");
        int muelle2 = Utilidades.leerNumero(teclado, "Ingrese Terminal Destino (1 - 6):", 1, 6);
        naves.mostrarNaves();
        Nave nave = naves.seleccionarNave(teclado, "Ingrese matricula de la nave; ", puerto1.distancia(puerto2));
        Fecha salidaFecha = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de salida");
        Fecha llegadaFecha = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de llegada");
        while (llegadaFecha.anterior(salidaFecha) || llegadaFecha.coincide(salidaFecha)) {
            System.out.println("Llegada debe ser posterior a salida.");
            salidaFecha = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de salida");
            llegadaFecha = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de llegada");
        }
        double precio = Utilidades.leerNumero(teclado, "Ingrese precio de pasaje:", 0, Double.POSITIVE_INFINITY);
        Porte nPorte = new Porte(generarID(new Random()), nave, puerto1, muelle1, salidaFecha, puerto2, muelle2, llegadaFecha, precio);
        System.out.println("Porte " + nPorte.getID() + " creado correctamente");
        return nPorte;
    }



    }