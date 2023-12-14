import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase principal de Planet Express App, la práctica de Taller de Programación
 *
 * @author Taller de Progamación
 * @version 1.0
 */
public class PlanetExpress {
    private final int maxPortes;
    private final int maxNaves;
    private final int maxClientes;
    private final int maxEnviosPorCliente;
    private ListaPuertosEspaciales listaPuertosEspaciales;
    private final int maxPuertosEspaciales;
    private ListaNaves listaNaves;
    private ListaClientes listaClientes;
    private ListaPortes listaPortes;
    //creo una variable de tipo
    private ListaEnvios listaEnvios;



    /**
     * TODO: Rellene el constructor de la clase
     *
     * @param maxPuertosEspaciales Máximo número de puertos espaciales que tendrá la lista de puertos espaciales de PlanetExpress App.
     * @param maxNaves             Máximo número de naves que tendrá la lista de naves de PlanetExpress App.
     * @param maxPortes            Máximo número de portes que tendrá la lista de portes de PlanetExpress App.
     * @param maxClientes          Máximo número de clientes que tendrá la lista de clientes de PlanetExpress App.
     * @param maxEnviosPorCliente  Máximo número de envíos por cliente.
     */
    public PlanetExpress(int maxPuertosEspaciales, int maxNaves, int maxPortes, int maxClientes, int maxEnviosPorCliente) {
        this.maxPuertosEspaciales = maxPuertosEspaciales;
        this.maxNaves = maxNaves;
        this.maxPortes = maxPortes;
        this.maxClientes = maxClientes;
        this.maxEnviosPorCliente = maxEnviosPorCliente;


    }


    /**
     * TODO: Metodo para leer los datos de los ficheros específicados en el enunciado y los agrega a
     *  la información de PlanetExpress (listaPuertosEspaciales, listaNaves, listaPortes, listaClientes)
     *
     * @param ficheroPuertos
     * @param ficheroNaves
     * @param ficheroPortes
     * @param ficheroClientes
     * @param ficheroEnvios
     */
    public void cargarDatos(String ficheroPuertos, String ficheroNaves, String ficheroPortes, String ficheroClientes, String ficheroEnvios) {

        listaPuertosEspaciales = ListaPuertosEspaciales.leerPuertosEspacialesCsv(ficheroPuertos, maxPuertosEspaciales);
        listaNaves =ListaNaves.leerNavesCsv(ficheroNaves,maxNaves);
        listaClientes =ListaClientes.leerClientesCsv(ficheroClientes,maxClientes,maxEnviosPorCliente);
        listaPortes = ListaPortes.leerPortesCsv(ficheroPortes,maxPortes,listaPuertosEspaciales,listaNaves);
       = ListaEnvios.leerEnviosCsv(ficheroEnvios, listaPortes,listaClientes);



    }


    /**
     * TODO: Metodo para almacenar los datos de PlanetExpress en los ficheros .csv especificados
     *  en el enunciado de la práctica
     *
     * @param ficheroPuertos
     * @param ficheroNaves
     * @param ficheroPortes
     * @param ficheroClientes
     * @param ficheroEnvios
     */
    public void guardarDatos(String ficheroPuertos, String ficheroNaves, String ficheroPortes, String ficheroClientes, String ficheroEnvios) {
        listaPuertosEspaciales = ListaPuertosEspaciales.escribirPuertosEspacialesCsv(ficheroPuertos);
        listaNaves =ListaNaves.escribirNavesCsv(ficheroNaves);
        listaClientes =ListaClientes.escribirClientesCsv(ficheroClientes);
        listaPortes = ListaPortes.escribirPortesCsv(ficheroPortes);
        = ListaEnvios.escribirEnviosCsv(ficheroEnvios, listaPortes,listaClientes);//como escrbiir los envios??????

    }

    public boolean maxPortesAlcanzado() {
        return listaPortes.estaLlena();
    }

    public boolean insertarPorte(Porte porte) {
        return listaPortes.insertarPorte(porte);
    }

    public boolean maxClientesAlcanzado() {
        return listaClientes.estaLlena();
    }

    public boolean insertarCliente(Cliente cliente) {
        return listaClientes.insertarCliente(cliente);
    }

