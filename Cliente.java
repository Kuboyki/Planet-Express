import java.util.Scanner;
/**
 * Description of the class
 *
 * @author Taller de Programación
 * @version     1.0
 */
public class Cliente {
    private final ListaEnvios listaEnvios;
    private final String nombre;
    private final String apellidos;
    private final String email;

    /**
     * Constructor of the class
     *
     * @param nombre Nombre del cliente
     * @param apellidos Apellidos del cliente
     * @param email Email del cliente
     * @param maxEnvios Número máximo de envíos que puede tener el cliente
     */
    public Cliente(String nombre, String apellidos, String email, int maxEnvios) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.listaEnvios = new ListaEnvios(maxEnvios);
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public String getEmail() {
        return email;
    }
    // TODO: Texto que debe generar: Zapp Brannigan, zapp.brannigan@dop.gov
    public String toString() {
        return (getNombre()+" "+getApellidos()+", "+getEmail());
    }
    // TODO: Devuelve un booleano que indica si se ha alcanzado el número máximo de envíos
    public boolean maxEnviosAlcanzado() {
        if (listaEnvios.estaLlena()==true){
            return true;
        }else{
            return false;
        }
       // return listaEnvios.estaLlena();
    }
    // TODO: Devuelve un envío en función de su posición
    public Envio getEnvio(int i) {
        return listaEnvios.getEnvio(i);
    }
    public ListaEnvios getListaEnvios() {
        return listaEnvios;
    }
    // TODO: Añade un envío al cliente
    public boolean aniadirEnvio(Envio envio) {
        if (maxEnviosAlcanzado() == true) {
            System.out.println("No es posible añadir más envíos");
        } else if (){ //com se añade un envío al cliente ????????????????????????
             envio.getCliente().listaEnvios.insertarEnvio(envio);
        }
        return
    }
    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);
    }
    // TODO: Elimina el envío de la lista de envíos del pasajero
    public boolean cancelarEnvio(String localizador) {
        return listaEnvios.eliminarEnvio(localizador);
        //Esto hay que modificarlo??????
    }
    public void listarEnvios() {
        listaEnvios.listarEnvios();
    }
    // Encapsula la funcionalidad seleccionarEnvio de ListaEnvios
    // Que es esto de encapsula creo que no lo hemos dado.
    public Envio seleccionarEnvio(Scanner teclado, String mensaje) {
        return listaEnvios.seleccionarEnvio(teclado, mensaje);
    }
    /**
     * TODO: Método estático para crear un nuevo cliente "no repetido", se pide por teclado los datos necesarios
     * al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado
     * La función tiene que solicitar repetidamente los parámetros hasta que sean correctos
     * @param teclado
     * @param clientes
     * @param maxEnvios
     * @return Cliente
     */
    // me falta la parte del que se repita
    public static Cliente altaCliente(Scanner teclado, ListaClientes clientes, int maxEnvios) {
        System.out.println("Nombre: ");
        String nombre = teclado.nextLine();
        System.out.println("Apellidos: ");
        String apellidos = teclado.nextLine();
        System.out.println("Email: ");
        String email = teclado.nextLine();
        Cliente nuevoCliente;
        if (clientes.estaLlena()==true) { //si no hay sitio para mas clientes
             System.out.println("No se pueden dar de alta más clientes");
        }else if(clientes.buscarClienteEmail(email) != null){ //si no da null, significa que lo ha encontrado, y por tatno ya existe dicho cliente
             System.out.println("Este cliente ya existe");
        }else{ //si no lo ha enocntrado y hay hueco, si se puede dar de alta un nuevo cliente
            nuevoCliente= new Cliente(nombre, apellidos, email, maxEnvios);//se crea objeto cliente
            clientes.insertarCliente(nuevoCliente); //se añade dicho objeto a la lista
             System.out.println("Cliente con email "+nuevoCliente.getEmail()+ "creado correctamente");
        }
        return ;
        //qué se devuelve???????????????????????'
    }
    /**
     * TODO: Metodo para comprobar que el formato de email introducido sea correcto
     * @param email
     * @return
     */
    //no comprendo la idea de que sea correcto: el formato?
    public static boolean correctoEmail(String email) {
        ListaClientes clientes;
        boolean correcto=false;
        if (email= clientes.buscarClienteEmail(email).getEmail()){ //buscarClienteEmail(email).getNombre()+".planetexpress.com") { //está bien así?????
            correcto=true;
        }
            return correcto;

    }
}
