import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
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
     * @param id
     * @param nave
     * @param origen
     * @param muelleOrigen
     * @param salida
     * @param destino
     * @param muelleDestino
     * @param llegada
     * @param precio
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
    }

    public String getID() {
        return id;
    }

    public Nave getNave() {
        return nave;
    }

    public PuertoEspacial getOrigen() {
        return origen;
    }

    public int getMuelleOrigen() {
        return muelleOrigen;
    }

    public Fecha getSalida() {
        return salida;
    }

    public PuertoEspacial getDestino() {
        return destino;
    }

    public int getMuelleDestino() {
        return muelleDestino;
    }

    public Fecha getLlegada() {
        return llegada;
    }

    public double getPrecio() {
        return precio;
    }

    // TODO: Devuelve el número de huecos libres que hay en el porte
    public int numHuecosLibres() {
        int huecosLibres = 0;
        for (int i = 0; i < huecos.length; i++) {
            for (int j = 0; j < huecos.length; j++) {
                if (huecos[i] == null && huecos[j] == null) {
                    huecosLibres++;
                }
            }
        }//es así o con listaEnvios.buscarEnvio(localizador).getFila(); como en el metodo de desocupar
        return huecosLibres;
    }

    // TODO: ¿Están llenos todos los huecos?
    public boolean porteLleno() {
        boolean vacio = false;
        for (int i = 0; i < huecos.length; i++) {
            for (int j = 0; j < huecos.length; j++) {
                if (huecos[i] == null && huecos[j] == null) {
                    vacio = true;
                    return vacio;

                }
            }//es así o con listaEnvios.buscarEnvio(localizador).getFila(); como en el metodo de desocupar
        }
        return vacio;
    }

    // TODO: ¿Está ocupado el hueco consultado?
    public boolean huecoOcupado(int fila, int columna) {
        boolean lleno = false;
        if (huecos[fila][columna] == true) {
            lleno = true;
            return lleno;
        } else {
            return lleno;
        }//es así o con listaEnvios.buscarEnvio(localizador).getFila(); como en el metodo de desocupar
    }

    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);
    }

    /**
     * TODO: Devuelve el objeto Envio que corresponde con una fila o columna,
     *
     * @param fila
     * @param columna
     * @return el objeto Envio que corresponde, o null si está libre o se excede en el límite de fila y columna
     */
    public Envio buscarEnvio(int fila, int columna) {
//está bien asñi ???
        if(listaEnvios.buscarEnvio(getID(),fila,columna){
            return listaEnvios.buscarEnvio(getID(), fila, columna);
        }
        return null;
    }


    /**
     * TODO: Método que Si está desocupado el hueco que indica el envio, lo pone ocupado y devuelve true,
     *  si no devuelve false
     *
     * @param envio
     * @return
     */
    public boolean ocuparHueco(Envio envio) {
        boolean ocupado = false;
        if (desocuparHueco(envio.getLocalizador()) == true) {
            ocupado = true;
        }
        return ocupado;
    }


    /**
     * TODO: A través del localizador del envio, se desocupará el hueco
     *
     * @param localizador
     * @return
     */
    public boolean desocuparHueco(String localizador) {
        boolean salir = false;
        int fila = listaEnvios.buscarEnvio(localizador).getFila();
        int columna = listaEnvios.buscarEnvio(localizador).getColumna();

        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                if (huecos[i][j] == huecos[fila][columna]) {
                    huecos[i][j] = Boolean.parseBoolean(null);
                    salir = true;
                }
            }
        }
        return salir;
    }

    /**
     * TODO: Devuelve una cadena con información completa del porte
     *
     * @return ejemplo del formato -> "Porte PM0066 de Gaia Galactic Terminal(GGT) M5 (01/01/2023 08:15:00) a
     * Cidonia(CID) M1 (01/01/2024 11:00:05) en Planet Express One(EP-245732X) por 13424,56 SSD, huecos libres: 10"
     */
    public String toString() {
        return "Porte " + generarID(new Random()) + " de " + origen.getNombre() + origen.getCodigo() + " M" + origen.getMuelles() + " " + salida.toString() + " a " + destino.getNombre() + destino.getCodigo() + " M" + destino.getMuelles() + " " + llegada.toString() + nave.toStringSimple()
        " por";
        //return "Porte " + PM0066 + " de " + Gaia Galactic Terminal +" (" GGT ") " + M5 + " (" 01 / 01 / 2023 08:15:00
        //") a " + Cidonia "(" CID ") " M1 " (" 01 / 01 / 2024 11:00:05 ") en " + Planet Express One +"(" + EP - 245732
        //X + ") por " + 13424, 56 SSD ", huecos libres: " + numHuecosLibres();
    }

    /**
     * TODO: Devuelve una cadena con información abreviada del vuelo
     *
     * @return ejemplo del formato -> "Porte PM0066 de GGT M5 (01/01/2023 08:15:00) a CID M1 (01/01/2024 11:00:05)"
     */
    public String toStringSimple() {
        //return "Porte " PM0066 " de " GGT M5 " (" 01 / 01 / 2023 08:15:00 ") a " CID M1 " (" 01 / 01 / 2024 11:00:05
        //")";
        //return "Porte "
    }

    /**
     * TODO: Devuelve true si el código origen, destino y fecha son los mismos que el porte
     *
     * @param codigoOrigen
     * @param codigoDestino
     * @param fecha
     * @return
     */
    public boolean coincide(String codigoOrigen, String codigoDestino, Fecha fecha) {
        if (origen.getCodigo().equals(codigoOrigen) && destino.getCodigo().equals(codigoDestino)) {
            if (salida.getDia() == fecha.getDia() && salida.getMes() == fecha.getMes() && salida.getAnio() == fecha.getAnio()) {
                return true;
            }
            return false;
        }
        return false;
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
        System.out.print("     *");
        for (char c = 'A'; c < 'A' + huecos.length; c++) {
            System.out.print(" " + c);
        }
        System.out.println();

        for (int i = 0; i < huecos.length; i++) {
            System.out.print("     " + (i + 1) + " [");
            for (int j = 0; j < huecos[i].length; j++) {
                if (huecos[i][j] != null) {
                    System.out.print("X");
                } else {
                    System.out.println(" ");
                    if (j < huecos[i].length - 1) {
                        System.out.print("][");
                    } else {
                        System.out.print("]");
                    }
                }
                System.out.println();
            }
        }
    }
    /**
     * TODO: Devuelve true si ha podido escribir en un fichero la lista de envíos del porte, siguiendo las indicaciones
     *  del enunciado
     *
     * @param fichero
     * @return
     */
    public boolean generarListaEnvios(String fichero) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(fichero);
            pw.println();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }


    /**
     * TODO: Genera un ID de porte. Este consistirá en una cadena de 6 caracteres, de los cuales los dos primeros
     *  serán PM y los 4 siguientes serán números aleatorios.
     *  NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
     *
     * @param rand
     * @return ejemplo -> "PM0123"
     */
    public static String generarID(Random rand) {
        StringBuilder idPorte = new StringBuilder("PM");
        for (int i = 0; i < 4; i++) {
            idPorte.append(rand.nextInt(10));
        }
        return null;
    }

    /**
     * TODO: Crea y devuelve un objeto Porte de los datos que selecciona el usuario de puertos espaciales
     *  y naves y la restricción de que no puede estar repetido el identificador, siguiendo las indicaciones
     *  del enunciado.
     *  La función solicita repetidamente los parametros hasta que sean correctos
     *
     * @param teclado
     * @param rand
     * @param puertosEspaciales
     * @param naves
     * @param portes
     * @return
     */
    public static Porte altaPorte(Scanner teclado, Random rand, ListaPuertosEspaciales puertosEspaciales,
                                  ListaNaves naves, ListaPortes portes){
            String origen =  Utilidades.leerCadena(teclado,"Ingresar código de puerto Origen: ");
            int muelleo = Utilidades.leerNumero(teclado,"Ingresar el muelle de Origen (1 - 4): ",1,4);
            String destino =  Utilidades.leerCadena(teclado,"Ingrese código de puerto Destino: ");
            int muelled =  Utilidades.leerNumero(teclado,"Ingrese Terminal Destino (1 - 6):",1,6);
        return new Porte(rand,naves,origen,muelleo,destino,muelled);
        System.out.println("Ingrese código de puerto Destino");
        return null;
    }
}