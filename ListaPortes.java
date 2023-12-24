//Realizado por Sandra Blázquez Aldea bu0060 y Dong Jinghong IWSIM11

import java.io.*;
import java.util.Scanner;


/**
 * Clase en el que se gestionan en grupo los portes que hay
 *
 * @author Sandra Blázquez Aldea
 * @author Dong Jinghong
 * @version 1.0
 */
public class ListaPortes {
    private Porte[] portes;
    private int ocupacion = 0;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad capacidad del array de la lista de portes que contiene a todos los portes que hay
     */
    public ListaPortes(int capacidad) {
        this.portes = new Porte[capacidad];
    }

    /**
     *
     * @return el número de portes que hay en la lista
     */
    // TODO: Devuelve el número de portes que hay en la lista
    public int getOcupacion() {
        for (int i = 0; i < portes.length; i++) {
            if (portes[i] != null) {
                ocupacion++;
            }
        }
        return ocupacion;
    }

    /**
     * Función que detecta si está llena o no la lista
      * @return true si está llena o no
     */
// TODO: ¿Está llena la lista?
    public boolean estaLlena() {

        return this.getOcupacion() == portes.length;

    }

    /**
     *
     * @param i posición del porte
     * @return porte según la posición que pasan como argumento
     */
    //TODO: devuelve un porte dado un indice
    public Porte getPorte(int i) {
        return portes[i - 1];
    }

    /**
     * TODO: Devuelve true si puede insertar el porte
     *
     * @param porte que se quiere insertar en la lista
     * @return false en caso de estar llena la lista o de error
     */
    public boolean insertarPorte(Porte porte) {
        boolean salir = false;
        if (!estaLlena()) {
            portes[getOcupacion()]= porte;
            salir=true;
        }
        return salir;
    }


    /**
     * TODO: Devuelve el objeto Porte que tenga el identificador igual al parámetro id
     *
     * @param id identificación del porte al que se quiere encontrar
     * @return el objeto Porte que encontramos o null si no existe
     */
    public Porte buscarPorte(String id) {
        Porte encontrado = null;
        boolean salir = false;
        while(encontrado==null) {
            for (int i = 0; i < portes.length; i++) {
                if (portes[i].getID().equals(id)) {
                    encontrado = getPorte(i);

                }
            }
        }

        return encontrado;
    }

    /**
     * TODO: Devuelve un nuevo objeto ListaPortes conteniendo los Portes que vayan de un puerto espacial a otro
     *  en una determinada fecha
     *
     * @param codigoOrigen que corresponde al aeropuerto desde el que se parte
     * @param codigoDestino que corresponde al aeropuerto al que se llega
     * @param fecha en la que se sale de un aeropuerto a otro
     * @return en una lista los portes que vayan desde el aeropuerto indicado hasta el destino indicado
     */
    public ListaPortes buscarPortes(String codigoOrigen, String codigoDestino, Fecha fecha) {

ListaPortes listaPortes = new ListaPortes(portes.length);
        boolean correcto=false;
        int i =0;
        while(i<ocupacion && !correcto){
            if(portes[i].coincide(codigoOrigen,codigoDestino,fecha)){
                        listaPortes.insertarPorte(portes[i]);
                correcto=true;
            }
            i++;
        }
if (correcto){
    return listaPortes;
}else{
    return null;
}
    }

    /**
     * TODO: Muestra por pantalla los Portes siguiendo el formato de los ejemplos del enunciado
     */
    public void listarPortes() {
        for (int i = 0; i < portes.length; i++) {
           System.out.println( portes[i].toStringSimple());
        }
    }

    /**
     * TODO: Permite seleccionar un Porte existente a partir de su ID, usando el mensaje pasado como argumento para
     *  la solicitud y siguiendo el orden y los textos mostrados en el enunciado, y usando la cadena cancelar para
     *  salir devolviendo null.
     *  La función solicita repetidamente hasta que se introduzca un ID correcto
     *
     * @param teclado que hace referencia al teclado físico por el que se introduce la identificación del porte que se quiere seleccionar
     * @param mensaje que se muestra por pantalla para indicar que se necesita introducir por teclado
     * @param cancelar en el caso de que no se quiera seleccionar dicho porte
     * @return
     */
    public Porte seleccionarPorte(Scanner teclado, String mensaje, String cancelar) {
        String identificacion = Utilidades.leerCadena(teclado, mensaje);//"Seleccione un porte:"
        Porte comb = buscarPorte(identificacion);
        boolean salir = false;
        while (comb==null || !salir) {
            System.out.println("Porte no encontrado.");

            if (identificacion.equals(cancelar)){
                salir = true;
            }
             identificacion = Utilidades.leerCadena(teclado, mensaje);
        }
        return buscarPorte(identificacion);

    }

