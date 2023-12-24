//Realizado por Sandra Blázquez Aldea bu0060 y Dong Jinghong IWSIM11

import java.io.*;
import java.util.Scanner;


/**
 * Clase en el que se gestionan en grupo los clientes que hay
 *
 * @author Sandra Blázquez Aldea
 * @author Dong Jinghong
 * @version     1.0
 */
public class ListaClientes {
    private Cliente[] clientes;
    private int ocupacion = 0;
    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad capacidad del array de la lista de clientes que contiene a todos los clientes que hay
     */
    public ListaClientes(int capacidad) {
        this.clientes = new Cliente[capacidad];
    }
    // TODO: Devuelve el número de clientes que hay en la lista de clientes
    public int getOcupacion() {
        for(int i=0;i<clientes.length;i++){
            if(clientes[i]!=null){
                ocupacion++;
            }
        }
        return ocupacion;
    }
    // TODO: ¿Está llena la lista de clientes?
    public boolean estaLlena() {

        return this.getOcupacion()==clientes.length;

    }
	// TODO: Devuelve el cliente dada el indice
    public Cliente getCliente(int i) {
        return clientes[i-1];
    }
    // TODO: Inserta el cliente en la lista de clientes
    public boolean insertarCliente(Cliente cliente) {
    boolean salir = false;
        if(!estaLlena()) {
            for (int i = 0; i < clientes.length; i++) {
                if (clientes[i] == null) {
                    clientes[i] = cliente;
                    salir = true;
                }
            }
             }

        return salir;
    }

    // TODO: Devuelve el cliente que coincida con el email, o null en caso de no encontrarlo
    public Cliente buscarClienteEmail(String email){

        Cliente encontrado=null;
        if (Cliente.correctoEmail(email)) {
            for (int i = 0; i < clientes.length; i++) {
                if (clientes[i] != null) {
                    if (clientes[i].getEmail().equals(email)) {
                        encontrado = clientes[i];
                    }
                }
            }
        }

        return encontrado ;
    }
    /**
     * TODO: Método para seleccionar un Cliente existente a partir de su email, usando el mensaje pasado como argumento
     *  para la solicitud y, siguiendo el orden y los textos mostrados en el enunciado.
     *  La función debe solicitar repetidamente hasta que se introduzca un email correcto
     * @param teclado que hace referencia al teclado físico por el que se introduce el email del cliente al que se quiere seleccionar
     * @param mensaje que sale por pantalla para indicar lo que se tiene que introducir por teclado
     * @return devuelve el cliente que se ha seleccionado o null en el caso de qu eno se haya logrado seleccionar ninguno
     */
    // SON TOTALMENTE DIFERENTE CON EL METODO ANTERIOR
    public Cliente seleccionarCliente(Scanner teclado, String mensaje) {
        String email = Utilidades.leerCadena(teclado, mensaje);//"Email del cliente: "
        while (buscarClienteEmail(email) == null) {
            System.out.println("Email no encontrado.");

            email = Utilidades.leerCadena(teclado, mensaje);//"Email del cliente: "
        }
        return buscarClienteEmail(email);
    }
    /**
     * TODO: Método para guardar la lista de clientes en un fichero .csv, sobreescribiendo la información del mismo
     *  fichero
     * @param fichero que corresponde al nombre del fichero en el que se va a sobreescribir la información que contenga
     * @return devuleve true o false dependiendo de si se ha podido escribir o no en el fichero
     */
    public boolean escribirClientesCsv(String fichero) {
        PrintWriter pw = null;
        boolean escribir = false;
        Cliente cliente;
        try {
            pw = new PrintWriter(new FileWriter(fichero,false));

            for (int i =0; i<clientes.length; i++){
                cliente = clientes[i];
                pw.println(cliente.getNombre()+";"+cliente.getApellidos()+";"+cliente.getEmail()+";"+cliente.getEnvio(i));
            }
            pw.close();
            escribir = true;
        } catch (FileNotFoundException e) {
            System.out.println("Fichero " + fichero + " no encontrado" + e.getMessage());
        }catch (IOException e){

            System.out.println("Error de escritura en fichero " +fichero+e.getMessage());
        } finally {
            if (pw != null){
                pw.close();
            }
        }
        return escribir;
    }

    /**
     * TODO: Genera una lista de Clientes a partir del fichero CSV, usando los límites especificados como argumentos
     *  para la capacidad de la lista y el número de billetes máximo por pasajero
     * @param fichero que corresponde al nombre del fichero del cual se va a leer la información que contenga
     * @param capacidad del objeto listaClientes que corresponde a la lista de clientes
     * @param maxEnviosPorCliente maxima cantidad de envíos por cliente posibles
     * @return lista de clientes leída
     */
    public static ListaClientes leerClientesCsv(String fichero, int capacidad, int maxEnviosPorCliente) {
        ListaClientes listaClientes = new ListaClientes(capacidad);
        BufferedReader sc = null;
        try {
            sc=new BufferedReader(new FileReader(fichero));
            String cadena;
            while ((cadena= sc.readLine()) != null) {
                String[] dato =cadena.split("");
Cliente cliente = new Cliente(dato[0] ,dato[1],dato[2],maxEnviosPorCliente);
listaClientes.insertarCliente(cliente);
            }
        }catch(FileNotFoundException e){
            System.out.print("Fichero "+fichero+" no encontrado"+ e.getMessage());
        }catch(IOException e){
            System.out.print("Error de lectura de fichero "+e.getMessage());
       }finally {
            if (sc != null) {
                try{
                    sc.close();
                } catch (IOException e) {
                    System.out.println("Error de cierre de fichero "
                            + e.getMessage());
                }
            }
        }



        return listaClientes;
    }
}