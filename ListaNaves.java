import javax.swing.*;
import java.io.*;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version 1.0
 */
public class ListaNaves {
    private final Nave[] naves;
    private int ocupacion = 0;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaNaves(int capacidad) {
        naves = new Nave[capacidad];

    }

    // TODO: Devuelve el número de naves que hay en la lista
    public int getOcupacion() {
        for (int i = 0; i < naves.length; i++) {
            if (naves[i] != null) {
                ocupacion++;
            }
        }
        return ocupacion;
    }

    // TODO: ¿Está llena la lista de naves?
    public boolean estaLlena() {
       /* boolean llena = false;
        if(getOcupacion()==naves.length){
            llena = true;
        }
        return llena;
        */
        return this.getOcupacion() == naves.length;

    }

    // TODO: Devuelve nave dado un indice
    public Nave getNave(int posicion) {
        return naves[posicion];
    }

    /**
     * TODO: insertamos una nueva nave en la lista
     *
     * @param nave
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarNave(Nave nave) {
        boolean salir = false;
        if (!estaLlena()) {
            for (int i = 0; i < naves.length; i++) {
                if (naves[i] == null) {
                    salir = true;
                    naves[i] = nave;
                }
            }
        }
        return salir;
    }

    /**
     * TODO: Buscamos la nave a partir de la matricula pasada como parámetro
     *
     * @param matricula
     * @return la nave que encontramos o null si no existe
     */
    public Nave buscarNave(String matricula) {
        Nave encontrado = null;
        for (int i = 0; i < naves.length; i++) {
            if (naves[i].getMatricula().equals(matricula)) {
                encontrado = naves[i];
            }
        }
        if (encontrado == null) {
            System.out.println("Matrícula de avión no encontrada.");
        }
        return encontrado;
    }

    // TODO: Muestra por pantalla las naves de la lista con el formato indicado en el enunciado
    public void mostrarNaves() {
        for (int i = 0; i < naves.length; i++) {
            naves[i].toString();
        }
    }


    /**
     * TODO: Permite seleccionar una nave existente a partir de su matrícula, y comprueba si dispone de un alcance
     *  mayor o igual que el pasado como argumento, usando el mensaje pasado como argumento para la solicitud y
     *  siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente la matrícula de la nave hasta que se introduzca una con alcance suficiente
     *
     * @param teclado
     * @param mensaje
     * @param alcance
     * @return
     */
    //Ingrese matrícula de la nave: EC-2
    //Matrícula de avión no encontrada.
    //Ingrese matrícula de la nave: Soyuz
    //Avión seleccionado con alcance insuficiente.
    //Ingrese matrícula de la nave: MS-19
    public Nave seleccionarNave(Scanner teclado, String mensaje, double alcance) {
        String matricula = Utilidades.leerCadena(teclado, mensaje);//"Ingrese matrícula de la nave: "
        while (buscarNave(matricula) == null && buscarNave(matricula).getAlcance() < alcance) {

            if (buscarNave(matricula).getAlcance() < alcance) {
                System.out.print("Avión seleccionado con alcance insuficiente.");
            }
            matricula = Utilidades.leerCadena(teclado, mensaje);
        }
        return buscarNave(matricula);

    }


    /**
     * TODO: Genera un fichero CSV con la lista de Naves, SOBREESCRIBIÉNDOLO
     *
     * @param nombre
     * @return
     */
    public boolean escribirNavesCsv(String nombre) {
        PrintWriter pw = null;
        boolean close = false;
        try {
            pw = new PrintWriter(new FileWriter(nombre)); //crear un objeto PrintWriter de un objeto File

            for (int i = 0; i < naves.length; i++) {
                naves[i].toString();
                //o es pw.println(mostrarNaves());
            }
            pw.close();
            close = true;
        } catch (FileNotFoundException e) {
            System.out.print("Fichero " + nombre + " no encontrado" + e.getMessage());
        } catch (IOException e) {
            //if (close == false) {
            //System.out.println("Error de cierre de fichero "
            //     + e.getMessage());}
            System.out.print("Error de escritura en fichero " + nombre + e.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
                close = true;
            }
        }
        return close;
    }


    /**
     * TODO: Genera una lista de naves a partir del fichero CSV, usando el argumento como capacidad máxima de la lista
     *
     * @param fichero
     * @param capacidad
     * @return
     */
    public static ListaNaves leerNavesCsv(String fichero, int capacidad) {
        ListaNaves listaNaves = new ListaNaves(capacidad);
        BufferedReader sc = null;
        try {
            sc = new BufferedReader(new FileReader(fichero));
            String cadena;
            while ((cadena = sc.readLine()) != null) {

                String[] dato = cadena.split("");
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
// if (close == false) {
// System.out.println("Error de cierre de fichero "
//          + e.getMessage());
//  } else {