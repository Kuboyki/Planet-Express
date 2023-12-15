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
public class ListaPortes {
    private Porte[] portes;
    private int ocupacion = 0;
    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaPortes(int capacidad) {
        this.portes= new Porte[capacidad];
    }
    // TODO: Devuelve el número de portes que hay en la lista
    public int getOcupacion() {
        for (int i = 0; i < portes.length; i++) {
            if (portes[i] != null){
                ocupacion++;
            }
        }
        return ocupacion;
    }
    // TODO: ¿Está llena la lista?
    public boolean estaLlena() {
        boolean hayhueco = false;
        for(int i = 0;i< portes.length;i++){
            if(portes[i]==null){
                hayhueco = true;
            }
        }
        return hayhueco;
    }
	//TODO: devuelve un porte dado un indice
    public Porte getPorte(int i) {
        return portes[i-1];
    }
    /**
     * TODO: Devuelve true si puede insertar el porte
     * @param porte
     * @return false en caso de estar llena la lista o de error
     */
    public boolean insertarPorte(Porte porte) {
        estaLlena();
        if(estaLlena()==true){
            for(int i=0;i< portes.length;i++){
                if (portes[i]==null){
                    portes[i]=porte;
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * TODO: Devuelve el objeto Porte que tenga el identificador igual al parámetro id
     * @param id
     * @return el objeto Porte que encontramos o null si no existe
     */
    public Porte buscarPorte(String id) {
        for (int i = 0;i<portes.length;i++) {
            if (portes[i].getID().equals(id)) {
                return portes[i];
            }
        }
        return null;
    }

    /**
     * TODO: Devuelve un nuevo objeto ListaPortes conteniendo los Portes que vayan de un puerto espacial a otro
     *  en una determinada fecha
     * @param codigoOrigen
     * @param codigoDestino
     * @param fecha
     * @return
     */
    public ListaPortes buscarPortes(String codigoOrigen, String codigoDestino, Fecha fecha) {
        for(int i=0; i< portes.length;i++){
            if(portes[i].getMuelleOrigen()==Integer.parseInt(codigoOrigen)){
                if((portes[i].getMuelleDestino() == Integer.parseInt(codigoDestino))){
                    System.out.println("Fecha de Salida.");
                    if(portes[i].getSalida().equals(fecha)){
                        return{
                    System.out.println("Fecha introducido incorrecto."){
                }else{
                    System.out.println("Muelle destino introducido incorrecto.");
                }
            }else {
            }

        }
    }

    /**
     * TODO: Muestra por pantalla los Portes siguiendo el formato de los ejemplos del enunciado
     */
    public void listarPortes() {
        for(i = 0; i<portes.length; i++){
            portes[i].toString();
    }
    /**
     * TODO: Permite seleccionar un Porte existente a partir de su ID, usando el mensaje pasado como argumento para
     *  la solicitud y siguiendo el orden y los textos mostrados en el enunciado, y usando la cadena cancelar para
     *  salir devolviendo null.
     *  La función solicita repetidamente hasta que se introduzca un ID correcto
     * @param teclado
     * @param mensaje
     * @param cancelar
     * @return
     */
    public Porte seleccionarPorte(Scanner teclado, String mensaje, String cancelar) {
        while (true){
            System.out.println(mensaje);
        }
        Porte porte = null;
        return porte;
    }

    /**
     * TODO: Ha de escribir la lista de Portes en la ruta y nombre del fichero pasado como parámetro.
     *  Si existe el fichero, SE SOBREESCRIBE, si no existe se crea.
     * @param fichero
     * @return
     */
    public boolean escribirPortesCsv(String fichero) {
        try {

            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    /**
     * TODO: Genera una lista de Portes a partir del fichero CSV, usando los límites especificados como argumentos para
     *  la capacidad de la lista
     * @param fichero
     * @param capacidad
     * @param puertosEspaciales
     * @param naves
     * @return
     */
    public static ListaPortes leerPortesCsv(String fichero, int capacidad, ListaPuertosEspaciales puertosEspaciales, ListaNaves naves) {
        ListaPortes listaPortes = new ListaPortes(capacidad);
        try {
            salida = new PrintWriter(new FileOutputStream("ficheroPortes.csv"));

        } catch (Exception e) {
            return null;
        }
        //añado excepciones
        catch(FileNotFoundException e){
            System.out.print("Fichero "+fichero.getName()+" no encontrado"+ e.getMessage());
        }catch( e){
            System.out.print("Error de lectura de fichero "+e.getMessage());
        }catch(IOException e) {
            System.out.print("Error de escritura en fichero " + e.getMessage());
        }
        //Sin finally??
        return listaPortes;
    }
}