    /**
     * TODO: Ha de escribir la lista de Portes en la ruta y nombre del fichero pasado como parámetro.
     *  Si existe el fichero, SE SOBREESCRIBE, si no existe se crea.
     *
     * @param fichero que representa el nombre del fichero que se va a sobreescribir o crear
     * @return true si se ha sobreescrito o creado correctamente y false en caso contrario
     */
    public boolean escribirPortesCsv(String fichero) {
        PrintWriter pw = null;
        boolean closes = false;
        Porte porte;
        try {
            pw = new PrintWriter(new FileWriter(fichero,false));

                for (int i=0; i< portes.length; i++){
                    porte = portes[i];
                    pw.println(porte.getID()+";" +porte.getNave().getMatricula()+";" +porte.getOrigen().getCodigo()+";" +porte.getMuelleOrigen()+";" +porte.getSalida().getDia()+"/" +porte.getSalida().getMes()+"/" +porte.getSalida().getAnio()+" " +porte.getSalida().getHora()+":" +porte.getSalida().getMinuto()+";"  +porte.getDestino().getCodigo()+";" +porte.getMuelleDestino()+";" +porte.getLlegada().getDia()+"/" +porte.getLlegada().getMes()+"/" +porte.getLlegada().getAnio()+" " +porte.getLlegada().getHora()+":" +porte.getLlegada().getMinuto()+porte.getPrecio());
                }

                System.out.println("Fichero creado correctamente.");

            pw.close();
            closes = true;

        } catch (FileNotFoundException e) {
            System.out.print("Fichero " + fichero + " no encontrado" + e.getMessage());
        } catch (IOException e) {

            System.out.print("Error de escritura en fichero "+fichero + e.getMessage());

        } finally {
            if (pw != null) {
                pw.close();
                closes=true;
            }
        }
        return closes;
    }

    /**
     * TODO: Genera una lista de Portes a partir del fichero CSV, usando los límites especificados como argumentos para
     *  la capacidad de la lista
     *
     * @param fichero que representa el fichero a partir del cual se quiere generar la lista
     * @param capacidad de la lista de portes
     * @param puertosEspaciales objeto para poder acceder a otros metodos
     * @param naves objeto para poder acceder a otros metodos
     * @return de la lista de portes que se quiere generar
     */
    public static ListaPortes leerPortesCsv(String fichero, int capacidad, ListaPuertosEspaciales
            puertosEspaciales, ListaNaves naves) {
        ListaPortes listaPortes = new ListaPortes(capacidad);
        BufferedReader sc = null;
        try {
            sc = new BufferedReader(new FileReader(fichero));
            String cadena;
            int i = 0;
           while ((cadena = sc.readLine()) != null) {
                String[] dato =cadena.split(";");

                Porte porte = new Porte(dato[0], naves.buscarNave(dato[1]), puertosEspaciales.buscarPuertoEspacial(dato[2]),
                        Integer.parseInt(dato[3]), Fecha.fromString(dato[4]), puertosEspaciales.buscarPuertoEspacial(dato[5]),
                        Integer.parseInt(dato[6]), Fecha.fromString(dato[7]), Double.parseDouble(dato[8]));
                listaPortes.insertarPorte(porte);
                i++;
            }

        } catch (FileNotFoundException e) {
            System.out.print("Fichero " + fichero + " no encontrado" + e.getMessage());
            // } catch (IOException e) {
            //      System.out.print("Error de lectura de fichero " + e.getMessage());
        }catch(IOException e){
            System.out.print("Error de lectura de fichero "+fichero+e.getMessage());
        }finally {
            if (sc != null) {
                    try {
                        sc.close();
                    }catch (IOException ex){
                        System.out.println("Error de cierre de fichero ");
                    }            }
        }

        return listaPortes;
    }
}

