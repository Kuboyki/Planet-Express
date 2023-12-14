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
            if (naves[i] == null) {
                ocupacion += 0;
            } else {
                ocupacion++;
            }
        }

        return ocupacion;
    }

    // TODO: ¿Está llena la lista de naves?
    public boolean estaLlena() {
        boolean lleno = getOcupacion() == naves.length;
        return lleno;
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
        boolean esPosibleRellenar = false;
        boolean salir = false;
        if (!estaLlena()) {
            esPosibleRellenar = true;

            do {
                for (int i = 0; i < naves.length; i++) {
                    if (naves[i] == null) {
                        salir = true;
                        naves[i] = nave;
                    }
                }
            } while (salir == false); // se puede !salir ?????
        }

        return esPosibleRellenar;

    }

    /**
     * TODO: Buscamos la nave a partir de la matricula pasada como parámetro
     *
     * @param matricula
     * @return la nave que encontramos o null si no existe
     */
    public Nave buscarNave(String matricula) {
        Nave nave = getNave(Integer.parseInt(matricula));

        return nave;
    }

    // TODO: Muestra por pantalla las naves de la lista con el formato indicado en el enunciado
    public void mostrarNaves() {
        for (int i = 0; i < naves.length; i++) {
            System.out.println(getNave(i).getMarca() + "; " + getNave(i).getModelo() + "; " + getNave(i).getMatricula() + "; " + getNave(i).getColumnas() + "; " + getNave(i).getFilas() + "; " + getNave(i).getAlcance());
        }
        //Starship;SN15;BFR-15;5;5;66.6846E-5

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
        Nave nave = null;
        System.out.print("Ingrese matrícula de la nave: ");
        String codigo = teclado.next();
        do {
            if (codigo != mensaje) {
                System.out.print("Matrícula de avión no encontrada.");
            } else if (buscarNave(codigo).getAlcance() != alcance) {
                System.out.print("Avión seleccionado con alcance insuficiente.");
            }
            System.out.print("Ingrese matrícula de la nave: ");
            codigo = teclado.next();
        } while (codigo != mensaje || buscarNave(codigo).getAlcance() != alcance);
        nave = buscarNave(mensaje);
        return nave;
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
int cont=0;
String cadena;

do{



}while(cont != naves.length);//se termina cuando no hay más naves
//        return  getMarca()+" "+getModelo()+" ("+getMatricula()+"): "+(getFilas()*getColumnas())+ " contenedores, hasta "+getAlcance()+" UA";
close = true;

            return true;
        } catch (FileNotFoundException e) {
            System.out.print("Fichero " + nombre + " no encontrado" + e.getMessage());
        } catch (IOException e) {
            if (close == false) {
                System.out.println("Error de cierre de fichero "
                        + e.getMessage());
            } else {
                System.out.print("Error de escritura en fichero " + e.getMessage());
            }
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return false;
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
        Scanner sc = null;
        boolean close = false;
        try {
            sc = new Scanner(new FileReader(fichero));
            String cadena;
            while (sc.hasNextLine()) {
                //mientras cadena tenga líneas que leer
                // si feues leer enteros= hasNextInt()
                cadena = sc.nextLine(); //me devuelve true si hay algo qye leer, y lo im`rimo
                System.out.println(cadena);
            }
            sc.close();
            close = true;
            //añado excepciones
        } catch (FileNotFoundException e) {
            System.out.print("Fichero " + fichero + " no encontrado" + e.getMessage());
        } catch (IOException e) {
            if (close == false) {
                System.out.println("Error de cierre de fichero "
                        + e.getMessage());
            } else {
                System.out.print("Error de lectura de fichero " + e.getMessage());
            }
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        return listaNaves;
    }
}
