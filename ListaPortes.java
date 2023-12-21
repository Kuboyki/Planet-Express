//Realizado por Sandra Blázquez Aldea bu0060 y DONG JINGHONG IWSIM11

import java.io.*;
import java.util.Scanner;


/**
 * Description of the class
 *
 * @author
 * @author
 * @version 1.0
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
        this.portes = new Porte[capacidad];
    }

    // TODO: Devuelve el número de portes que hay en la lista
    public int getOcupacion() {
        for (int i = 0; i < portes.length; i++) {
            if (portes[i] != null) {
                ocupacion++;
            }
        }
        return ocupacion;
    }

    // TODO: ¿Está llena la lista?
    public boolean estaLlena() {
        /*boolean llena = false;
        if (getOcupacion()== portes.length) {
           llena = true;
        }
        return llena;
         */
        return this.getOcupacion() == portes.length;

    }

    //TODO: devuelve un porte dado un indice
    public Porte getPorte(int i) {
        return portes[i - 1];
    }

    /**
     * TODO: Devuelve true si puede insertar el porte
     *
     * @param porte
     * @return false en caso de estar llena la lista o de error
     */
    public boolean insertarPorte(Porte porte) {
        boolean salir = false;
        if (estaLlena() == false) {
            for (int i = 0; i < portes.length; i++) {
                if (portes[i] == null) {
                    portes[i] = porte;
                    salir = true;
                }
            }
        }
        return salir;
    }


    /**
     * TODO: Devuelve el objeto Porte que tenga el identificador igual al parámetro id
     *
     * @param id
     * @return el objeto Porte que encontramos o null si no existe
     */
    public Porte buscarPorte(String id) {
        Porte encontrado = null;
        for (int i = 0; i < portes.length; i++) {
            if (portes[i].getID().equals(id)) {
                encontrado = portes[i];
            }
        }
        if (encontrado==null){
            System.out.println("Porte no encontrado.");
        }
        return encontrado;
    }

    /**
     * TODO: Devuelve un nuevo objeto ListaPortes conteniendo los Portes que vayan de un puerto espacial a otro
     *  en una determinada fecha
     *
     * @param codigoOrigen
     * @param codigoDestino
     * @param fecha
     * @return
     */
    public ListaPortes buscarPortes(String codigoOrigen, String codigoDestino, Fecha fecha) {
        ListaPortes porte ;
        for (int i = 0; i < portes.length; i++) {
            portes[i].coincide(codigoOrigen,codigoDestino,fecha);
            if (portes[i].coincide(codigoOrigen,codigoDestino,fecha) ==true){
                porte[portes[i]];
            }//com oguardo los portres ???????????????????
         }
        return porte;
    }

    /**
     * TODO: Muestra por pantalla los Portes siguiendo el formato de los ejemplos del enunciado
     */
    public void listarPortes() {
        for (int i = 0; i < portes.length; i++) {
            portes[i].toStringSimple();
        }
    }

    /**
     * TODO: Permite seleccionar un Porte existente a partir de su ID, usando el mensaje pasado como argumento para
     *  la solicitud y siguiendo el orden y los textos mostrados en el enunciado, y usando la cadena cancelar para
     *  salir devolviendo null.
     *  La función solicita repetidamente hasta que se introduzca un ID correcto
     *
     * @param teclado
     * @param mensaje
     * @param cancelar
     * @return
     */
    public Porte seleccionarPorte(Scanner teclado, String mensaje, String cancelar) {
        String combinacion = Utilidades.leerCadena(teclado, mensaje);//"Seleccione un porte:"
        Porte comb = buscarPorte(combinacion);
        boolean salir = false;
        while (comb==null || !salir) {
            if (combinacion == "cancelar" || combinacion == "CANCELAR"){
                salir = true;
            }
             combinacion = Utilidades.leerCadena(teclado, mensaje);
        }
        return buscarPorte(combinacion);
    }

    /**
     * TODO: Ha de escribir la lista de Portes en la ruta y nombre del fichero pasado como parámetro.
     *  Si existe el fichero, SE SOBREESCRIBE, si no existe se crea.
     *
     * @param fichero
     * @return
     */
    public boolean escribirPortesCsv(String fichero) {
        PrintWriter pw = null;
        boolean closes = false;
        try {
            pw = new PrintWriter(new FileWriter(fichero));
            for (int i=0; i< portes.length; i++){
                pw.println(portes [i].toString());
            } //o es pw.println(listarPortes()); ????????????????????
            pw.close();
            closes = true;

        } catch (FileNotFoundException e) {
            System.out.print("Fichero " + fichero + " no encontrado" + e.getMessage());
        } catch (IOException e) {
            //   if ( closes==false) {
            //       System.out.println("Error de cierre de fichero "
            //                + e.getMessage());
            //   }else{
            System.out.print("Error de escritura en fichero "+fichero + e.getMessage());

        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return closes;
    }

    /**
     * TODO: Genera una lista de Portes a partir del fichero CSV, usando los límites especificados como argumentos para
     *  la capacidad de la lista
     *
     * @param fichero
     * @param capacidad
     * @param puertosEspaciales
     * @param naves
     * @return
     */
    public static ListaPortes leerPortesCsv(String fichero, int capacidad, ListaPuertosEspaciales
            puertosEspaciales, ListaNaves naves) {
        ListaPortes listaPortes = new ListaPortes(capacidad);
        try {
            sc = new Scanner(new FileReader(fichero));
            String cadena;
            while (sc.hasNextLine()) {
                cadena = sc.nextLine();
                System.out.println(cadena);
            }
            sc.close(); //esto hay que ponerlo ?????????''

        } catch (Exception e) {
            return null;
        }
        //añado excepciones
        catch (FileNotFoundException e) {
            System.out.print("Fichero " + fichero + " no encontrado" + e.getMessage());
            // } catch (IOException e) {
            //      System.out.print("Error de lectura de fichero " + e.getMessage());
        } finally {
            if (sc != null) {
                sc.close();
            }
        }

        return listaPortes;
    }
}