    /**
     * TODO: Metodo para buscar los portes especificados tal y como aparece en el enunciado de la práctica,
     *  Devuelve una lista de los portes entre dos puertos espaciales con una fecha de salida solicitados por teclado
     *  al usuario en el orden y con los textos establecidos (tomar como referencia los ejemplos de ejecución en el
     *  enunciado de la prática)
     *
     * @param teclado
     * @return
     */
    public ListaPortes buscarPorte(Scanner teclado) {


        return listaPortes.buscarPortes(codigoOrigen, codigoDestino, fecha);
    }


    /**
     * TODO: Metodo para contratar un envio tal y como se indica en el enunciado de la práctica. Se contrata un envio para un porte
     *  especificado, pidiendo por teclado los datos necesarios al usuario en el orden y con los textos (tomar como referencia los
     *  ejemplos de ejecución en el enunciado de la prática)
     *
     * @param teclado
     * @param rand
     * @param porte
     */
    public void contratarEnvio(Scanner teclado, Random rand, Porte porte) {
        if (porte != null) {


        }
    }


    /**
     * TODO Metodo statico con la interfaz del menú de entrada a la App.
     * Tiene que coincidir con las trazas de ejecución que se muestran en el enunciado
     *
     * @param teclado
     * @return opción seleccionada
     */
    public static int menu(Scanner teclado) {
        System.out.println("1. Alta de Porte.");
        System.out.println("2. Alta de Cliente.");
        System.out.println("3. Buscar Porte.");
        System.out.println("4. Mostrar envíos de un cliente.");
        System.out.println("5. Generar lista de envíos.");
        System.out.println("0. Salir.");
//qué poner en la parte donde se supone que hay que poner un mensaje????????''
        return Utilidades.leerNumero (teclado,"Seleccione opción: ",0, 5);
    }

    /**
     * TODO: Método Main que carga los datos de los ficheros CSV pasados por argumento (consola) en PlanetExpress,
     *  llama iterativamente al menú y realiza la opción especificada hasta que se indique la opción Salir. Al finalizar
     *  guarda los datos de PlanetExpress en los mismos ficheros CSV.
     *
     * @param args argumentos de la línea de comandos, recibe **10 argumentos** estrictamente en el siguiente orden:
     *             1. Número máximo de puertos espaciales que tendrá la lista de puertos espaciales de PlanetExpress App.
     *             2. Número máximo de naves que tendrá la lista de naves de PlanetExpress App.
     *             3. Número máximo de portes que tendrá la lita de portes de PlanetExpress App.
     *             4. Número máximo de clientes que tendrá la lista de clientes de PlanetExpress App.
     *             5. Número máximo de envíos por pasajero.
     *             6. Nombre del fichero CSV que contiene la lista de puertos espaciales de PlanetExpress App (tanto para lectura como escritura).
     *             7. Nombre del fichero CSV que contiene la lista de naves de PlanetExpress App (tanto para lectura como escritura).
     *             8. Nombre del fichero CSV que contiene la lista de portes de PlanetExpress App (tanto para lectura como escritura).
     *             9. Nombre del fichero CSV que contiene la lista de clientes de PlanetExpress App (tanto para lectura como escritura).
     *             10. Nombre del fichero CSV que contiene los envíos adquiridos en PlanetExpress App (tanto para lectura como escritura).
     *             En el caso de que no se reciban exactamente estos argumentos, el programa mostrará el siguiente mensaje
     *             y concluirá la ejecución del mismo: `Número de argumentos incorrecto`.
     */
    public static void main(String[] args) {
        if (args.length != 10) {
            System.out.println("Número de argumentos incorrecto");
            return;
        }


        do {
            opcion = menu(teclado);
            switch (opcion) {
                case 1:     // TODO: Alta de Porte


                    break;
                case 2:     // TODO: Alta de Cliente


                    break;
                case 3:     // TODO: Buscar Porte


                    break;
                case 4:     // TODO: Listado de envíos de un cliente

                    break;
                case 5:     // TODO: Lista de envíos de un porte


                    break;
            }
        } while (opcion != 0);
guardarDatos()

    }
}
