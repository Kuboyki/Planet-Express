import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
    boolean llena = false;
        if(estaLlena()==false){
           llena = true;
           do{
               for (int i=0;i<clientes.length;i++){
                   if(clientes[i]== null){
                       clientes[i]=cliente;
                       llena=true;
                   }
               }
           }while (llena==true);
        }
        return llena;
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
        for(int i=0;i<clientes.length;i++){
            if(clientes[i].getEmail().equals(email)){
                return clientes[i];
            }
        }
        return null;
    }
    /**
     * TODO: Método para seleccionar un Cliente existente a partir de su email, usando el mensaje pasado como argumento
     *  para la solicitud y, siguiendo el orden y los textos mostrados en el enunciado.
     *  La función debe solicitar repetidamente hasta que se introduzca un email correcto
     * @param teclado
     * @param mensaje
     * @return
     */
    //Error de no comprender el el enunciado y del ejemplo del enunciado.
    public Cliente seleccionarCliente(Scanner teclado, String mensaje) {
        String email = teclado.nextLine();
        do{
            buscarClienteEmail(email);
                if (buscarClienteEmail(email)==null){
                    System.out.println("Email no encontrado.");
                }
        }while (buscarClienteEmail(email)!=null);
        Cliente cliente = null;
        return cliente;
    }
    // No entiendo la parte de fichero hay que preguntar y hacer lo en clase.
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
            salida = new PrintWriter(new FileOutputStream("ficheroClientes.csv"));

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