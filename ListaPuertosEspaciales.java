import java.io.*;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class ListaPuertosEspaciales {
    private PuertoEspacial[] lista;
private  int ocupacion=0;
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

        for (int i=0; i< lista.length; i++) {
            if (lista[i]==null) {
                ocupacion+=0;
            }else {
                ocupacion++;
            }
        }

        return ocupacion; //esto se puede pq el propio enunciado dice que devolvamos el número de puertos espaciales
    }

    // TODO: ¿Está llena la lista?
    public boolean estaLlena() {
        boolean lleno= false;
        if (getOcupacion()== lista.length){
            lleno = true;
        }
        return lleno;
    }

	// TODO: Devuelve un puerto espacial dado un indice
    public PuertoEspacial getPuertoEspacial(int i) {
        return lista[i];
    }

    /**
     * TODO: insertamos un Puerto espacial nuevo en la lista
     * @param puertoEspacial
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarPuertoEspacial(PuertoEspacial puertoEspacial) {
        boolean esPosibleRellenar=false;
        boolean salir =false;
        if(estaLlena()==false){
            esPosibleRellenar = true;

            do{
                for (int i=0; i< lista.length; i++){
                    if (lista[i] ==null){
                        salir = true;
                        lista [i]=puertoEspacial;
                        //no sería puertoEspacial= new PuertoEspacial(lista[i]) ???????????????????????????????????????'
                    }
                }
                //recorre la lista hasta haber un espacio en blanco y así , cuando salga del bucle, es decir,
                // ha enocntrado un sespaico en blanoc, pueda meter ahí el objeto
            }while (salir = false);
        }

        return esPosibleRellenar;
    }

    /**
     * TODO: Buscamos un Puerto espacial a partir del codigo pasado como parámetro
     * @param codigo
     * @return Puerto espacial que encontramos o null si no existe
     */
    public PuertoEspacial buscarPuertoEspacial(String codigo) {
        PuertoEspacial puerto= getPuertoEspacial(Integer.parseInt(codigo));
        if (puerto != null){
            return puerto;
        }
        else {
            return null;
        }
    }

    /**
     * TODO: Permite seleccionar un puerto espacial existente a partir de su código, usando el mensaje pasado como
     *  argumento para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente el código hasta que se introduzca uno correcto
     * @param teclado
     * @param mensaje
     * @return
     */
    public PuertoEspacial seleccionarPuertoEspacial(Scanner teclado, String mensaje) {
        PuertoEspacial puertoEspacial = null;
        System.out.print("Ingrese código de puerto Origen: ");
        String codigo = teclado.next();
        do {
            if (codigo != mensaje) {
                System.out.print("Código de puerto no encontrado.");
            }
            System.out.print("Ingrese código de puerto Origen: ");
             codigo = teclado.next();
        }while (codigo != mensaje);
       puertoEspacial= buscarPuertoEspacial(mensaje);

         return puertoEspacial;
    }

    /**
     * TODO: Genera un fichero CSV con la lista de puertos espaciales, SOBREESCRIBIENDOLO
     * @param nombre
     * @return
     */
    public boolean escribirPuertosEspacialesCsv(String nombre) {
        PrintWriter pw = null;
        //falta devolver algo, qué devolvemos ????? el pw ???????????''

        try {
            pw = new PrintWriter (new FileWriter("ficheroPuertos.csv"));//no hay que poner nombre???????????????????
            return true;
        }catch(FileNotFoundException e){
            System.out.print("Fichero "+nombre+" no encontrado"+e.getMessage());
        } catch(IOException e) {
            System.out.print("Error de escritura en fichero "+nombre + e.getMessage());
        }finally {
            if (pw != null) {
                try {
                    pw.close();
                } catch (IOException ex){
                    System.out.println("Error de cierre de fichero "
                            +nombre +ex.getMessage());
                }
            }
        }
    }


    /**
     * TODO: Genera una lista de PuertosEspaciales a partir del fichero CSV, usando el argumento como capacidad máxima
     *  de la lista
     * @param fichero
     * @param capacidad
     * @return
     */
    public static ListaPuertosEspaciales leerPuertosEspacialesCsv(String fichero, int capacidad) {
        ListaPuertosEspaciales listaPuertosEspaciales = new ListaPuertosEspaciales(capacidad);
        Scanner sc = null;
        try {
            sc = new Scanner (new FileReader("ficheroPuertos.csv"));

        } catch(FileNotFoundException e){
            System.out.print("Fichero "+fichero+" no encontrado"+ e.getMessage());
        }catch(IOException e) {
            System.out.print("Error de lectura de fichero " + e.getMessage());
        }finally {
            if (sc != null) {
                try {
                    sc.close();
                } catch (IOException ex) {
                    System.out.println("Error de cierre de fichero "
                            + ex.getMessage());
                }
            }
        }
        return listaPuertosEspaciales;
    }
}
