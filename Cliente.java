import java.util.Scanner;

/**
 * Clase en el que se gestionan y se realizan las acciones correspondientes a los clientes
 *
 * @author Taller de Programación
 * @version 1.0
 */
public class Cliente {
    private final ListaEnvios listaEnvios;
    private final String nombre;
    private final String apellidos;
    private final String email;

    /**
     * Constructor of the class
     *
     * @param nombre    Nombre del cliente
     * @param apellidos Apellidos del cliente
     * @param email     Email del cliente
     * @param maxEnvios Número máximo de envíos que puede tener el cliente
     */
    public Cliente(String nombre, String apellidos, String email, int maxEnvios) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.listaEnvios = new ListaEnvios(maxEnvios);
    }

    /**
     * Función para saber el nombre del cliente
     * @return el nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *Función para saber el apellido del cliente
     * @return el apellido del cliente
     */

    public String getApellidos() {
        return apellidos;
    }

    /**
     *Función para saber el email del cliente
     * @return el email del cliente
     */

    public String getEmail() {
        return email;
    }

    // TODO: Texto que debe generar: Zapp Brannigan, zapp.brannigan@dop.gov

    /**
     *
     * @return Imprime los datos del cliente en cierto formato
     */
    public String toString() {
        return (getNombre() + " " + getApellidos() + ", " + getEmail());
    }

    /**
     *
     * @return devuleve el maximo de envios posibles
     */

    // TODO: Devuelve un booleano que indica si se ha alcanzado el número máximo de envíos
    public boolean maxEnviosAlcanzado() {
        return listaEnvios.estaLlena();
    }
    /**
     *
     * @return Devuelve el envio que corresponde con la posición que se pasa como argumento
     */

    // TODO: Devuelve un envío en función de su posición
    public Envio getEnvio(int i) {
        return listaEnvios.getEnvio(i);
    }
    /**
     * Devuelve la lista de envios
     * @return Devuelve la lista de envios
     */
    public ListaEnvios getListaEnvios() {
        return listaEnvios;
    }

    /**
     * Añade un envio al cliente
     * @return  si añade el envío correctamente devuelve un true, si no, un false
     */
    // TODO: Añade un envío al cliente
    public boolean aniadirEnvio(Envio envio) {
        boolean aniadido = false;
        if (!maxEnviosAlcanzado()) {
            envio.getCliente().listaEnvios.insertarEnvio(envio);
            aniadido = true;
        }else{
            System.out.println("No es posible añadir más envíos");
        }
        return aniadido;
    }
    /**
     * Busca un envio según el localizador
     * @return  devuelve el envío correspondiente
     */

    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);
    }

    // TODO: Elimina el envío de la lista de envíos del pasajero
    /**
     * Cancela un envio según el localizador
     * @return  devuelve true si lo ha eliminado correctamente
     */
    public boolean cancelarEnvio(String localizador) {
        return listaEnvios.eliminarEnvio(localizador);
    }
    /**
     * Muestra la lista de envíos
     * @return  devuelve la lista de envíos
     */

    public void listarEnvios() {
        listaEnvios.listarEnvios();
    }

    // Encapsula la funcionalidad seleccionarEnvio de ListaEnvios
    // Que es esto de encapsula creo que no lo hemos dado.
    /**
     * Está seleccionando el envío que se introduce por teclado
     * @return  devuelve el envío correspondiente
     */
    public Envio seleccionarEnvio(Scanner teclado, String mensaje) {
        return listaEnvios.seleccionarEnvio(teclado, mensaje);
    }

    /**
     * TODO: Método estático para crear un nuevo cliente "no repetido", se pide por teclado los datos necesarios
     * al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado
     * La función tiene que solicitar repetidamente los parámetros hasta que sean correctos
     *
     * @param teclado nos sirve como referencia al teclado físico, para poder introducir los nombres y datos del cliente
     * @param clientes que representa la lista de clientes donde están todos los clientes
     * @param maxEnvios que representa el números máximo de envios
     * @return Cliente, que es el nuevo cliente que se ha creado
     */
    public static Cliente altaCliente(Scanner teclado, ListaClientes clientes, int maxEnvios) {
        Cliente nuevoCliente;
        String nombre =Utilidades.leerCadena(teclado, "Nombre: ");
        String apellidos =  Utilidades.leerCadena(teclado, "Apellidos: ");
        String email =  Utilidades.leerCadena(teclado, "Email: ");
        while (!correctoEmail(email)){
            System.out.println("Email no encontrado.");
             nombre =Utilidades.leerCadena(teclado, "Nombre: ");
             apellidos =  Utilidades.leerCadena(teclado, "Apellidos: ");
             email =  Utilidades.leerCadena(teclado, "Email: ");

        }

         nuevoCliente = new Cliente(nombre,apellidos,email,maxEnvios);

        if (clientes.buscarClienteEmail(email) !=null ) {
            //si no da null, significa que lo ha encontrado, y por tatno ya existe dicho cliente
            System.out.println("Este cliente ya existe");
            altaCliente(teclado,clientes,maxEnvios);
        } else if (clientes.buscarClienteEmail(email) ==null ){ //si no lo ha enocntrado y hay hueco, si se puede dar de alta un nuevo cliente
            System.out.println("Cliente con email " + nuevoCliente.getEmail() + " creado correctamente");
            clientes.insertarCliente(nuevoCliente); //se añade dicho objeto a la lista
        }
        return nuevoCliente;
    }

    /**
     * TODO: Metodo para comprobar que el formato de email introducido sea correcto
     *
     * @param email que corresponde al email del cliente
     * @return si es correcto el email introducido devuelve un true, si no, un false
     */
    public static boolean correctoEmail(String email) {
        boolean formatoCorrecto = false;
        if ((email.endsWith("@planetexpress.com")) || email.endsWith("@upm.es")) {
            formatoCorrecto = true;
        }
        return formatoCorrecto;

    }
}
