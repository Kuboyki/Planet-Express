//Realizado por Sandra Blázquez Aldea bu0060 y Dong Jinghong IWSIM11

import java.io.*;
import java.util.Scanner;

/**
 * Clase en el que se gestionan en grupo los envíos que hay
 *
 * @author Sandra Blázquez Aldea
 * @author Dong Jinghong
 * @version 1.0
 */
public class ListaEnvios {
    private Envio[] envios;

    private int ocupacion = 0;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad capacidad del array de la lista de envíos que contiene a todos los envíos que hay
     */
    public ListaEnvios(int capacidad) {
        this.envios = new Envio[capacidad];

    }

    /**
     * Función que devuelve el número de envíos que hay en la lista
     * @return devuelve el número de envíos que hay en la lista
     */

    // TODO: Devuelve el número de envíos que hay en la lista
    public int getOcupacion() {
        for (int i = 0; i < envios.length; i++) {
            if (envios[i] != null) {
                ocupacion++;
            }
        }
        return ocupacion;
    }
    /**
     * Función que ve si la lista está llena o no
     * @return devuelve un true o un false según si la lista está llena o no
     */

    // TODO: ¿Está llena la lista de envíos?
    public boolean estaLlena() {
        return this.getOcupacion() == envios.length;

    }
    /**
     * Función que devuelve el envio dado un indice
     * @return devuelve el envío en la posición que se pasa como argumento
     */
    //TODO: Devuelve el envio dado un indice
    public Envio getEnvio(int i) {
        return envios[i - 1];

    }

    /**
     * TODO: insertamos un nuevo envío en la lista
     *
     * @param envio que se va a insertar en la lista de envíos
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarEnvio(Envio envio) {
        boolean salir = false;
        if (!estaLlena()) {
                    envios[getOcupacion()] = envio;
                    salir = true;
                }


        return salir;
    }

    /**
     * TODO: Buscamos el envio a partir del localizador pasado como parámetro
     *
     * @param localizador por el que se va a intentar identificar el envío que se busca
     * @return el envío que encontramos o null si no existe
     */
    public Envio buscarEnvio(String localizador) {
        Envio encontrado = null;
        for (int i = 0; i < envios.length; i++) {
            if (envios[i].getLocalizador().equals(localizador)) {
                encontrado = envios[i];
            }
        }
        if (encontrado == null) {
            System.out.println("Localizador incorrecto");
        }
        return encontrado;

    }


    /**
     * TODO: Buscamos el envio a partir del idPorte, fila y columna pasados como parámetros
     *
     * @param idPorte identificación del porte que se usa para buscar su envío correspondiente
     * @param fila en la que se sitúa el porte dentro de la matriz de la lista de portes
     * @param columna en la que se sitúa el porte dentro de la matriz de la lista de portes
     * @return el envío que encontramos o null si no existe
     */
    public Envio buscarEnvio(String idPorte, int fila, int columna) {
        Envio encontrado = null;
        for (int i = 0; i < envios.length; i++) {
            if (envios[i].getPorte().getID().equals(idPorte)) {
                if (envios[i].getFila() == fila) {
                    if (envios[i].getColumna() == columna) {
                        encontrado = envios[i];
                    }
                }
            }
        }
        if (encontrado == null) {
            System.out.println("Envío no encontrado"); //NO SE SI ESTO SE DEBE PONER
        }
        return encontrado;
       }

    /**
     * TODO: Eliminamos un envio a través del localizador pasado por parámetro
     *
     * @param localizador por el que se va a intentar identificar el envío que se quiere identificar
     * @return True si se ha borrado correctamente, false en cualquier otro caso
     */
    public boolean eliminarEnvio(String localizador) {
        boolean salir = false;
        for (int i = 0; i < envios.length; i++) {
            if (envios[i].getLocalizador().equals(localizador)) {
                envios[i] = null;
                salir = true;
            }
        }
        return salir;

    }

