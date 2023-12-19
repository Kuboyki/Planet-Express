import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.IllegalFormatCodePointException;
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
        boolean llena = false;
        if(getOcupacion()==clientes.length){
            llena = true;
        }
        return llena;
    }
	// TODO: Devuelve el cliente dada el indice
    public Cliente getCliente(int i) {
        return clientes[i-1];
    }
    // TODO: Inserta el cliente en la lista de clientes
    public boolean insertarCliente(Cliente cliente) {
    boolean salir = false;
        if(estaLlena()==false){
           do{
               for (int i=0;i<clientes.length;i++){
                   if(clientes[i]== null){
                       clientes[i]=cliente;
                       salir=true;
                   }
               }
           }while (salir==false);
        }
        return salir;
    }
    /*public boolean insertarCliente(Cliente cliente) {
        estaLlena();
        if(estaLlena()==true){
            for(int i=0;i<clientes.length;i++){
                if (clientes[i]==null){
                    clientes[i]=cliente;
                    return true;
                }
            }
        }
        return false;

    }
     */
    // TODO: Devuelve el cliente que coincida con el email, o null en caso de no encontrarlo
    public Cliente buscarClienteEmail(String email) {
        //IMPORTANTE
        //he modificado lo de return por encontrdo, no sé si está mejor así o no ---PREGUNTAR
        Cliente encontrado=null;
        for(int i=0;i<clientes.length;i++){
            if(clientes[i].getEmail().equals(email)){
                encontrado= clientes[i]; //return clientes[i];
            }
        }
        return encontrado ; //return null;
    } //no hay que poner si no se ha enocntrado que no se ha econtrado ???? no hay ejemplo
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
        Utilidades.leerCadena(teclado, "Email del cliente: ");
        String email = teclado.nextLine();
        do{
            if (email != mensaje){
                System.out.println("Email no encontrado.");
            }
            Utilidades.leerCadena(teclado, "Email del cliente: ");
             email = teclado.nextLine();
        }while(email != mensaje);

        //QUÉ HACE EL MENSAJE AQUÍ????????---- ME HE OLVIDADO

        return buscarClienteEmail(email);


       /* while (true){
            System.out.println(mensaje);
            Utilidades.leerCadena(teclado, "Email del cliente: ");
            String email = teclado.nextLine();
            Cliente cliente =buscarClienteEmail(email);
            if(cliente ==null){
                System.out.println("Email incorrecto");
            }
            return cliente;
        }*/
    }
    // No entiendo la parte de fichero hay que preguntar y hacer lo en clase. SOBREESCRIBIR ESTA EN EL TEMA 6 PAG 16
    /**
     * TODO: Método para guardar la lista de clientes en un fichero .csv, sobreescribiendo la información del mismo
     *  fichero
     * @param fichero
     * @return
     */
    public boolean escribirClientesCsv(String fichero) {

        try {
        } catch (FileNotFoundException e) {
            return false;
        } finally {

        }
        return true;
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

        try {
            salida = new PrintWriter(new FileOutputStream(fichero));

        } catch (FileNotFoundException e) {
            return null;
        }
        //añado excepciones
        catch(FileNotFoundException e){
            System.out.print("Fichero "+ficheroPuertos+" no encontrado"+ e.getMessage());
        }catch(IOException e){
            System.out.print("Error de lectura de fichero "+e.getMessage());
        }catch(IOException e) {
            System.out.print("Error de escritura en fichero " + e.getMessage());
        }finally {
            if (salida != null) {
                try {
                    salida.close();
                } catch (IOException e) {
                    System.out.println("Error de cierre de fichero "
                            + e.getMessage());
                }
            }
        }
        return listaClientes;
    }
}