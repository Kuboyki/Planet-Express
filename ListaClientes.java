import java.io.*;
import java.util.Scanner;


/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class ListaClientes {
    private Cliente[] clientes;
    private int ocupacion = 0;
    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad indica numero de espacio que hay en el array o la lista de cliente.
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
        /*boolean llena = false;
        if(getOcupacion()==clientes.length){
            llena = true;
        }
        return llena;
        */
        return this.getOcupacion()==clientes.length;

    }
	// TODO: Devuelve el cliente dada el indice
    public Cliente getCliente(int i) {
        return clientes[i-1];
    }
    // TODO: Inserta el cliente en la lista de clientes
    public boolean insertarCliente(Cliente cliente) {
    boolean salir = false;
        if(estaLlena()==false) {
            //     do{
            for (int i = 0; i < clientes.length; i++) {
                if (clientes[i] == null) {
                    clientes[i] = cliente;
                    salir = true;
                }
            }
            //    }while (salir==false);
             }

        return salir;
    }

    // TODO: Devuelve el cliente que coincida con el email, o null en caso de no encontrarlo
    public Cliente buscarClienteEmail(String email){

        Cliente encontrado=null;
        if (Cliente.correctoEmail(email)==true) {
            for (int i = 0; i < clientes.length; i++) {
                if (clientes[i].getEmail().equals(email)) {
                    encontrado = clientes[i];
                }
            }
        }
        if (encontrado==null){
            System.out.println("Email no encontrado.");
        }
        return encontrado ;
    }
    /**
     * TODO: Método para seleccionar un Cliente existente a partir de su email, usando el mensaje pasado como argumento
     *  para la solicitud y, siguiendo el orden y los textos mostrados en el enunciado.
     *  La función debe solicitar repetidamente hasta que se introduzca un email correcto
     * @param teclado
     * @param mensaje
     * @return
     */
    // SON TOTALMENTE DIFERENTE CON EL METODO ANTERIOR
    public Cliente seleccionarCliente(Scanner teclado, String mensaje) {
        String email =Utilidades.leerCadena(teclado, mensaje);//"Email del cliente: "
while(buscarClienteEmail(email)==null){
     email =Utilidades.leerCadena(teclado, mensaje);//"Email del cliente: "
}
        return buscarClienteEmail(email);
    }
    /**
     * TODO: Método para guardar la lista de clientes en un fichero .csv, sobreescribiendo la información del mismo
     *  fichero
     * @param fichero
     * @return
     */
    public boolean escribirClientesCsv(String fichero) {
        PrintWriter pw = null;
        boolean close = false;
        try {
            pw = new PrintWriter(new FileWriter(fichero));

            for (int i =0; i<clientes.length; i++){
                pw.println(clientes[i].toString());
            }
            pw.close();
            close = true;
        } catch (FileNotFoundException e) {
            System.out.println("Fichero " + fichero + " no encontrado" + e.getMessage());
        }catch (IOException e){
            //if (close == false) {
            //System.out.println("Error de cierre de fichero "
            //     + e.getMessage());}
            System.out.println("Error de escritura en fichero " +fichero+e.getMessage());
        } finally {
            if (pw != null){
                pw.close();
            }
        }
        return close;
    }

    /**
     * TODO: Genera una lista de Clientes a partir del fichero CSV, usando los límites especificados como argumentos
     *  para la capacidad de la lista y el número de billetes máximo por pasajero
     * @param fichero
     * @param capacidad
     * @param maxEnviosPorCliente
     * @return lista de clientes
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