    /**
     * TODO: Muestra por pantalla los Envios de la lista, con el formato que aparece
     * en el enunciado
     *
     */
    public void listarEnvios() {
        for (int i = 0; i < envios.length; i++) {
          System.out.println(  envios[i].toString());
        }
    }

    /**
     * TODO: Permite seleccionar un Envio existente a partir de su (localizador), usando el mensaje pasado como argumento
     *  para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente hasta que se introduzca un localizador correcto
     *
     * @param teclado que hace referencia al teclado físico por el que se va a introducir el localizador correspondiente
     * @param mensaje que se va a ver en pantalla para indicar lo que se quiere se introduzca por teclado
     * @return el envío que se quiere identificar a través del localizador
     */
    public Envio seleccionarEnvio(Scanner teclado, String mensaje) {

        String localizador = Utilidades.leerCadena(teclado, mensaje); //Seleccione un envio:
        Envio envio = null;

        while (buscarEnvio(localizador) == null) {
            localizador = Utilidades.leerCadena(teclado, mensaje); //Seleccione un envio:
            envio = buscarEnvio(localizador);
        }
        return envio;
    }

    /**
     * TODO: Añade los Envios al final de un fichero CSV, SIN SOBREESCRIBIR la información
     *
     * @param fichero que corresponde al nombre del fichero en el que se van a añadir los envíos
     * @return true en caso de que se haya añadido correctamente y false en caso contrario
     */
    public boolean aniadirEnviosCsv(String fichero) {
        PrintWriter pw = null;
        boolean aniadir = false;
        Envio  envio;

        try {
            pw = new PrintWriter(new FileWriter(fichero, true)); // Modo adición
for (int i =0; i< envios.length; i++){
    envio = envios[i];
    pw.println(envio.getLocalizador()+";"+envio.getPorte().getID()+";"+envio.getCliente().getEmail()+";"+envio.getFila()+";"+envio.getColumna()+";"+envio.getPrecio());
}
            aniadir = true;
        } catch (FileNotFoundException e) {
            System.out.print("Fichero " + fichero + " no encontrado" + e.getMessage());
        } catch (IOException e) {
            System.out.print("Error de escritura de fichero " + fichero + e.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return aniadir;
    }


    /**
     * TODO: Lee los Envios del fichero CSV y los añade a las listas de sus respectivos Portes y Clientes
     *
     * @param ficheroEnvios fichero donde se encuentran en un formato concreto los distintos envíos que hay
     * @param portes objeto que representa el porte donde se tiene qeu añadir el envío
     * @param clientes objeto que representa el cliente al que se tiene que asignar el envío
     */
    public static void leerEnviosCsv(String ficheroEnvios, ListaPortes portes, ListaClientes clientes) {
        BufferedReader sc = null;

        try {
            sc = new BufferedReader(new FileReader(ficheroEnvios));
            String cadena;
            while ((cadena = sc.readLine()) != null) {
                String[] dato = cadena.split(", ");
                Envio envio = new Envio(dato[0], portes.buscarPorte(dato[1]),
                        clientes.buscarClienteEmail(dato[2]), Integer.parseInt(dato[3]),
                        Integer.parseInt(dato[4]), Double.parseDouble(dato[5]));
                    clientes.buscarClienteEmail(dato[2]).aniadirEnvio(envio);
                    portes.buscarPorte(dato[1]).ocuparHueco(envio);;
                }


        }  catch (FileNotFoundException e) {
            System.out.print("Fichero " + ficheroEnvios + " no encontrado" + e.getMessage());
        } catch (IOException e) {
            System.out.print("Error de lectura de fichero " + ficheroEnvios + e.getMessage());
        } finally {
            if (sc != null) {
                try {
                    sc.close();
                } catch (IOException ex) {
                    System.out.println("Error de cierre de fichero ");
                }
            }
        }
    }
}
