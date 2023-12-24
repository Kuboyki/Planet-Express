//Realizado por Sandra Blázquez Aldea bu0060 y Dong Jinghong IWSIM11

import java.io.*;
import java.util.Scanner;

/**
 * Clase en el que se gestionan en grupo los puertos que hay
 *
 * @author Sandra Blázquez Aldea
 * @author Dong Jinghong
 * @version 1.0
 */
public class ListaPuertosEspaciales {
    private PuertoEspacial[] lista;


    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad capacidad del array de la lista de puertos espaciales que contiene a todos los puertos espaciales que hay
     */
    public ListaPuertosEspaciales(int capacidad) {
        this.lista = new PuertoEspacial[capacidad];
    }

    // TODO: Devuelve el número de puertos espaciales que hay en la lista
    public int getOcupacion() {
        int ocupacion = 0;
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] != null) {
                ocupacion++;
            }
        }
        return ocupacion;
    }

    // TODO: ¿Está llena la lista?
    public boolean estaLlena() {

        return this.getOcupacion()==lista.length;
    }

    // TODO: Devuelve un puerto espacial dado un indice
    public PuertoEspacial getPuertoEspacial(int i) {
        return lista[i];
    }
    /**
     * TODO: insertamos un Puerto espacial nuevo en la lista
     *
     * @param puertoEspacial que se quiere insertar en la lista
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarPuertoEspacial(PuertoEspacial puertoEspacial) {
        boolean salir = false;
        if (!estaLlena()) {

                    lista[getOcupacion()] = puertoEspacial;
                    salir = true;

        }
        return salir;
    }

    /**
     * TODO: Buscamos un Puerto espacial a partir del codigo pasado como parámetro
     *
     * @param codigo con el que se quiere identificar al puerto espacial
     * @return Puerto espacial que encontramos o null si no existe
     */
    public PuertoEspacial buscarPuertoEspacial(String codigo) {
        PuertoEspacial encontrado = null;
        boolean salir = false;
        int i = 0;
        while (i < getOcupacion() && !salir) {
            if (lista[i].getCodigo().equals(codigo)) {
                encontrado = getPuertoEspacial(i);
                salir = true;
            }
            i++;
        }
             return encontrado;

}


    /**
     * TODO: Permite seleccionar un puerto espacial existente a partir de su código, usando el mensaje pasado como
     *  argumento para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente el código hasta que se introduzca uno correcto
     *
     * @param teclado que hace referencia al teclado físico por el que se introduce el código con el que se quiere identificar al puerto espacial
     * @param mensaje  que se muestra por pantalla para indicar que se necesita introducir por teclado
     * @return puerto espacial que se quiere seleccionar o null en caso de que no se encuentre
     */
    public PuertoEspacial seleccionarPuertoEspacial(Scanner teclado, String mensaje) {
    //    String codigo = Utilidades.leerCadena(teclado, mensaje); //"Ingrese código de puerto Origen: //destino "
        PuertoEspacial puertoEspacial = null;
        String codigo;
        do  {
            codigo = Utilidades.leerCadena(teclado, mensaje);
            puertoEspacial=buscarPuertoEspacial(codigo);
            if (puertoEspacial==null){
                System.out.println("Código de puerto no encontrado.");
            }

        }while (puertoEspacial==null);
        return puertoEspacial;

    }

    /**
     * TODO: Genera un fichero CSV con la lista de puertos espaciales, SOBREESCRIBIENDOLO
     *
     * @param nombre del fichero
     * @return fichero generado con la lista de puertos espaciales
     */
    public boolean escribirPuertosEspacialesCsv(String nombre) {
        PrintWriter pw = null;
        PuertoEspacial puertoEspacial;
        boolean closes = true;
        try {
            pw = new PrintWriter(new FileWriter(nombre,false));

            for (int i = 0; i < getOcupacion(); i++) {
                puertoEspacial = lista[i];
                pw.println(puertoEspacial.getNombre() + ";" + puertoEspacial.getCodigo() + ";"
                        + puertoEspacial.getRadio() + ";" + puertoEspacial.getAzimut() + ";" + puertoEspacial.getPolar() + ";"
                        + puertoEspacial.getMuelles());
            }

        } catch (FileNotFoundException e) {
            System.out.print("Fichero " + nombre + " no encontrado" + e.getMessage());
        } catch (IOException e) {

            System.out.print("Error de escritura en fichero "+nombre + e.getMessage());
closes = false;
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return closes;
    }

    /**
     * TODO: Genera una lista de PuertosEspaciales a partir del fichero CSV, usando el argumento como capacidad máxima
     *  de la lista
     *
     * @param fichero que representa el fichero a partir del cual se quiere generar la lista
     * @param capacidad de la lista de puertos espaciales
     * @return la lista de puertos espaciales generada
     */
    public static ListaPuertosEspaciales leerPuertosEspacialesCsv(String fichero, int capacidad) {
        ListaPuertosEspaciales listaPuertosEspaciales = new ListaPuertosEspaciales(capacidad);
    BufferedReader sc = null;
        try {
       sc=new BufferedReader(new FileReader(fichero));
            String cadena;
        while ((cadena= sc.readLine()) != null) {

            String[] dato = cadena.split(";");

                PuertoEspacial puertoEspacial = new PuertoEspacial(dato[0], dato[1], Double.parseDouble(dato[2]), Double.parseDouble(dato[3]), Double.parseDouble(dato[4]), Integer.parseInt(dato[5]));
                listaPuertosEspaciales.insertarPuertoEspacial(puertoEspacial);

        }
        sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fichero " + fichero + " no encontrado" + e.getMessage());
        } catch (IOException e) {

            System.out.print("Error de lectura de fichero " + e.getMessage());
        } finally {
            if (sc != null) {
                try {
                    sc.close();
                }catch (IOException ex){
             System.out.println("Error de cierre de fichero ");
                }
            }
        }
        return listaPuertosEspaciales;
    }
}
