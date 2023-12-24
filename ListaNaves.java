//Realizado por Sandra Blázquez Aldea bu0060 y Dong Jinghong IWSIM11

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

/**
 * Clase en el que se gestionan en grupo las naves que hay
 *
 * @author Sandra Blázquez Aldea
 * @author Dong Jinghong
 * @version 1.0
 */
public class ListaNaves {
    private final Nave[] naves;
    private int ocupacion = 0;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad capacidad del array de la lista de naves que contiene a todos las naves que hay
     */
    public ListaNaves(int capacidad) {
        naves = new Nave[capacidad];

    }
    /**
     *
     * @return el número de naves que hay en la lista
     */
    // TODO: Devuelve el número de naves que hay en la lista
    public int getOcupacion() {
        for (int i = 0; i < naves.length; i++) {
            if (naves[i] != null) {
                ocupacion++;
            }
        }
        return ocupacion;
    }
    /**
     *  Función que dicta si ya se ha alcanzado el limite de naves
     * @return true si está llena la lista o false en caso contrario
     */
    // TODO: ¿Está llena la lista de naves?
    public boolean estaLlena() {

        return this.getOcupacion() == naves.length;

    }

    /**
     *  Función que deuvelve una nave
     * @param posicion posicion de la nave que se pasa por parámetro
     * @return un anave según la posición que tenga
     */
    // TODO: Devuelve nave dado un indice
    public Nave getNave(int posicion) {
        return naves[posicion];
    }

    /**
     * TODO: insertamos una nueva nave en la lista
     *
     * @param nave que se introduce como parámetro para añadirla a la lista de naves
     * @return true en caso de que la nave introducida como parámetro se añada correctamente a la lista de naves, false en caso de lista llena o error
     */
    public boolean insertarNave(Nave nave) {
        boolean salir = false;
        if (!estaLlena()) {
           salir = true;
        naves[getOcupacion()] = nave;
                }


        return salir;
    }

    /**
     * TODO: Buscamos la nave a partir de la matricula pasada como parámetro
     *
     * @param matricula de la nave con la que se la puede identificar
     * @return la nave que encontramos apartir de la matricula  o null si no la encunetra en la lista de naves
     */
    public Nave buscarNave(String matricula) {
        Nave encontrado = null;
        for (int i = 0; i < naves.length; i++) {
            if (naves[i].getMatricula().equals(matricula)) {
                encontrado = naves[i];
            }
        }

        return encontrado;
    }

    /**
     * Muestra por pantalla las naves que hay en un determinado formato
     */
    // TODO: Muestra por pantalla las naves de la lista con el formato indicado en el enunciado
    public void mostrarNaves() {
        for (int i = 0; i < naves.length; i++) {
System.out.println(naves[i].toStringSimple());

        }
    }


    /**
     * TODO: Permite seleccionar una nave existente a partir de su matrícula, y comprueba si dispone de un alcance
     *  mayor o igual que el pasado como argumento, usando el mensaje pasado como argumento para la solicitud y
     *  siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente la matrícula de la nave hasta que se introduzca una con alcance suficiente
     *
     * @param teclado nos sirve como referencia al teclado físico, para poder introducir valores que se le asignan a la matrícula de la nave
     * @param mensaje que aparece por pantalla para indicar lo que hay que introeducir por teclado
     * @param alcance el máximo hasta donde puede llegar
     * @return devolver el objeto naves que se ha buscado y seleccionado a partir de la matrícula introducida por teclado
     */
    //Ingrese matrícula de la nave: EC-2
    //Matrícula de avión no encontrada.
    //Ingrese matrícula de la nave: Soyuz
    //Avión seleccionado con alcance insuficiente.
    //Ingrese matrícula de la nave: MS-19
    public Nave seleccionarNave(Scanner teclado, String mensaje, double alcance) {
        String matricula = Utilidades.leerCadena(teclado, mensaje);//"Ingrese matrícula de la nave: "
        while (buscarNave(matricula) == null || buscarNave(matricula).getAlcance() < alcance) {
            if (buscarNave(matricula) == null) {
                System.out.println("Matrícula de avión no encontrada.");
            } else if (buscarNave(matricula).getAlcance() < alcance) {
                System.out.print("Avión seleccionado con alcance insuficiente.");
            }
            matricula = Utilidades.leerCadena(teclado, mensaje);
        }
        return buscarNave(matricula);

    }


    /**
     * TODO: Genera un fichero CSV con la lista de Naves, SOBREESCRIBIÉNDOLO
     *
     * @param nombre del fichero que se va a sobreescribir
     * @return devuelve true o false dependiendo si se ha podido escribir o no
     */
    public boolean escribirNavesCsv(String nombre) {
        PrintWriter pw = null;
        Nave nave;
        boolean leer = false;
        try {
            pw = new PrintWriter(new FileWriter(nombre,false)); //crear un objeto PrintWriter de un objeto File

            for (int i = 0; i < getOcupacion(); i++) {
                nave = naves[i];
                pw.println(nave.getMarca() + ";" + nave.getModelo() + ";"
                        + nave.getMatricula() + ";" + nave.getFilas() + ";" + nave.getColumnas() + ";"
                        + nave.getAlcance());
            }
            leer = true;
        } catch (FileNotFoundException e) {
            System.out.print("Fichero " + nombre + " no encontrado" + e.getMessage());
        } catch (IOException e) {

            System.out.print("Error de escritura en fichero " + nombre + e.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return leer;
    }


    /**
     * TODO: Genera una lista de naves a partir del fichero CSV, usando el argumento como capacidad máxima de la lista
     *
     * @param fichero donde se encuentran en un formato concreto todas las naves que hay
     * @param capacidad de la lista naves donde están todas las naves que hay
     * @return la lista que se ha leído
     */
    public static ListaNaves leerNavesCsv(String fichero, int capacidad) {
        ListaNaves listaNaves = new ListaNaves(capacidad);
        BufferedReader sc = null;
        try {
            sc = new BufferedReader(new FileReader(fichero));
            String cadena;
            while ((cadena = sc.readLine()) != null) {

                String[] dato = cadena.split(", ");
                Nave nave = new Nave(dato[0], dato[1], dato[2], Integer.parseInt(dato[3]), Integer.parseInt(dato[4]), Double.parseDouble(dato[5]));
                listaNaves.insertarNave(nave);
            }
        } catch (FileNotFoundException e) {
            System.out.print("Fichero " + fichero + " no encontrado" + e.getMessage());
        } catch (IOException e) {
            System.out.print("Error de lectura de fichero " + e.getMessage());
        } finally {
            if (sc != null) {
                try {
                    sc.close();
                } catch (IOException ex) {
                    System.out.println("Error de cierre de fichero ");
                }
            }
        }
        return listaNaves;
    }
}
