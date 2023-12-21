import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
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
     * @param localizador
     * @param porte
     * @param cliente
     * @param fila
     * @param columna
     * @param precio
     */
    public Envio(String localizador, Porte porte, Cliente cliente, int fila, int columna, double precio) {
        this.localizador = localizador;
        this.porte = porte;
        this.cliente = cliente;
        this.fila = fila;
        this.columna = columna;
        this.precio = precio;
    }
    public String getLocalizador() {
        return localizador;
    }
    public Porte getPorte() {
        return porte;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public int getFila() {
        return fila;
    }
    public int getColumna() {
        return columna;
    }
    // TODO: Ejemplos: "1A" para el hueco con fila 1 y columna 1, "3D" para el hueco con fila 3 y columna 4
    public String getHueco() {
        return getFila()+String.valueOf(getColumna())+" para el hueco con fila "+getFila()+" y columna "+getColumna();
    }
    public double getPrecio() {
        return precio;
    }
    //TODO: Texto que debe generar: Envío PM1111AAAABBBBC para Porte PM0066 de GGT M5 (01/01/2023 08:15:00) a CID M1 (01/01/2024 11:00:05) en hueco 6C por 13424,56 SSD
    public String toString() {

        return   "Envío "+getLocalizador()+ " para Porte " +getPorte().getID()+" de "
                +porte.getOrigen()+" "+porte.getMuelleOrigen()+ "("+porte.getSalida() + ") a "
                +porte.getDestino()+" "+ porte.getMuelleDestino()+" ("+porte.getLlegada()+ " " +getHora()+
                ") en hueco "+getFila()+String.valueOf(getColumna())+" por "+getPrecio()+ "SSD";

    }
    // TODO: Cancela este envío, eliminándolo de la lista de envíos del porte y del cliente correspondiente
    public boolean cancelar() {
        boolean cancelar = false;
        String localizador= getLocalizador();
        cliente.cancelarEnvio(localizador);
        porte.desocuparHueco(localizador);
         if (cliente.buscarEnvio(localizador) ==null &&  porte.buscarEnvio(localizador)==null){
             cancelar =true;
         }
         return cancelar;
    }

    /**
     * TODO: Método para imprimir la información de este envío en un fichero que respecta el formato descrito en el
     *  enunciado
     * @param fichero
     * @return Devuelve la información con el siguiente formato como ejemplo ->
     *     -----------------------------------------------------
     *     --------- Factura del envío PM1111AAAABBBBC ---------
     *     -----------------------------------------------------
     *     Porte: PM0066
     *     Origen: Gaia Galactic Terminal (GGT) M5
     *     Destino: Cidonia (CID) M1
     *     Salida: 01/01/2023 08:15:00
     *     Llegada: 01/01/2024 11:00:05
     *     Cliente: Zapp Brannigan, zapp.brannigan@dop.gov
     *     Hueco: 6C
     *     Precio: 13424,56 SSD
     */
    public boolean generarFactura(String fichero) { //yo guardo en el ficheor lo de arriba
        PrintWriter pw = null;
        boolean close = false;

        try {
            pw = new PrintWriter(new FileWriter(fichero));
close=true;
        } catch (FileNotFoundException e) {
            System.out.print("Fichero " + fichero + " no encontrado" + e.getMessage());

        }
        return close;
    }



    /**
     *	TODO: Genera un localizador de envío. Este consistirá en una cadena de 15 caracteres, de los cuales los seis
	 *   primeros será el ID del porte asociado y los 9 siguientes serán letras mayúsculas aleatorias. Ejemplo: PM0123ABCD
     *   NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
     * @param rand
     * @param idPorte
     * @return
     */
    public static String generarLocalizador(Random rand, String idPorte) {
        StringBuilder localizador = new StringBuilder(idPorte);
final String cadena= "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        for (int i =0; i<6; i++ ){
            rand= new Random(); // está indicado que es hasta 27???
           // int posicion = Math.round(rand);
            int posicion = (int) rand;
            char letra = cadena.charAt(posicion);
            localizador.append(letra);

        }
        return  localizador;
    }


    /**
     * TODO: Método para crear un nuevo envío para un porte y cliente específico, pidiendo por teclado los datos
     *  necesarios al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado
     *  La función solicita repetidamente los parámetros hasta que sean correctos
     * @param teclado
     * @param rand
     * @param porte
     * @param cliente
     * @return Envio para el porte y cliente especificados
     */
    public static Envio altaEnvio(Scanner teclado, Random rand, Porte porte, Cliente cliente) {
        ListaPortes.seleccionarPorte(teclado,"Seleccione un porte: ",generarLocalizador(rand, porte.getID()));
        //ListaPortes.seleccionarPorte(teclado,"Seleccione un porte: ", String cancelar);

        char letra=Utilidades.leerLetra(teclado,"¿Comprar billete para un nuevo pasajero (n), o para uno ya existente (e)?",'e','n');
        while(letra != 'n' || letra != 'e') {
            System.out.println("El valor de entrada debe ser 'n' o 'e'");
            letra=Utilidades.leerLetra(teclado,"¿Comprar billete para un nuevo pasajero (n), o para uno ya existente (e)?",'e','n');
        }

        cliente.seleccionarEnvio(teclado,"Email del cliente: ");
        //o era   listaClientes.seleccionarCliente(teclado,"Email del cliente: ");


        int fila=Utilidades.leerNumero(teclado,"Fila del hueco: ",1,porte.getmaxFila());
        int columna=Utilidades.leerNumero(teclado,"Columna del hueco: ",1,getmaxColumna() );

       Utilidades.leerNumero(teclado,"Precio del envío: ", , );

                System.out.println("Envío"+ generarLocalizador(rand, rand.getID())+ "creado correctamente");

        ListaEnvios.aniadirEnviosCsv();
        Envio envio =  ;
        ListaEnvios.insertarEnvio(envio);
return envio;
    }
}