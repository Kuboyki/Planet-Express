import java.io.*;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class ListaEnvios {
    private Envio[] envios;

    private int ocupacion = 0;
    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaEnvios(int capacidad) {
		this.envios= new Envio[capacidad];
		
    }
    // TODO: Devuelve el número de envíos que hay en la lista
    public int getOcupacion() {
        for (int i = 0; i < envios.length; i++) {
            if (envios[i] != null){
                ocupacion++;
            }
        }
        return ocupacion;
    }
    // TODO: ¿Está llena la lista de envíos?
    public boolean estaLlena() {
        boolean hayhueco = false;
        for(int i = 0;i< envios.length;i++){
            if(envios[i]==null){
                hayhueco = true;
            }
        }
        return hayhueco;
    }
	//TODO: Devuelve el envio dado un indice
    public Envio getEnvio(int i) {
        return envios[i-1];
    }

    /**
     * TODO: insertamos un nuevo envío en la lista
     * @param envio
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarEnvio(Envio envio) {
        estaLlena();
        if(estaLlena()==true){
            for(int i=0;i< envios.length;i++){
                if (envios[i]==null){
                    envios[i]=envio;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * TODO: Buscamos el envio a partir del localizador pasado como parámetro
     * @param localizador
     * @return el envio que encontramos o null si no existe
     */
    public Envio buscarEnvio(String localizador) {
        for (int i = 0;i< envios.length;i++) {
            if (envios[i].getLocalizador().equals(localizador)) {
                return envios[i];
            }
        }
        return null;
    }

    /**
     * TODO: Buscamos el envio a partir del idPorte, fila y columna pasados como parámetros
     * @param idPorte
     * @param fila
     * @param columna
     * @return el envio que encontramos o null si no existe
     */
    public Envio buscarEnvio(String idPorte, int fila, int columna) {
        for(int i=0;i<envios.length;i++){
            if(envios[i].getPorte().equals(idPorte)){
                if(envios[i].getFila()==fila){
                    if(envios[i].getColumna()==columna){
                        return envios[i];
                    }
                }
            }
        }
        return null;
    }
    /**
     * TODO: Eliminamos un envio a través del localizador pasado por parámetro
     * @param localizador
     * @return True si se ha borrado correctamente, false en cualquier otro caso
     */
    public boolean eliminarEnvio (String localizador) {
        for(int i = 0;i< envios.length;i++){
            if(envios[i].getLocalizador().equals(localizador)){
                envios[i]=null;
                return true;
            }
        }
        return false;
    }

    /**
     * TODO: Muestra por pantalla los Envios de la lista, con el formato que aparece
     * en el enunciado
     */
    // creo que me falta por completar el metodo
    public void listarEnvios() {
        for(int i=0;i< envios.length;i++){
            envios[i].toString();
        }
    }
    /**
     * TODO: Permite seleccionar un Envio existente a partir de su localizador, usando el mensaje pasado como argumento
     *  para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente hasta que se introduzca un localizador correcto
     * @param teclado
     * @param mensaje
     * @return
     */
    // no comprendo mucho la idea de este enunciado
    public Envio seleccionarEnvio(Scanner teclado, String mensaje) {
        for(int i=0;i< envios.length;i++){
        }
        Envio envio = null;


        return envio;
    }



    /**
     * TODO: Añade los Envios al final de un fichero CSV, SIN SOBREESCRIBIR la información
     * @param fichero
     * @return
     */
    public boolean aniadirEnviosCsv(String fichero) {
        PrintWriter pw = null;
        try {

            return true;
        } catch (Exception e) {
            return false;
        } finally {

        }
    }

    /**
     * TODO: Lee los Envios del fichero CSV y los añade a las listas de sus respectivos Portes y Clientes
     * @param ficheroEnvios
     * @param portes
     * @param clientes
     */
    public static void leerEnviosCsv(String ficheroEnvios, ListaPortes portes, ListaClientes clientes) {
        Scanner sc = null;
        try {
            sc = new Scanner(new FileOutputStream("ficheroEnvios.csv"));

        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero de envíos");
        }
        //añado excepciones para este método
        catch(FileNotFoundException e){
            System.out.print("Fichero "+ficheroPuertos+" no encontrado"+ e.getMessage());
        }catch( IOException e){
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
    }
}
