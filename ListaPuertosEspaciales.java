import java.io.*;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version 1.0
 */
public class ListaPuertosEspaciales {
    private PuertoEspacial[] lista;
    private int ocupacion = 0;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaPuertosEspaciales(int capacidad) {
        lista = new PuertoEspacial[capacidad];

    }

    // TODO: Devuelve el número de puertos espaciales que hay en la lista
    public int getOcupacion() {

        for (int i = 0; i < lista.length; i++) {
            if (lista[i] != null) {
                ocupacion++;
            }
        }
        return ocupacion;
    }

    // TODO: ¿Está llena la lista?
    public boolean estaLlena() {
        /* boolean lleno = false;
        if (getOcupacion() == lista.length) {
            lleno = true;
        }
        return lleno;
        */
        return this.getOcupacion()==lista.length;

    }

    // TODO: Devuelve un puerto espacial dado un indice
    public PuertoEspacial getPuertoEspacial(int i) {
        return lista[i];
    }

    /**
     * TODO: insertamos un Puerto espacial nuevo en la lista
     *
     * @param puertoEspacial
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarPuertoEspacial(PuertoEspacial puertoEspacial) {
        boolean salir = false;
        if (estaLlena() == false) {
            //pq hay algunos metodos que se pueden llamar usando el nombre de la clase y el nombre del metodo (los static) y los demas no ???
            for (int i = 0; i < lista.length; i++) {
                if (lista[i] == null) {
                    lista[i] = puertoEspacial;
                    salir = true;

                }
            }
            //recorre la lista hasta haber un espacio en blanco y así, cuando salga del bucle, es decir,
            // ha enocntrado un espaico en blanco, pueda meter ahí el objeto
        }
        return salir;
    }

    /**
     * TODO: Buscamos un Puerto espacial a partir del codigo pasado como parámetro
     *
     * @param codigo
     * @return Puerto espacial que encontramos o null si no existe
     */
    public PuertoEspacial buscarPuertoEspacial(String codigo) {
        PuertoEspacial encontrado = null;
        for (int i = 0; i < lista.length; i++) {
            if (lista[i].getCodigo().equals(codigo)) {
                encontrado = lista[i];
            }
        }
        if (encontrado == null) {
            System.out.print("Código de puerto no encontrado.");
        }
        return encontrado;
    }

    /**
     * TODO: Permite seleccionar un puerto espacial existente a partir de su código, usando el mensaje pasado como
     *  argumento para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente el código hasta que se introduzca uno correcto
     *
     * @param teclado
     * @param mensaje
     * @return
     */
    public PuertoEspacial seleccionarPuertoEspacial(Scanner teclado, String mensaje) {
        String codigo = Utilidades.leerCadena(teclado, mensaje); //"Ingrese código de puerto Origen: //destino "
        while (buscarPuertoEspacial(codigo) == null) {
            codigo = Utilidades.leerCadena(teclado, mensaje); //"Ingrese código de puerto Origen: //destino "
        }
        return buscarPuertoEspacial(mensaje);
//está bien ????

    }

    /**
     * TODO: Genera un fichero CSV con la lista de puertos espaciales, SOBREESCRIBIENDOLO
     *
     * @param nombre
     * @return
     */
    public boolean escribirPuertosEspacialesCsv(String nombre) {
        PrintWriter pw = null;
        boolean closes = false;
        try {
            pw = new PrintWriter(new FileWriter(nombre));
           for (int i=0; i< lista.length; i++){
               pw.println(lista[i].toString());
           }
            pw.close();
            closes = true;

        } catch (FileNotFoundException e) {
            System.out.print("Fichero " + nombre + " no encontrado" + e.getMessage());
        } catch (IOException e) {
            //   if ( closes==false) {
            //       System.out.println("Error de cierre de fichero "
            //                + e.getMessage());
            //   }else{
            System.out.print("Error de escritura en fichero "+nombre + e.getMessage());

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
     * @param fichero
     * @param capacidad
     * @return
     */
    public static ListaPuertosEspaciales leerPuertosEspacialesCsv(String fichero, int capacidad) {
        ListaPuertosEspaciales listaPuertosEspaciales = new ListaPuertosEspaciales(capacidad);
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(fichero));
            String cadena;
            while (sc.hasNextLine()) {
                cadena = sc.nextLine();
                System.out.println(cadena);
            }
            sc.close(); //esto hay que ponerlo ?????????''


        } catch (FileNotFoundException e) {
            System.out.print("Fichero " + fichero + " no encontrado" + e.getMessage());
        } catch (IOException e) {

            //     if ( close==false) {
            //         System.out.println("Error de cierre de fichero "
            //                 + e.getMessage());
            //     }else{
            System.out.print("Error de lectura de fichero " + e.getMessage());

        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        return listaPuertosEspaciales;
    }
}
