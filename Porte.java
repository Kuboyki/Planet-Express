
import java.io.FileNotFoundException;
import java.io.IOException;
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
       /* boolean vacio = false;
        for (int i = 0; i < huecos.length; i++) {
            for (int j = 0; j < huecos.length; j++) {
                if (huecos[i] == null && huecos[j] == null) {
                    vacio = true;
                    return vacio;

                }
            }*/
        boolean lleno = false;
        if (numHuecosLibres() == 0) {
            lleno = true;
        }
        return lleno; //vacio
    }

    // TODO: ¿Está ocupado el hueco consultado?
    public boolean huecoOcupado(int fila, int columna) {
        boolean ocupado = false;
        if (huecos[fila][columna]) { //está bien ??
            ocupado = true;
        }
        return ocupado;
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
    public Envio buscarEnvio(int fila, int columna) { //está bien así??
        return buscarEnvio(fila, columna);
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

        if (!huecoOcupado(envio.getFila(), envio.getColumna())) {
            for (int i = 0; i < huecos.length; i++) {
                for (int j = 0; j < huecos.length; j++) {
                    if (!huecos[i][j]) {
                        huecos[i][j] = huecos[envio.getFila()][envio.getColumna()];
                    }
                }
            }
            ocupado = true;
        }
        return ocupado;
    }

    int contarFila() {
        int cont = 0;
        for (int i = 0; i < huecos.length; i++) {
            for (int j = 0; j < 1; j++) {
                cont++;
            }
        }
        return cont;
    }

    int contarColumna() {
        return huecos.length;
    }
    //ESTO SE PONE ???????? PARA LA CALSE ENVIOS ALTA ENVISOS ?????


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
                    huecos[i][j] = false;
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
     * @param codigoOrigen
     * @param codigoDestino
     * @param fecha
     * @return
     */
    public boolean coincide(String codigoOrigen, String codigoDestino, Fecha fecha) {
        boolean coincide = false;
        if (getOrigen().getCodigo().equals(codigoOrigen) && getDestino().getCodigo().equals(codigoDestino) &&
                (getSalida().equals(fecha) || getLlegada().equals(fecha))) {

            /*if (salida.getDia() == fecha.getDia() && salida.getMes() == fecha.getMes() && salida.getAnio() == fecha.getAnio()) {
                return true;
            }*/
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
        final String numeros = "123456789"; //se puede hacer asñi suponiendo que no van a haber más de 9 filas ????
        //qué significan los números ?? el numero de huecos libres o el numero de que ?????

        for (int i = 0; i < huecos.length; i++) {
            System.out.print(cadena.charAt(i));
        }
        System.out.println(" ");
        for (int i = 0; i < contarFila(); i++) { //está bien así ???? en lugar de poner huecos.length
            System.out.print(numeros.charAt(i));
            for (int j = 0; j < contarColumna(); j++) {
                if (huecoOcupado(i, j)) {
                    System.out.print("[X]");
                } else if (!huecoOcupado(i, j)) {
                    System.out.print("[ ]");
//qué había mal en el enunciado de la preunta ????
                }
            }
            System.out.println(" ");
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
        boolean lectura = false;
        ListaPortes portes = null;
        try {
            pw = new PrintWriter(fichero);
            for (int i = 0; i < listaEnvios.getOcupacion(); i++) {
                pw.println(portes.getPorte(i).toStringSimple());
            }
            lectura = true;
        } catch (FileNotFoundException e) {
            System.out.print("Fichero " + fichero + " no encontrado" + e.getMessage());
        } catch (IOException e) {
            System.out.print("Error de escritura en fichero " + fichero + e.getMessage());

        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return lectura;
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
        return String.valueOf(idPorte);
        //esto esta bien así ?????
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
                                  ListaNaves naves, ListaPortes portes) {

       /* if (portes.estaLlena()) {
            System.out.println("No se pueden dar de alta más vuelos.");
        }*/
        String codigoOrigen = Utilidades.leerCadena(teclado, "Ingrese código de puerto Origen: ");
        boolean salir = false;
        PuertoEspacial puerto1=null;
        do{
            System.out.println("Código de puerto no encontrado.");
             codigoOrigen = Utilidades.leerCadena(teclado, "Ingrese código de puerto Origen: ");
             puerto1 =puertosEspaciales.buscarPuertoEspacial(codigoOrigen);//esto se pone en el main ????
            /*
             PuertoEspacial puerto1 =null;//esto se pone en el main ????

        while(puerto1!=null){
            System.out.println("Código de puerto no encontrado.");
             codigoOrigen = Utilidades.leerCadena(teclado, "Ingrese código de puerto Origen: ");
             puerto1 =puertosEspaciales.buscarPuertoEspacial(codigoOrigen);//esto se pone en el main ????

             */

        }while(puerto1!=null);
        int muelle1 = Utilidades.leerNumero(teclado, "Ingresar el muelle de Origen (1 - 4): ", 1, 4);

        String codigoDestino = Utilidades.leerCadena(teclado, "Ingrese código de puerto Destino: ");
        PuertoEspacial puerto2 =puertosEspaciales.buscarPuertoEspacial(codigoDestino);//esto se pone en el main ????
        while(puerto2==null){
            codigoDestino = Utilidades.leerCadena(teclado, "Ingrese código de puerto Destino: ");
        }
        int muelle2 = Utilidades.leerNumero(teclado, "Ingrese Terminal Destino (1 - 6):", 1, 6);
        naves.toString();
        Nave nave= naves.seleccionarNave(teclado, "Ingrese matricula de la nave; ", puerto1.distancia(puerto2));
        Fecha salidaFecha = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de salida");
        Fecha llegadaFecha = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de llegada");
       /* if (salidaFecha.getAnio() > llegadaFecha.getAnio()) {
            System.out.println("Llegada debe ser posterior a salida.");
        } else {
            if (salidaFecha.getMes() > llegadaFecha.getMes()) {
                System.out.println("Llegada debe ser posterior a salida.");
            } else {
                if (salidaFecha.getDia() > llegadaFecha.getDia()) {
                    System.out.println("Llegada debe ser posterior a salida.");
                } else {
                    if (salidaFecha.getHora() > llegadaFecha.getHora()) {
                        System.out.println("Llegada debe ser posterior a salida.");
                    } else {
                        if (salidaFecha.getMinuto() > llegadaFecha.getMinuto()) {
                            System.out.println("Llegada debe ser posterior a salida.");
                        } else {
                            if (salidaFecha.getSegundo() > llegadaFecha.getSegundo()) {
                                System.out.println("Llegada debe ser porsterior a salida.");
                            }
                        }
                    }
                }
            }
        }*/
        while(llegadaFecha.anterior(salidaFecha)){
            System.out.println("Llegada debe ser posterior a salida.");
             salidaFecha = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de salida");
             llegadaFecha = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de llegada");
        }//está bien así ??
        double precio =Utilidades.leerNumero(teclado, "Ingrese precio de pasaje:", 0, Double.POSITIVE_INFINITY);//está bien esto ??
      Porte nPorte = new Porte(generarID(new Random()),nave, puerto1,muelle1,salidaFecha,puerto2,muelle2,llegadaFecha,precio);
        System.out.println("Porte " + nPorte.getID() + " creado correctamente");
        return nPorte;


    }
}