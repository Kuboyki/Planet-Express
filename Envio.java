//Realizado por Sandra Blázquez Aldea bu0060 y Dong Jinghong IWSIM11

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase en el que se gestionan y se realizan las acciones correspondientes a los envíos
 *
 * @author Sandra Blázquez Aldea
 * @author Dong Jinghong
 * @version 1.0
 */
public class Envio {

    private String localizador;
    private Porte porte;
    private Cliente cliente;
    private int fila;
    private int columna;
    private double precio;

    /**
     * Constructor of the class
     *
     * @param localizador con el que se identifica al envío
     * @param porte con el que se identifica el porte
     * @param cliente con el que se identifica el cliente
     * @param fila  en la que está situado el envío
     * @param columna en la que está situado el envío
     * @param precio del envío
     */
    public Envio(String localizador, Porte porte, Cliente cliente, int fila, int columna, double precio) {
        this.localizador = localizador;
        this.porte = porte;
        this.cliente = cliente;
        this.fila = fila;
        this.columna = columna;
        this.precio = precio;
    }

    /**
     * Función que devuelve el localizador del envío
     * @return el localizador
     */
    public String getLocalizador() {
        return localizador;
    }
    /**
     * Función que devuelve el porte del envío
     * @return el porte
     */
    public Porte getPorte() {
        return porte;
    }
    /**
     * Función que devuelve el cliente del envío
     * @return el cliente
     */
    public Cliente getCliente() {
        return cliente;
    }
    /**
     * Función que devuelve la fila donde se encuentra el envío
     * @return la fila
     */
    public int getFila() {
        return fila;
    }
    /**
     * Función que devuelve la columna donde se encuentra el envío
     * @return la columna
     */
    public int getColumna() {
        return columna;
    }

    // TODO: Ejemplos: "1A" para el hueco con fila 1 y columna 1, "3D" para el hueco con fila 3 y columna 4
    /**
     *
     * @return devuelve los datos de un envío en un determinado formato
     */
    public String getHueco() {
        return getFila() + String.valueOf(getColumna()) + " para el hueco con fila " + getFila() + " y columna " + getColumna();
    }
    /**
     * FUnción que pretende identificar el precio del envío
     * @return deevuelve el precio
     */
    public double getPrecio() {
        return precio;
    }

    //TODO: Texto que debe generar: Envío PM1111AAAABBBBC para Porte PM0066 de GGT M5 (01/01/2023 08:15:00) a CID M1 (01/01/2024 11:00:05) en hueco 6C por 13424,56 SSD
    /**
     *
     * @return devuelve los datos de un envío en un determinado formato
     */
    public String toString() {

        return "Envío " + getLocalizador() + " para Porte " + getPorte().getID() + " de "
                + porte.getOrigen() + " " + porte.getMuelleOrigen() + "(" + porte.getSalida() + ") a "
                + porte.getDestino() + " " + porte.getMuelleDestino() + " (" + porte.getLlegada() + ") en hueco " + getFila() + String.valueOf(getColumna()) + " por " + getPrecio() + "SSD";

    }
    /**
     * Función que elimina el envío
     * @return devuelve un true si el envío se ha cancelado o un false si no
     */
    // TODO: Cancela este envío, eliminándolo de la lista de envíos del porte y del cliente correspondiente
    public boolean cancelar() {
        boolean cancelar = false;
        String localizador = getLocalizador();
        cliente.cancelarEnvio(localizador);
        porte.desocuparHueco(localizador);
        if (cliente.buscarEnvio(localizador) == null && porte.buscarEnvio(localizador) == null) {
            cancelar = true;
        }
        return cancelar;
    }

