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
        return this.getOcupacion()==envios.length;
/*boolean llena = false;
        if (getOcupacion()== envios.length) {
           llena = true;
        }
        return llena;
         */
    }
	//TODO: Devuelve el envio dado un indice
    public Envio getEnvio(int i) {
        return envios[i - 1];
    }

    /**
     * TODO: insertamos un nuevo envío en la lista
     * @param envio
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarEnvio(Envio envio) {
        boolean salir = false;
        if(estaLlena()==false){
            //do{
                for (int i=0;i<envios.length;i++){
                    if(envios[i]== null){
                        envios[i]=envio;
                        salir=true;
                    }
                }
          //  }while (salir==false);
        }
        return salir;
    }

    /**
     * TODO: Buscamos el envio a partir del localizador pasado como parámetro
     * @param localizador
     * @return el envio que encontramos o null si no existe
     */
    public Envio buscarEnvio(String localizador) {
        Envio encontrado = null;
        for (int i = 0;i< envios.length;i++) {
            if (envios[i].getLocalizador().equals(localizador)) {
               encontrado= envios[i];
            }
        }
        if (encontrado==null){
        System.out.println("Localizador incorrecto");
        }
        return encontrado;

    }


    /**
     * TODO: Buscamos el envio a partir del idPorte, fila y columna pasados como parámetros
     * @param idPorte
     * @param fila
     * @param columna
     * @return el envio que encontramos o null si no existe
     */
    public Envio buscarEnvio(String idPorte, int fila, int columna) {
        Envio encontrado = null;
        for (int i = 0; i < envios.length; i++) {
            if (envios[i].getPorte().getID().equals(idPorte)) {
                if (envios[i].getFila()==fila) {
                    if (envios[i].getColumna() == columna) {
                        encontrado = envios[i];
                    }
                }
            }
        }
        if (encontrado==null){
            System.out.println("Envío no encontrado"); //NO SE SI ESTO SE DEBE PONER
        }
        return encontrado;
    }

    /**
     * TODO: Eliminamos un envio a través del localizador pasado por parámetro
     * @param localizador
     * @return True si se ha borrado correctamente, false en cualquier otro caso
     */
    public boolean eliminarEnvio (String localizador) {
       boolean salir=false;
            for(int i = 0;i< envios.length;i++){
                if(envios[i].getLocalizador().equals(localizador)){
                    envios[i]=null;
                    salir= true;
                }
            }
        return salir;
    }

    /**
     * TODO: Muestra por pantalla los Envios de la lista, con el formato que aparece
     * en el enunciado
     */
    public void listarEnvios() {
        for(int i=0;i< envios.length;i++){
            envios[i].toString();
        }
    }
    /**
     * TODO: Permite seleccionar un Envio existente a partir de su (localizador), usando el mensaje pasado como argumento
     *  para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente hasta que se introduzca un localizador correcto
     * @param teclado
     * @param mensaje
     * @return
     */
    public Envio seleccionarEnvio(Scanner teclado, String mensaje) {

        String localizador =Utilidades.leerCadena(teclado,mensaje); //Seleccione un envio:
       /* do {
            if (localizador != mensaje) {
                System.out.println("Localizador incorrecto");
            }
            localizador=  Utilidades.leerCadena(teclado,mensaje);
        } while (localizador != mensaje); */
        while(buscarEnvio(localizador)==null){
            localizador =Utilidades.leerCadena(teclado,mensaje); //Seleccione un envio:
        }
        return buscarEnvio(localizador);
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
            sc = new Scanner(new FileOutputStream(ficheroEnvios));

        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero de envíos");
        }
        //añado excepciones para este método
        catch(FileNotFoundException e){
            System.out.print("Fichero "+ficheroEnvios+" no encontrado"+ e.getMessage());
        }catch( IOException e){
            System.out.print("Error de lectura de fichero "+e.getMessage());
        }catch(IOException e) {
            System.out.print("Error de escritura en fichero " + e.getMessage());
        }finally {
            if (sc != null) {
                    sc.close();
                }
            }
        }
    }
}