    /**
     * TODO: Método para imprimir la información de este envío en un fichero que respecta el formato descrito en el
     *  enunciado
     *
     * @param fichero fichero en el que se imprime la información del envío en cierto formato
     * @return Devuelve la información con el siguiente formato como ejemplo ->
     * -----------------------------------------------------
     * --------- Factura del envío PM1111AAAABBBBC ---------
     * -----------------------------------------------------
     * Porte: PM0066
     * Origen: Gaia Galactic Terminal (GGT) M5
     * Destino: Cidonia (CID) M1
     * Salida: 01/01/2023 08:15:00
     * Llegada: 01/01/2024 11:00:05
     * Cliente: Zapp Brannigan, zapp.brannigan@dop.gov
     * Hueco: 6C
     * Precio: 13424,56 SSD
     */
    public boolean generarFactura(String fichero) { //yo guardo en el ficheor lo de arriba
        PrintWriter pw = null;
        boolean close = false;

        try {
            pw = new PrintWriter(new FileWriter(fichero));
            pw.println(" -----------------------------------------------------");
            pw.println("--------- Factura del envío " + getLocalizador() + " ---------");
            pw.println(" -----------------------------------------------------");
            pw.println("Porte: " + getPorte().getID());
            pw.println("Origen: " + getPorte().getOrigen().getNombre() + " (" + getPorte().getOrigen().getCodigo() + ") " + getPorte().getMuelleOrigen());
            pw.println("Destino: " + getPorte().getDestino().getNombre() + " (" + getPorte().getDestino().getCodigo() + ") " + getPorte().getMuelleDestino());
            pw.println("Salida: " + getPorte().getSalida());
            pw.println("Llegada: " + getPorte().getLlegada());
            pw.println("Cliente: " + getCliente() + ", " + getCliente().getEmail());
            pw.println("Hueco: " + getHueco());
            pw.println("Precio: " + getPrecio() + " SSD");

            System.out.println("Factura generada correctamente");



        } catch (FileNotFoundException e) {
            System.out.print("Fichero " + fichero + " no encontrado" + e.getMessage());
        } catch (IOException e) {
            System.out.print("Error de escritura en fichero " + fichero + e.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
                close = true;
            }
        }
        return close;
    }


    /**
     * TODO: Genera un localizador de envío. Este consistirá en una cadena de 15 caracteres, de los cuales los seis
     *   primeros será el ID del porte asociado y los 9 siguientes serán letras mayúsculas aleatorias. Ejemplo: PM0123ABCD
     *   NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
     *
     * @param rand número random
     * @param idPorte identificación del porte del envío
     * @return el localizador generado de un envío
     */
    public static String generarLocalizador(Random rand, String idPorte) {
        StringBuilder localizador = new StringBuilder(idPorte);
        final String cadena = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        for (int i = 0; i < 6; i++) {
            int posicion = rand.nextInt(26);
            char letra = cadena.charAt(posicion);
            localizador.append(letra);
        }

        return localizador.toString();// String.valueOf(localizador)
    }


    /**
     * TODO: Método para crear un nuevo envío para un porte y cliente específico, pidiendo por teclado los datos
     *  necesarios al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado
     *  La función solicita repetidamente los parámetros hasta que sean correctos
     *
     * @param teclado  nos sirve como referencia al teclado físico, para poder asignar valores a las variables como fila o columna
     * @param rand número random
     * @param porte que se usa para generar el localizador del envío
     * @param cliente que se usa para crear el envío
     * @return Envio para el porte y cliente especificados
     */
    public static Envio altaEnvio(Scanner teclado, Random rand, Porte porte, Cliente cliente) {


        int fila = Utilidades.leerNumero(teclado, "Fila del hueco: ", 1, porte.contarFila());
        int columna = Utilidades.leerNumero(teclado, "Columna del hueco: ", 1, porte.contarColumna());
porte.imprimirMatrizHuecos();
        while (porte.huecoOcupado(fila, porte.contarColumna())){
            fila = Utilidades.leerNumero(teclado, "Fila del hueco: ", 1, porte.contarFila());
            columna = Utilidades.leerNumero(teclado, "Columna del hueco: ", 1, porte.contarColumna());
        }
        double precio = Utilidades.leerNumero(teclado, "Precio del envío: ", 0, Double.MAX_VALUE);
        String localizador= generarLocalizador(rand, porte.getID());

        Envio envio = new Envio(localizador,porte,cliente,fila,columna,precio);

        System.out.println("Envío " + localizador + " creado correctamente");

       cliente.getListaEnvios().insertarEnvio(envio);
       cliente.aniadirEnvio(envio);
       porte.ocuparHueco(envio);


        return envio;
    }
}